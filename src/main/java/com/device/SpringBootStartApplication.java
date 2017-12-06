package com.device;

import com.device.config.SpringDevConfig;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @Description: 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
 * @Date: 2017-12-06 16:48
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(SpringDevConfig.class);
    }

}
