package com.device.biz;

import com.device.dao.UserInfoMapper;
import com.device.entity.Page;
import com.device.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Date: 2017-12-06 10:27
 */
@Service
public class UserService implements BaseService<UserInfo> {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Page<UserInfo> findPage(Page<UserInfo> page) {
        return null;
    }

    public UserInfo selectByAccount(String account) {
        return userInfoMapper.selectByAccount(account);
    }

    @Override
    public List<UserInfo> selectAll() {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserInfo obj) {
        return userInfoMapper.insert(obj);
    }

    @Override
    public int insertSelective(UserInfo obj) {
        return userInfoMapper.insertSelective(obj);
    }

    @Override
    public UserInfo selectByPrimaryKey(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(UserInfo obj) {
        return userInfoMapper.updateByPrimaryKeySelective(obj);
    }

    @Override
    public int updateByPrimaryKey(UserInfo obj) {
        return userInfoMapper.updateByPrimaryKey(obj);
    }

}
