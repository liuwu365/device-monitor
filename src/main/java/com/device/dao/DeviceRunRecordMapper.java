package com.device.dao;

import com.device.entity.DeviceRunRecord;
import com.device.entity.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DeviceRunRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceRunRecord record);

    int insertSelective(DeviceRunRecord record);

    DeviceRunRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceRunRecord record);

    int updateByPrimaryKey(DeviceRunRecord record);

    /**
     * 分页查询信息
     *
     * @param page
     * @return
     */
    List<DeviceRunRecord> selectByPage(Page<DeviceRunRecord> page);

    long selectCountByPage(Page<DeviceRunRecord> page);

    /**
     * 获取某个项目的最新数据
     *
     * @param item
     * @return
     */
    DeviceRunRecord selectNewData(@Param("item") String item);

    /**
     * 获取多个项目的最新数据
     *
     * @param items
     * @return
     */
    List<DeviceRunRecord> selectNewDatas(List<String> items);

    /**
     * 获取某个项目的历史平均值
     *
     * @param item
     * @return
     */
    double selectAvgValue(@Param("item") String item);



}