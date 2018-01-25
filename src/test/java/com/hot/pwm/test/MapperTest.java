package com.hot.pwm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hot.pwm.bean.Project;
import com.hot.pwm.dao.PasswordMapper;
import com.hot.pwm.dao.ProjectMapper;

@Component
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	PasswordMapper passwordMapper;
	
	@Autowired
	ProjectMapper projectMapper;
	
	@Test
	public void testCURD(){
//		ApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext.xml");
////		//2.从容器里拿mapper
//		ProjectMapper  projectMapper= applicationContext.getBean(ProjectMapper.class);
		Project project=new Project() ;
		project.setProjectName("test");
		project.setUrl("http://www.baidu.com");
		project.setInfo("简介");
		projectMapper.insertSelective(project);
		System.out.println(project.getProjectId());
		
	}

}
