package com.ablog.service.user.impl;

import com.ablog.constant.ErrorConstant;
import com.ablog.dao.UserDao;
import com.ablog.exception.BusinessException;
import com.ablog.model.UserDomain;
import com.ablog.service.user.UserService;
import com.ablog.utils.TaleUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户相关Service接口实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;//这里会报错，但是并不影响


    @Override
    public UserDomain login(String username, String password) {

        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_IS_EMPTY);

        // String pwd = TaleUtils.MD5encode(username + password);
        UserDomain user = userDao.getUserInfoByCond(username, password);
        if (null == user)
            throw BusinessException.withErrorCode(ErrorConstant.Auth.USERNAME_PASSWORD_ERROR);
        return user;
    }

    @Override
    public UserDomain getUserInfoById(Integer uid) {
        return userDao.getUserInfoById(uid);
    }

    // 开启事务
    @Transactional
    @Override
    public int updateUserInfo(UserDomain user) {
        if (null == user.getUid())
            throw BusinessException.withErrorCode("用户编号不能为空");
        return userDao.updateUserInfo(user);
    }

    @Override
    public UserDomain getUserInfoByUsername(@Param("username")String username){
        return userDao.getUserInfoByUsername((username));
    }

    // 开启事务
    @Transactional
    @Override
    public int insertUserInfo(UserDomain userDomain){
        return userDao.insertUserInfo(userDomain);
    }

    // 开启事务
    @Transactional
    @Override
    public int insertUserCollect(@Param("uid")int uid, @Param("cid")int cid){
        return userDao.insertUserCollect(uid, cid);
    }

    // 开启事务
    @Transactional
    @Override
    public int insertUserSubscribe(@Param("uid")int uid, @Param("cid")int cid){
        return userDao.insertUserSubscribe(uid, cid);
    }

    // 开启事务
    @Transactional
    @Override
    public int deleteUserCollect(@Param("uid")int uid, @Param("cid")int cid){
        return userDao.deleteUserCollect(uid, cid);
    }

    // 开启事务
    @Transactional
    @Override
    public int deleteUserSubscribe(@Param("uid")int uid, @Param("cid")int cid){
        return userDao.deleteUserSubscribe(uid, cid);
    }

    // 开启事务
    @Transactional
    @Override
    public List<Integer> getCollectByUserId(@Param("uid")int uid){
        return userDao.getCollectByUserId(uid);
    }

    // 开启事务
    @Transactional
    @Override
    public List<Integer> getSubscribeByUserId(@Param("uid")int uid){
        return userDao.getSubscribeByUserId(uid);
    }
}
