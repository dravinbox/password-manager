package com.hot.pwm.dao;

import com.hot.pwm.bean.Password;
import com.hot.pwm.bean.PasswordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PasswordMapper {
    long countByExample(PasswordExample example);

    int deleteByExample(PasswordExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Password record);

    int insertSelective(Password record);

    List<Password> selectByExample(PasswordExample example);

    Password selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Password record, @Param("example") PasswordExample example);

    int updateByExample(@Param("record") Password record, @Param("example") PasswordExample example);

    int updateByPrimaryKeySelective(Password record);

    int updateByPrimaryKey(Password record);
}