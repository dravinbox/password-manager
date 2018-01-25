package com.hot.pwm.controller;


import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hot.pwm.app.Constant;
import com.hot.pwm.bean.Password;
import com.hot.pwm.bean.PasswordSim;
import com.hot.pwm.component.TableCellFactory;
import com.hot.pwm.component.TableCellFactory.IHandleFocus;
import com.hot.pwm.service.PasswordService;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

@Controller
public class PwdManagerController implements Initializable {
	@FXML
	Pane pane_1;
	@FXML
	Button bt_save;
	@FXML
	Button bt_add;
	@FXML
	TableView pwd_tv;
	@FXML
	TableColumn<PasswordSim, String> tc_info;
	@FXML
	TableColumn<PasswordSim, String> tc_account;
	@FXML
	TableColumn<PasswordSim, String> tc_pwd;
	@FXML
	TableColumn<PasswordSim, String> tc_phone;
	@FXML
	TableColumn<PasswordSim, String> tc_email;
	@FXML
	TableColumn<PasswordSim, String> tc_more;

	@Autowired
	PasswordService passwordService;

	private ObservableList<PasswordSim> list = FXCollections.observableArrayList();



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadList();

		setInputTableCell();

		bt_save.setOnMouseClicked(event->{
			pane_1.getScene().getWindow().hide();
		});
		bt_add.setOnMouseClicked(event -> {
			Password password = new Password();
			password.setProjectId(Constant.editing_pwd_project_id);
			password.setInfo("默认");
			passwordService.addOne(password);

			loadList();

		});

	}
	private void setInputTableCell() {
		setTableCell(tc_info, "info");
		setTableCell(tc_account, "username");
		setTableCell(tc_pwd, "password");
		setTableCell(tc_phone, "phone");
		setTableCell(tc_email, "email");
		setTableCell(tc_more, "more");

	}
	private void loadList(){
		getList(Constant.editing_pwd_project_id);
	}

	private void getList(int project_id) {
		// TODO Auto-generated method stub
		list.clear();
		List<Password> allByProjectId = passwordService.getAllByProjectId(project_id);
		for (Password password : allByProjectId) {
			PasswordSim passwordSim = new PasswordSim(password);
			list.add(passwordSim);
		}
		pwd_tv.setItems(list);

	}
	//设置某列为输入框形式。聚焦时，正在修改；失去焦点时，保存信息。
	private void setTableCell(TableColumn<PasswordSim, String> column, String field) {
		column.setCellValueFactory(celldata -> {
			SimpleStringProperty ssp = null;
			try {
				PasswordSim passwordSim = celldata.getValue();

				Class<?> c = Class.forName("com.hot.pwm.bean.PasswordSim");
				// Object obj = c.newInstance();
				// 第一个参数写的是方法名,第二个\第三个\...写的是方法参数列表中参数的类型
				Method method = c.getMethod(field + "Property");
				// invoke是执行该方法,并携带参数值
				ssp = (SimpleStringProperty) method.invoke(passwordSim);

			} catch (Exception e) {
				// TODO: handle exception
				System.err.println(e);
			}
			return ssp;

		});
		column.setCellFactory(e -> {
			TextField textField = new TextField();
			TableCellFactory tableCellFactory = new TableCellFactory<PasswordSim, String>(textField);
			TableCell cell = tableCellFactory.getTableCell();
			tableCellFactory.setFocusAction(new IHandleFocus() {
				@Override
				public void handleFocus() {
					System.out.println("编辑中...");
				}

				@Override
				public void handleBlur() {
					PasswordSim passwordSim = list.get(cell.getIndex());

					Password password = new Password();
					try {
						// 里面写自己的类名及路径
						Class<?> c = Class.forName("com.hot.pwm.bean.Password");
						// Object obj = c.newInstance();
						// 第一个参数写的是方法名,第二个\第三个\...写的是方法参数列表中参数的类型
						Method method = c.getMethod("setPid", Integer.class);
						// invoke是执行该方法,并携带参数值
						method.invoke(password, passwordSim.getPid());

						String field2 = field.replace(field.substring(0, 1), field.substring(0, 1).toUpperCase());
						Method method2 = c.getMethod("set" + field2, String.class);
						method2.invoke(password, textField.getText());

					} catch (Exception e) {
						// TODO: handle exception
						System.err.println(e);
					}

					// password.setPid(passwordSim.getPid());
					// password.setUsername(textField.getText());
					passwordService.updateByPidSelective(password);

				}
			});

			return cell;
		});

	}

}
