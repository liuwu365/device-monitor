package com.device.dao;

import com.device.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo selectByAccount(@Param("account") String account);
}