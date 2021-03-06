package com.ablog.service.user;

import com.ablog.model.UserDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户相关Service接口
 */
public interface UserService {

    /**
     * 用户登录
     * @param username  用户名
     * @param password  密码
     * @return
     */
    UserDomain login(String username, String password);

    /**
     * 通过用户ID获取用户信息
     * @param uid   主键
     * @return
     */
    UserDomain getUserInfoById(Integer uid);

    /**
     * 更改用户信息
     * @param user  user对象
     * @return
     */
    int updateUserInfo(UserDomain user);

    UserDomain getUserInfoByUsername(@Param("username")String username);
    int insertUserInfo(UserDomain userDomain);
    int insertUserCollect(@Param("uid")int uid, @Param("cid")int cid);
    int insertUserSubscribe(@Param("uid")int uid, @Param("cid")int cid);
    int deleteUserCollect(@Param("uid")int uid, @Param("cid")int cid);
    int deleteUserSubscribe(@Param("uid")int uid, @Param("cid")int cid);
    List<Integer> getCollectByUserId(@Param("uid")int uid);
    List<Integer> getSubscribeByUserId(@Param("uid")int uid);
}
