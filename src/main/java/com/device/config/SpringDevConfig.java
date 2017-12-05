package com.device.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description:
 * @User: liuwu_eva@163.com
 * @Date: 2017-05-04 下午 3:56
 */
@ImportResource({
        "classpath:application-dao.xml",
})
@Import(SpringCommonConfig.class)
public class SpringDevConfig {
    private static final Logger logger = LoggerFactory.getLogger(SpringDevConfig.class);

    public SpringDevConfig() {
        logger.info("load dev env");
    }
}
