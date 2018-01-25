package com.hot.pwm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hot.pwm.bean.PasswordExample;
import com.hot.pwm.bean.Project;
import com.hot.pwm.bean.ProjectExample;
import com.hot.pwm.bean.ProjectExample.Criteria;
import com.hot.pwm.dao.ProjectMapper;

import javafx.scene.web.PromptData;

import java.util.List;

@Service
public class ProjectService {
	@Autowired
	ProjectMapper projectMapper;

	public List<Project> getAll() {
		ProjectExample projectExample = new ProjectExample();
		Criteria criteria = projectExample.createCriteria();
		criteria.andUsableEqualTo(true);
		criteria.andProjectIdIsNotNull();
		return projectMapper.selectByExample(projectExample);
	}

	public List<Project> getFromText(String s_key) {
		ProjectExample projectExample = new ProjectExample();
		Criteria criteria = projectExample.createCriteria();
		criteria.andProjectNameLike("%"+s_key+"%");

		Criteria criteria2 = projectExample.createCriteria();
		criteria2.andInfoLike("%"+s_key+"%");
		
		Criteria criteria3 = projectExample.createCriteria();
		criteria3.andUrlLike("%"+s_key+"%");

		projectExample.or(criteria2);
		projectExample.or(criteria3);

		return  projectMapper.selectByExample(projectExample);
	}

	public void disableList(List<Integer> arr) {
		for (Integer integer : arr) {
			Project project=new Project();
			project.setProjectId(integer);
			project.setUsable(false);
			projectMapper.updateByPrimaryKeySelective(project);
		}
//		projectMapper.updateByExample(record, example)
		
	}

	public void addOne(Project project) {
		projectMapper.insertSelective(project);
	}

	public void updateByKeySelective(Project project) {
		// TODO Auto-generated method stub
		ProjectExample example = new ProjectExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andProjectIdEqualTo(project.getProjectId());
		projectMapper.updateByExampleSelective(project, example);
		
	}

}
