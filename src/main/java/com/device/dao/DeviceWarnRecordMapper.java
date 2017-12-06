package com.device.dao;

import com.device.entity.DeviceWarnRecord;
import com.device.entity.Page;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DeviceWarnRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceWarnRecord record);

    int insertSelective(DeviceWarnRecord record);

    DeviceWarnRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceWarnRecord record);

    int updateByPrimaryKey(DeviceWarnRecord record);

    /**
     * 分页查询信息
     *
     * @param page
     * @return
     */
    List<DeviceWarnRecord> selectByPage(Page<DeviceWarnRecord> page);

    long selectCountByPage(Page<DeviceWarnRecord> page);
}