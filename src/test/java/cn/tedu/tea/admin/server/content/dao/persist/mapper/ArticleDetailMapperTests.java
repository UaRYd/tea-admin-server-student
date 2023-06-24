package cn.tedu.tea.admin.server.content.dao.persist.mapper;

import cn.tedu.tea.admin.server.content.pojo.entity.ArticleDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ArticleDetailMapperTests {

    @Autowired
    ArticleDetailMapper mapper;

    @Test
    void insert() {
        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setArticleId(1L);
        articleDetail.setDetail("大麦茶和麦芽茶在加工方式、营养价值、核心特点上均有区别");

        System.out.println("插入数据之前，参数：" + articleDetail);
        int rows = mapper.insert(articleDetail);
        System.out.println("插入数据完成，受影响的行数：" + rows);
        System.out.println("插入数据之后，参数：" + articleDetail);
    }

}