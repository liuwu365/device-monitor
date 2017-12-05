package com.device.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description:配置类
 * @User: liuwu_eva@163.com
 * @Date: 2017-05-04 下午 3:15
 */
@Component
@Data
@Accessors(chain = true)
public class MyConfig {
    private static final Logger logger = LoggerFactory.getLogger(MyConfig.class);
    private static MyConfig config;

    public synchronized static MyConfig getInstance() {
        if (config == null) {
            config = new MyConfig();
        }
        return config;
    }

    private String name;

    //public MyConfig() {
    //    try {
    //        Configuration configuration = new PropertiesConfiguration("my-config.properties");
    //        this.name = configuration.getString("myconfig.name");
    //    } catch (ConfigurationException e) {
    //        logger.error("load match config failed!error:{}", StringUtil.stackTrace(e));
    //    }
    //}

}
