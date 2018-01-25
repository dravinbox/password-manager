package com.hot.pwm.bean;

public class Project {
    private Integer projectId;

    private String projectName;

    private String info;

    private String url;

    private Boolean usable;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Boolean getUsable() {
        return usable;
    }

    public void setUsable(Boolean usable) {
        this.usable = usable;
    }

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", info=" + info + ", url=" + url
				+ ", usable=" + usable + "]";
	}
    
    
    
}