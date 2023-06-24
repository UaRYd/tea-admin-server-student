package cn.tedu.tea.admin.server.content.dao.persist.mapper;

import cn.tedu.tea.admin.server.content.pojo.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ArticleMapperTests {

    @Autowired
    ArticleMapper mapper;

    @Test
    void insert() {
        Article article = new Article();
        article.setAuthorId(1L);
        article.setCategoryId(1L);
        article.setTitle("大麦茶和麦芽茶的区别");

        System.out.println("插入数据之前，参数：" + article);
        int rows = mapper.insert(article);
        System.out.println("插入数据完成，受影响的行数：" + rows);
        System.out.println("插入数据之后，参数：" + article);
    }

    @Test
    void getStandardById() {
        Long id = 1L;
        Object queryResult = mapper.getStandardById(id);
        System.out.println("根据ID查询数据完成，查询结果：" + queryResult);
    }

}