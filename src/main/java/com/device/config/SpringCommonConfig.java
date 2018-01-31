package com.device.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerPropertiesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import javax.servlet.Filter;
import javax.servlet.http.HttpSessionListener;

/**
 * @ClassName: SpringCommonConfig
 * @Package com.liuwu.config
 * @Description: 公共配置类
 * @author: liuwu1189@dingtalk.com
 * @date: 2017-05-04 16:16:55
 */
@ImportResource({
        "classpath:net/bull/javamelody/monitoring-spring-datasource.xml",
        "classpath:application-context.xml"
})
@Import({ServerPropertiesAutoConfiguration.class,
        DispatcherServletAutoConfiguration.class,
        EmbeddedServletContainerAutoConfiguration.class})
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, MultipartAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class SpringCommonConfig {

    @Bean
    public HttpSessionListener javaMelodyListener(){
        return new net.bull.javamelody.SessionListener();
    }

    @Bean
    public Filter javaMelodyFilter(){
        return new net.bull.javamelody.MonitoringFilter();
    }
}
