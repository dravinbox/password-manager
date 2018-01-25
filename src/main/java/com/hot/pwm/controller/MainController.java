package com.hot.pwm.controller;

import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;

import javax.swing.text.TabableView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hot.pwm.app.Constant;
import com.hot.pwm.app.SpringFxmlLoader;
import com.hot.pwm.bean.Password;
import com.hot.pwm.bean.PasswordSim;
import com.hot.pwm.bean.Project;
import com.hot.pwm.bean.ProjectSim;
import com.hot.pwm.component.CheckBoxButtonTableCell;
import com.hot.pwm.component.TableCellFactory;
import com.hot.pwm.component.TableCellFactory.IHandleFocus;
import com.hot.pwm.dao.PasswordMapper;
import com.hot.pwm.service.ProjectService;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

@Controller
public class MainController implements Initializable {
	

	@FXML
	private Button bt_search;
	@FXML
	private Button bt_batch_disable;
	@FXML
	private TextField tf_search;
	@FXML
	private Label pn_label;
	@FXML
	private Button bt_add;


	@FXML
	private TableView<ProjectSim> main_table;
	@FXML
	private TableColumn<ProjectSim, Boolean> tc_select;
	@FXML
	private TableColumn<ProjectSim, String> tc_name;
	@FXML
	private TableColumn<ProjectSim, String> tc_info;
	@FXML
	private TableColumn<ProjectSim, String> tc_url;
	@FXML
	private TableColumn<ProjectSim, String> tc_operate;

	@FXML
	private Pagination pn_pagination;

	@Autowired
	ProjectService projectService;

	private ObservableList<ProjectSim> list=FXCollections.observableArrayList();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		selectColumn();
		operateColumn();
		
		setTableCell(tc_name,"projectName");
		setTableCell(tc_info,"info");
		setTableCell(tc_url,"url");
//		tc_name.setCellValueFactory(cellData->cellData.getValue().projectNameProperty());
//		tc_info.setCellValueFactory(cellData->cellData.getValue().infoProperty());
//		tc_url.setCellValueFactory(cellData->cellData.getValue().urlProperty());
		main_table.setItems(list);

		pn_pagination.setPageFactory((Integer pageIndex)->{
			showList(pageIndex);
			return pn_label;
		});

		bt_add.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {

				addProject();

				/*
				SpringFxmlLoader springFxmlLoader = new SpringFxmlLoader();
				Parent target = (Parent) springFxmlLoader.springLoad("/view/addProject.fxml");
				Scene scene = new Scene(target); //创建场景；  
				Stage stg=new Stage();//创建舞台；  
				stg.setScene(scene); //将场景载入舞台；  
				stg.show(); //显示窗口；
				*/
			}

		});
		bt_batch_disable.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				delAll();
				
			}
		});

		tf_search.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					search();

				}

			}

		});

	}
	private void delAll(){
		
		List<Integer> arr=new ArrayList<Integer>();
		List<ProjectSim> arr_reload=new ArrayList<ProjectSim>();

		//在list中找true的，添加到arr和arr_list_del
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).isIsCheck()){
				//添加到倾清理数据列表中
				arr.add(list.get(i).getProjectId());
			}else{
				//添加到渲染列表中
				arr_reload.add(list.get(i));
			}
			
		}
		//重新加载list
		list.clear();
		for (ProjectSim projectSim : arr_reload) {
			list.add(projectSim);
		}

		//清理数据库,设置usable为0
		projectService.disableList(arr);
		
	}
	private void addProject(){
		Project p1=new Project();
		p1.setProjectName("项目名");
		projectService.addOne(p1);
		showLastPage();
	}
	private void showLastPage(){
		 PageHelper.startPage(1,Constant.pageNumber);
		List<Project> p_list = projectService.getAll();
		PageInfo<Project> pageInfo=new PageInfo<Project>(p_list);
		System.out.println(pageInfo.getPages());
		showList(pageInfo.getPages());
		pn_pagination.setCurrentPageIndex(pageInfo.getPages());
		
	}

	private void showList(Integer pagesize) {
		//清空list
		list.clear();
		// 查询数据库的前10条纪录Constant.pageNumber
		 PageHelper.startPage(pagesize+1,Constant.pageNumber);
//		 PageHelper.startPage(pagesize+1,2);
		List<Project> p_list = projectService.getAll();

		//使用pageInfo设置pagination
		PageInfo<Project> pageInfo=new PageInfo<Project>(p_list);
		System.out.println(pageInfo);
//		pn_pagination.setCurrentPageIndex(pageInfo.getPageNum());
//		pn_pagination.setMaxPageIndicatorCount(navigateLastPage);
		pn_pagination.setPageCount(pageInfo.getPages());

//		System.out.println("==="+pageInfo);

//		System.out.println("---"+p_list);
		for (Project p : p_list) {
//			System.out.println(p.toString());
			list.add(new ProjectSim(p));
		}

		main_table.setItems(list);
		
		
	}

	public void search() {
//		System.out.println("Button Clicked!"); System.out.println(tf_search.getText());

		String s_key=tf_search.getText();
		tf_search.setText("");
		
		List<Project> p_list = projectService.getFromText(s_key);
		System.out.println(p_list);
		if(!p_list.isEmpty()){
			list.clear();
			for (Project project : p_list) {
				list.add(new ProjectSim(project));
			}
		}else{
			//找不到相关
		}



	}
	private void operateColumn(){
		tc_operate.setCellFactory(new Callback<TableColumn<ProjectSim,String>, TableCell<ProjectSim,String>>() {

			@Override
			public TableCell<ProjectSim, String> call(TableColumn<ProjectSim, String> param) {
				// TODO Auto-generated method stub

				HBox hBox = new HBox();
				Button passwordButton = new Button();
				passwordButton.setText("管理密码");
				hBox.getChildren().add(passwordButton);
				Button disableButton=new Button();
				disableButton.setText("失效");
				hBox.getChildren().add(disableButton);

				TableCell cell = new TableCell<ProjectSim, String>(){
					@Override
					public  void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if(empty){
							setGraphic(null);
						}else{
							setGraphic(hBox);
						}
						
					}
				};

				passwordButton.setOnMouseClicked(e->{
						ProjectSim projectSim=list.get(cell.getIndex());
						Constant.editing_pwd_project_id=projectSim.getProjectId();
						SpringFxmlLoader springFxmlLoader= new SpringFxmlLoader();
						Parent mpwd =(Parent) springFxmlLoader.springLoad("/view/managerPwd.fxml");
						Scene scene = new Scene(mpwd);
						Stage stage = new Stage();
						stage.setScene(scene);
						stage.show();
					
				});
				disableButton.setOnMouseClicked(e->{
					ProjectSim projectSim=list.get(cell.getIndex());
					if(projectSim.isIsCheck()){
						projectSim.setIsCheck(false);
					}else{
						projectSim.setIsCheck(true);
					}
					delAll();
				});

				return cell;
			}
		});
		
	}
	private void selectColumn(){
		tc_select.setCellFactory(new Callback<TableColumn<ProjectSim,Boolean>, TableCell<ProjectSim,Boolean>>() {

			@Override
			public TableCell<ProjectSim, Boolean> call(TableColumn<ProjectSim, Boolean> param) {
			
				final CheckBoxButtonTableCell<ProjectSim, Boolean> cell = new CheckBoxButtonTableCell<ProjectSim,Boolean>();
				final CheckBox checkBox = (CheckBox) cell.getGraphic();
				checkBox.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
//						System.out.println("选择了");

						ProjectSim projectSim = list.get(cell.getIndex());
						
						if(projectSim.isIsCheck()){
							projectSim.setIsCheck(false);
						}else{
							projectSim.setIsCheck(true);
						}
						
					}
				});
				return cell;
			}
			
		});
		
	}
	//设置某列为输入框形式。聚焦时，正在修改；失去焦点时，保存信息。
	private void setTableCell(TableColumn<ProjectSim, String> column, String field) {
		column.setCellValueFactory(celldata -> {
			SimpleStringProperty ssp = null;
			try {
				ProjectSim projectSim = celldata.getValue();

				Class<?> c = Class.forName("com.hot.pwm.bean.ProjectSim");
				// Object obj = c.newInstance();
				// 第一个参数写的是方法名,第二个\第三个\...写的是方法参数列表中参数的类型
				Method method = c.getMethod(field + "Property");
				// invoke是执行该方法,并携带参数值
				ssp = (SimpleStringProperty) method.invoke(projectSim);

			} catch (Exception e) {
				// TODO: handle exception
				System.err.println(e);
			}
			return ssp;

		});
		column.setCellFactory(e -> {
			TextField textField = new TextField();
			TableCellFactory tableCellFactory = new TableCellFactory<ProjectSim, String>(textField);
			TableCell cell = tableCellFactory.getTableCell();
			tableCellFactory.setFocusAction(new IHandleFocus() {
				@Override
				public void handleFocus() {
					System.out.println("编辑中...");
				}

				@Override
				public void handleBlur() {
					ProjectSim projectSim = list.get(cell.getIndex());

					Project project = new Project();
					try {
						// 里面写自己的类名及路径
						Class<?> c = Class.forName("com.hot.pwm.bean.Project");
						// Object obj = c.newInstance();
						// 第一个参数写的是方法名,第二个\第三个\...写的是方法参数列表中参数的类型
						Method method = c.getMethod("setProjectId", Integer.class);
						// invoke是执行该方法,并携带参数值
						method.invoke(project, projectSim.getProjectId());

						String field2 = field.replace(field.substring(0, 1), field.substring(0, 1).toUpperCase());
						Method method2 = c.getMethod("set" + field2, String.class);
						method2.invoke(project, textField.getText());

					} catch (Exception e) {
						// TODO: handle exception
						System.err.println(e);
					}

					projectService.updateByKeySelective(project);

				}
			});

			return cell;
		});

	}

	// When user click on myButton
	// this method will be called.
	// public void showDateTime(ActionEvent event) {
	// System.out.println("Button Clicked!");
	//
	// Date now= new Date();
	//
	// DateFormat df = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss");
	// String dateTimeString = df.format(now);
	// // Show in VIEW
	// myTextField.setText(dateTimeString);
	//
	// }

}