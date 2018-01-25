package com.hot.pwm.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import io.datafx.controller.ViewController;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

//@Controller
@ViewController("/view/error_msg/AlertDialog_css.fxml")
public class ErrorMsgController {
	@FXML
	GridPane grid_pane;
	
	@FXML
	Label err_msg_label;
	
	@FXML
	@ActionTrigger("closeAction")
	Button okButton;
	


	/*
	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}
	*/
	@PostConstruct
	public void init(){
		System.out.println(".....init....");
	}
	
	@ActionMethod("closeAction")
	public void onAction(){
		System.out.println("123");
			grid_pane.getScene().getWindow().hide();
		
	}

}
