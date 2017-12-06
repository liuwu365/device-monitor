package com.device.dao;

import com.device.entity.DeviceInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceInfo record);

    int insertSelective(DeviceInfo record);

    DeviceInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceInfo record);

    int updateByPrimaryKey(DeviceInfo record);

    List<DeviceInfo> selectDeviceList(@Param("status") int status);

    DeviceInfo selectByDeviceUid(@Param("deviceUid")String deviceUid);
}