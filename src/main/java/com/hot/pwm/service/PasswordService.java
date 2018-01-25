package com.hot.pwm.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hot.pwm.bean.Password;
import com.hot.pwm.bean.PasswordExample;
import com.hot.pwm.bean.PasswordExample.Criteria;
import com.hot.pwm.dao.PasswordMapper;

@Service
public class PasswordService {
	@Autowired
	PasswordMapper passwordMapper;

	public List<Password> getAllByProjectId(int project_id) {
		PasswordExample example=new PasswordExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andProjectIdEqualTo(project_id);
		
		return passwordMapper.selectByExample(example);
	}
	public void updateByPidSelective(Password record){
		PasswordExample example = new PasswordExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andPidEqualTo(record.getPid());
		passwordMapper.updateByExampleSelective(record, example);
	}
	public void addOne(Password password) {
		passwordMapper.insertSelective(password);
		
	}
	public Password getOne(Integer pid) {
		return passwordMapper.selectByPrimaryKey(pid);
	}
	

}
