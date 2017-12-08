package com.device.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 代理开始
 * @Date: 2017-08-17 10:10
 */
@Controller
public class StartController {
    private static final Logger logger = LoggerFactory.getLogger(StartController.class);

    private final String loginPage = "web/login";

    @RequestMapping(value = {"", "/", "/login"}, method = RequestMethod.GET)
    public String toLoginPage(HttpServletRequest request) {
        logger.info("StartController request login.html ");
        request.setAttribute("ctx", "/device-monitor");
        return loginPage;
    }

}
