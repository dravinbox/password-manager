package com.hot.pwm.bean;

import javax.net.ssl.SSLParameters;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PasswordSim {
    private SimpleIntegerProperty pid;

    private SimpleIntegerProperty projectId;

    private SimpleStringProperty info;

    private SimpleStringProperty username;

    private SimpleStringProperty password;

    private SimpleStringProperty phone;

    private SimpleStringProperty email;

    private SimpleStringProperty more;

    private SimpleBooleanProperty usable;
    
    public PasswordSim(Password password) {
    	this.pid=new SimpleIntegerProperty( password.getPid());
    	this.projectId= new SimpleIntegerProperty(password.getProjectId());
    	this.info= new SimpleStringProperty(password.getInfo());
    	this.username=new SimpleStringProperty(password.getUsername());
    	this.password=new SimpleStringProperty(password.getPassword());
    	this.phone=new SimpleStringProperty(password.getPhone());
    	this.email=new SimpleStringProperty(password.getEmail());
    	this.more=new SimpleStringProperty(password.getMore());
    	this.usable=new SimpleBooleanProperty(password.getUsable());
	}

	public final SimpleIntegerProperty pidProperty() {
		return this.pid;
	}
	

	public final int getPid() {
		return this.pidProperty().get();
	}
	

	public final void setPid(final int pid) {
		this.pidProperty().set(pid);
	}
	

	public final SimpleIntegerProperty projectIdProperty() {
		return this.projectId;
	}
	

	public final int getProjectId() {
		return this.projectIdProperty().get();
	}
	

	public final void setProjectId(final int projectId) {
		this.projectIdProperty().set(projectId);
	}
	

	public final SimpleStringProperty infoProperty() {
		return this.info;
	}
	

	public final String getInfo() {
		return this.infoProperty().get();
	}
	

	public final void setInfo(final String info) {
		this.infoProperty().set(info);
	}
	

	public final SimpleStringProperty usernameProperty() {
		return this.username;
	}
	

	public final String getUsername() {
		return this.usernameProperty().get();
	}
	

	public final void setUsername(final String username) {
		this.usernameProperty().set(username);
	}
	

	public final SimpleStringProperty passwordProperty() {
		return this.password;
	}
	

	public final String getPassword() {
		return this.passwordProperty().get();
	}
	

	public final void setPassword(final String password) {
		this.passwordProperty().set(password);
	}
	

	public final SimpleStringProperty phoneProperty() {
		return this.phone;
	}
	

	public final String getPhone() {
		return this.phoneProperty().get();
	}
	

	public final void setPhone(final String phone) {
		this.phoneProperty().set(phone);
	}
	

	public final SimpleStringProperty emailProperty() {
		return this.email;
	}
	

	public final String getEmail() {
		return this.emailProperty().get();
	}
	

	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	

	public final SimpleStringProperty moreProperty() {
		return this.more;
	}
	

	public final String getMore() {
		return this.moreProperty().get();
	}
	

	public final void setMore(final String more) {
		this.moreProperty().set(more);
	}
	

	public final SimpleBooleanProperty usableProperty() {
		return this.usable;
	}
	

	public final boolean isUsable() {
		return this.usableProperty().get();
	}
	

	public final void setUsable(final boolean usable) {
		this.usableProperty().set(usable);
	}
	
    
    

}
