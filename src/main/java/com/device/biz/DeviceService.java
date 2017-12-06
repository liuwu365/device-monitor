package com.device.biz;

import com.device.dao.DeviceRunRecordMapper;
import com.device.dao.DeviceWarnRecordMapper;
import com.device.entity.DeviceRunRecord;
import com.device.entity.DeviceWarnRecord;
import com.device.entity.Page;
import com.device.util.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Date: 2017-11-30 19:18
 */
@Service
public class DeviceService implements BaseService<DeviceRunRecord> {
    @Autowired
    private DeviceRunRecordMapper deviceRunRecordMapper;
    @Autowired
    private DeviceWarnRecordMapper deviceWarnRecordMapper;


    public List<DeviceRunRecord> selectNewDatas(List<String> items) {
        return deviceRunRecordMapper.selectNewDatas(items);
    }

    public double selectItemAvgValue(String item) {
        return deviceRunRecordMapper.selectAvgValue(item);
    }

    @Override
    public Page<DeviceRunRecord> findPage(Page<DeviceRunRecord> page) {
        long total = deviceRunRecordMapper.selectCountByPage(page);
        List<DeviceRunRecord> list = deviceRunRecordMapper.selectByPage(page);
        page.setTotal(CheckUtil.isEmpty(total) ? 0 : total);
        page.setResult(list);
        return page;
    }

    public Page<DeviceWarnRecord> findWarnRecordPage(Page<DeviceWarnRecord> page) {
        long total = deviceWarnRecordMapper.selectCountByPage(page);
        List<DeviceWarnRecord> list = deviceWarnRecordMapper.selectByPage(page);
        page.setTotal(CheckUtil.isEmpty(total) ? 0 : total);
        page.setResult(list);
        return page;
    }

    @Override
    public List<DeviceRunRecord> selectAll() {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return deviceRunRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DeviceRunRecord obj) {
        return deviceRunRecordMapper.insert(obj);
    }

    @Override
    public int insertSelective(DeviceRunRecord obj) {
        return deviceRunRecordMapper.insertSelective(obj);
    }

    @Override
    public DeviceRunRecord selectByPrimaryKey(Long id) {
        return deviceRunRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(DeviceRunRecord obj) {
        return deviceRunRecordMapper.updateByPrimaryKeySelective(obj);
    }

    @Override
    public int updateByPrimaryKey(DeviceRunRecord obj) {
        return deviceRunRecordMapper.updateByPrimaryKey(obj);
    }
}
