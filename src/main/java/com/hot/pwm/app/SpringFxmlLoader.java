package com.hot.pwm.app;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

public class SpringFxmlLoader extends FXMLLoader {

	private static ApplicationContext applicationContext = null;

	public Object springLoad(String url) {
		applicationContext = new ClassPathXmlApplicationContext(new String[] { "classpath:applicationContext.xml" });
		this.setControllerFactory(applicationContext::getBean);
		this.setLocation(getClass().getResource(url)); 
		try {
			return this.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
