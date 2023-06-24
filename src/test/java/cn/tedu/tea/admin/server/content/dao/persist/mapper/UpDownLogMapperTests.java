package cn.tedu.tea.admin.server.content.dao.persist.mapper;

import cn.tedu.tea.admin.server.content.pojo.entity.UpDownLog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UpDownLogMapperTests {

    @Autowired
    UpDownLogMapper mapper;

    @Test
    void insert() {
        UpDownLog upDownLog = new UpDownLog();
        upDownLog.setUserId(1L);
        upDownLog.setResourceType(1);
        upDownLog.setResourceId(1L);
        upDownLog.setOpType(1);

        System.out.println("插入数据之前，参数：" + upDownLog);
        int rows = mapper.insert(upDownLog);
        System.out.println("插入数据完成，受影响的行数：" + rows);
        System.out.println("插入数据之后，参数：" + upDownLog);
    }

    @Test
    void getStandardById() {
        Long id = 2L;
        Object queryResult = mapper.getStandardById(id);
        System.out.println("根据ID查询数据完成，查询结果：" + queryResult);
    }

}