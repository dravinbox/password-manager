package com.hot.pwm.bean;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProjectSim {
	
	private SimpleBooleanProperty isCheck;
    private SimpleIntegerProperty projectId;

    private SimpleStringProperty projectName;

    private SimpleStringProperty info;

    private SimpleStringProperty url;

    private SimpleBooleanProperty usable;
    
    public ProjectSim(Project project) {
    	this.isCheck=new SimpleBooleanProperty(false);
    	this.projectId=new SimpleIntegerProperty(project.getProjectId());
    	this.projectName=new SimpleStringProperty(project.getProjectName());
    	this.info=new SimpleStringProperty(project.getInfo());
    	this.url=new SimpleStringProperty(project.getUrl());
    	this.usable=new SimpleBooleanProperty(project.getUsable());
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
	

	public final SimpleStringProperty projectNameProperty() {
		return this.projectName;
	}
	

	public final String getProjectName() {
		return this.projectNameProperty().get();
	}
	

	public final void setProjectName(final String projectName) {
		this.projectNameProperty().set(projectName);
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
	

	public final SimpleStringProperty urlProperty() {
		return this.url;
	}
	

	public final String getUrl() {
		return this.urlProperty().get();
	}
	

	public final void setUrl(final String url) {
		this.urlProperty().set(url);
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



	public final SimpleBooleanProperty isCheckProperty() {
		return this.isCheck;
	}
	



	public final boolean isIsCheck() {
		return this.isCheckProperty().get();
	}
	



	public final void setIsCheck(final boolean isCheck) {
		this.isCheckProperty().set(isCheck);
	}
	
	

    
    
}