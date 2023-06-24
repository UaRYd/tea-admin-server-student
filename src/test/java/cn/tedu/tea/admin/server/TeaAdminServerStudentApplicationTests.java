package cn.tedu.tea.admin.server;

import cn.tedu.tea.admin.server.content.dao.persist.mapper.TagMapper;
import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class TeaAdminServerStudentApplicationTests {

    @Autowired
    TagMapper mapper;

    @Test
    void contextLoads() {

    }

    @Test
    void insert_tag() {
        Tag tag = new Tag();
        tag.setName("测试案例1");
        mapper.insert(tag);
    }

}
