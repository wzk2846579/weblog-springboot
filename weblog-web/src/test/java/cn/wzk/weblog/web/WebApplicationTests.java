package cn.wzk.weblog.web;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author wangzk16
 * @date 2026年06月15日 17:59
 */
@SpringBootTest
@Slf4j
public class WebApplicationTests {

    @Test
    public void contextLoads() {}

    @Test
    public void testLog() {
        log.info("这是一条info级别的日志！");
        log.warn("这是一条warn级别的日志！");
        log.error("这是一条error级别的日志！");

        String author = "WZK";
        log.info("这是一行带占位符的日志，作者：{}", author);
    }
}
