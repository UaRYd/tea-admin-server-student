package cn.tedu.tea.admin.server;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SLF4JTests {

    @Test
    void test() {
        log.trace("这是一条【TRACE】日志");
        log.debug("这是一条【DEBUG】日志");
        log.info("这是一条【INFO】日志");
        log.warn("这是一条【WARN】日志");
        log.error("这是一条【ERROR】日志");

        int x = 1;
        int y = 2;
        // x = 1, y = 2, x + y = 3
        System.out.println("x = " + x + ", y = " + y + ", x + y = " + (x + y));
        log.debug("x = {}, y = {}, x + y = {}", x, y, x + y);
    }
}
