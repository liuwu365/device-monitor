package com.device;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = StartApplication.class)
@ContextConfiguration(locations = {
        "classpath:application-context.xml",
        "classpath*:application-dao.xml"
})
public class StartApplicationTests {
    private static final Gson gson = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(StartApplicationTests.class);

    private ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r);
        t.setName("Processor");
        return t;
    });

    @Test
    public void testDb() {

    }




}
