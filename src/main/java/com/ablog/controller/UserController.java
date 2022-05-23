package com.ablog.controller;

import com.ablog.constant.LogActions;
import com.ablog.controller.admin.AuthController;
import com.ablog.model.UserDomain;
import com.ablog.service.log.LogService;
import com.ablog.service.user.UserService;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.github.pagehelper.PageInfo;
import com.vdurmont.emoji.EmojiParser;
import com.ablog.constant.ErrorConstant;
import com.ablog.constant.Types;
import com.ablog.constant.WebConst;
import com.ablog.dto.MetaDto;
import com.ablog.dto.cond.ContentCond;
import com.ablog.exception.BusinessException;
import com.ablog.model.CommentDomain;
import com.ablog.model.ContentDomain;
import com.ablog.model.MetaDomain;
import com.ablog.service.article.ContentService;
import com.ablog.service.comment.CommentService;
import com.ablog.service.meta.MetaService;
import com.ablog.utils.APIResponse;
import com.ablog.utils.IPKit;
import com.ablog.utils.TaleUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Api("用户管理")
@Controller
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @GetMapping("/user")
    public String userPage(){
        return "user/index";
    }

    @GetMapping("/user/login")
    public String loginPage(){
        return "user/login";
    }

    @GetMapping("/user/register")
    public String registerPage(){
        return "user/register";
    }

    @ApiOperation("游客登录")
    @PostMapping("/user/tologin")
    @ResponseBody
    public APIResponse toLogin(
            HttpServletRequest request,
            HttpServletResponse response,
            @ApiParam(name = "username", value = "用户名", required = true)
            @RequestParam(name = "username", required = true)
                    String username,
            @ApiParam(name = "password", value = "用户名", required = true)
            @RequestParam(name = "password", required = true)
                    String password,
            @ApiParam(name = "remember_me", value = "记住我", required = false)
            @RequestParam(name = "remember_me", required = false)
                    String remember_me
    ){
        Integer error_count = cache.get("login_error_count");
        try {
            // 调用Service登录方法
            UserDomain userInfo = userService.login(username, password);
            // 设置用户信息session
            request.getSession().setAttribute(WebConst.LOGIN_SESSION_KEY, userInfo);
            // 判断是否勾选记住我
            if (StringUtils.isNotBlank(remember_me)) {
                TaleUtils.setCookie(response, userInfo.getUid());
            }
            // 写入日志
            logService.addLog(LogActions.LOGIN.getAction(), userInfo.getUsername()+"用户", request.getRemoteAddr(), userInfo.getUid());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            error_count = null == error_count ? 1 : error_count + 1;
            if (error_count > 3) {
                return APIResponse.fail("您输入密码已经错误超过3次，请10分钟后尝试");
            }
            System.out.println(error_count);
            // 设置缓存为10分钟
            cache.set("login_error_count", error_count, 10 * 60);
            String msg = "登录失败";
            if (e instanceof BusinessException) {
                msg = e.getMessage();
            } else {
                LOGGER.error(msg,e);
            }
            return APIResponse.fail(msg);
        }
        // 返回登录成功信息
        return APIResponse.success();
    }

    @ApiOperation("游客注册")
    @PostMapping("/user/toregister")
    @ResponseBody
    public APIResponse toRegister(
            HttpServletRequest request,
            HttpServletResponse response,
            @ApiParam(name = "username", value = "用户名", required = true)
            @RequestParam(name = "username", required = true)
                    String username,
            @ApiParam(name = "password", value = "用户名", required = true)
            @RequestParam(name = "password", required = true)
                    String password,
            @ApiParam(name = "email", value = "邮箱", required = true)
            @RequestParam(name = "email", required = true)
                    String email
    ){
        // 调用Service登录方法
        UserDomain userInfo = userService.getUserInfoByUsername(username);
        if(userInfo != null)
            return APIResponse.fail("该用户名已存在！");
        UserDomain userDomain = new UserDomain();
        userDomain.setUsername(username);
        userDomain.setPassword(password);
        userDomain.setEmail(email);
        userDomain.setScreenName(username);
        userDomain.setLogged(0);
        userDomain.setActivated(0);
        Date date = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddhh");
        String strDate = sdf1.format(date);
        userDomain.setCreated(Integer.parseInt(strDate));
        userDomain.setGroupName("visitor");
        // 新增用户
        userService.insertUserInfo(userDomain);
        return APIResponse.success();
    }
}