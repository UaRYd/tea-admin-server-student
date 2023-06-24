package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.content.pojo.param.ArticleAddNewParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class ArticleServiceTests {

    @Autowired
    IArticleService service;

    @Test
    @Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
    @Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addNew() {
        ArticleAddNewParam articleAddNewParam = new ArticleAddNewParam();
        articleAddNewParam.setAuthorId(1L);
        articleAddNewParam.setCategoryId(1L);
        articleAddNewParam.setTitle("大麦茶和麦芽茶的区别");

        try {
            service.addNew(articleAddNewParam);
            System.out.println("添加数据完成！");
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

}
