package com.device.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: 代理开始
 * @Date: 2017-08-17 10:10
 */
@Controller
public class StartController {
    private static final Logger logger = LoggerFactory.getLogger(StartController.class);

    private final String loginPage = "web/index";

    @RequestMapping(value = {"", "/", "/login"}, method = RequestMethod.GET)
    public String toLoginPage() {
        logger.info("StartController request login.html ");
        return loginPage;
    }

}
