package com.hot.pwm.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hot.pwm.app.SpringFxmlLoader;
import com.hot.pwm.bean.Project;
import com.hot.pwm.service.ProjectService;

import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

@Controller
public class AddProjectController implements Initializable {
	@FXML
	Pane pane;
	
	
	@FXML
	TextField add_project_name;
	@FXML
	TextArea add_info;
	@FXML
	TextField add_url;
	
	@FXML
	Button bt_add;
	@FXML
	Button bt_cancel;
	
	@Autowired
	ProjectService projectService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bt_add.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				System.out.println("project_name:");
				System.out.println(add_project_name.getText());
				System.out.println("info:");
				System.out.println(add_info.getText());
				System.out.println("url:");
				System.out.println(add_url.getText());

				String projectName=add_project_name.getText();
				String info=add_info.getText();
				String url=add_url.getText();

				Project project = new Project();
				project.setProjectName(projectName);
				project.setInfo(info);
				project.setUrl(url);

				 projectService.addOne(project);
				System.out.println(project.getProjectId());
				
				Integer pid=project.getProjectId();
				if(pid!=null&&pid>0){
					//添加成功
					close();
					
				}else{
					//添加失败
					SpringFxmlLoader springFxmlLoader = new SpringFxmlLoader();
					Parent root = (Parent)springFxmlLoader.springLoad("/view/error_msg/AlertDialog_css.fxml");
					Scene scene = new Scene(root);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.show();
//					ErrorMsgController controller = springFxmlLoader.getController();
//					controller.err_msg_label.setText("添加失败");
				}
				
				
			}
		});
		
		bt_cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				pane.getScene().getWindow().hide();
				
			}
		});
	}
	
	private void close(){
		pane.getScene().getWindow().hide();
	}

}
