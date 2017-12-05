package com.device.dao;

import com.device.entity.DeviceWarnRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeviceWarnRuleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceWarnRule record);

    int insertSelective(DeviceWarnRule record);

    DeviceWarnRule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceWarnRule record);

    int updateByPrimaryKey(DeviceWarnRule record);

    List<DeviceWarnRule> selectWarnRule(@Param("item") String item, @Param("status") int status);
}