package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.content.pojo.param.CommentAddNewParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class CommentServiceTests {

    @Autowired
    ICommentService service;

    @Test
    @Sql(scripts = {"classpath:/sql/truncate_table.sql",
            "classpath:/sql/insert_data.sql"})
    @Sql(scripts = "classpath:/sql/truncate_table.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void addNew() {
        CommentAddNewParam commentAddNewParam = new CommentAddNewParam();
        commentAddNewParam.setAuthorId(1L);
        commentAddNewParam.setArticleId(1L);
        commentAddNewParam.setContent("这篇文章真赞！");

        try {
            service.addNew(commentAddNewParam);
            System.out.println("添加数据完成！");
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

}
