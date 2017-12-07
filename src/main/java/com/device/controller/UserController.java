package com.device.controller;

import com.device.biz.UserService;
import com.device.entity.Result;
import com.device.entity.ResultCode;
import com.device.entity.UserInfo;
import com.device.enums.UserStatus;
import com.device.util.CheckUtil;
import com.device.util.ErrorWriterUtil;
import com.device.util.SecurityUtil;
import com.device.util.SessionUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @Description:
 * @Date: 2017-12-06 10:30
 */
@Controller
@RequestMapping("/interface/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final Gson gson = new Gson();

    @Resource
    private UserService userService;

    @RequestMapping(value = {"", "/", "/login"}, method = RequestMethod.GET)
    public String toLoginPage(Model model) {
        logger.info("UserController request login.html ");
        return "static/web/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(String account, String password, HttpServletRequest request) {
        Result result = new Result();
        try {
            if (CheckUtil.isEmpty(account) || CheckUtil.isEmpty(password)) {
                return Result.failure("帐号或密码不能为空");
            }
            UserInfo userInfo = userService.selectByAccount(account);
            if (CheckUtil.isEmpty(userInfo)) {
                return Result.failure("您输入的帐号不存在");
            }
            if (userInfo.getStatus().intValue() == UserStatus.CLOSE.getCode()) {
                return Result.failure("您的帐号未启用，不能登录");
            }
            if (!SecurityUtil.MD5(password).equals(userInfo.getPassword())) {
                return Result.failure("密码错误");
            }
            //保存session
            SessionUtil.setUser(request, userInfo);
            result = Result.success();
        } catch (Exception e) {
            result = Result.failure(ResultCode.SERVER_INTERNAL_ERROR);
            logger.error("user login error|ex={}", ErrorWriterUtil.writeError(e));
        }
        return result;
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public Result logout(HttpServletRequest request) {
        Result result = null;
        SessionUtil.removeUser(request);
        result = Result.success("退出登录成功");
        logger.info("logout result={}", gson.toJson(result));
        return result;
    }

}
