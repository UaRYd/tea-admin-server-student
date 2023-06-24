package cn.tedu.tea.admin.server.core.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@MapperScans({
        @MapperScan("cn.tedu.tea.admin.server.account.dao.persist.mapper"),
        @MapperScan("cn.tedu.tea.admin.server.content.dao.persist.mapper")
})
public class MyBatisConfiguration {
    public MyBatisConfiguration() {
        log.info("创建配置类对象：MyBatisConfiguration");
    }
}
