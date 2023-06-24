package cn.tedu.tea.admin.server.content.dao.persist.mapper;

import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.TagStandardVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TagMapperTests {

    @Autowired
    TagMapper mapper;

    @Test
    void insert() {
        Tag tag = new Tag();
        tag.setName("测试标签名称102");

        System.out.println("插入数据之前，参数：" + tag);
        int rows = mapper.insert(tag);
        System.out.println("插入数据完成，受影响的行数：" + rows);
        System.out.println("插入数据之后，参数：" + tag);
    }

    @Test
    void deleteById() {
        Long id = 1L;
        int rows = mapper.deleteById(id);
        System.out.println("删除数据完成，受影响的行数：" + rows);
    }

    @Test
    void deleteByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        int rows = mapper.deleteByMap(map);
        System.out.println("删除数据完成，受影响的行数：" + rows);
    }

    @Test
    void delete() {
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1);
        int rows = mapper.delete(wrapper);
        System.out.println("删除数据完成，受影响的行数：" + rows);
    }

    @Test
    void deleteBatchIds() {
        List<Long> idList = new ArrayList<>();
        idList.add(4L);
        idList.add(5L);
        idList.add(6L);

        int rows = mapper.deleteBatchIds(idList);
        System.out.println("删除数据完成，受影响的行数：" + rows);
    }

    @Test
    void updateById() {
        Tag tag = new Tag();
        tag.setId(9L);
        // tag.setName("测试标签名称998");
        tag.setEnable(1);
        tag.setSort(198);

        int rows = mapper.updateById(tag);
        System.out.println("修改数据完成，受影响的行数：" + rows);
    }

    @Test
    void update() {
        Tag tag = new Tag();
        tag.setEnable(1);
        tag.setSort(197);

        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("id", 1);

        int rows = mapper.update(tag, wrapper);
        System.out.println("修改数据完成，受影响的行数：" + rows);
    }

    @Test
    void selectCount() {
        Integer count = mapper.selectCount(null);
        System.out.println("统计数据完成，统计结果：" + count);
    }

    @Test
    void selectCountByName() {
        String name = "abcd";
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);

        Integer count = mapper.selectCount(wrapper);
        System.out.println("统计数据完成，统计结果：" + count);
    }

    @Test
    void getStandardById() {
        Long id = 6L;
        TagStandardVO queryResult = mapper.getStandardById(id);
        System.out.println("根据ID查询数据完成，查询结果：" + queryResult);
    }

    @Test
    void listTagType() {
        List<?> list = mapper.listTagType();
        System.out.println("查询列表完成，列表项的数量：" + list.size());
        for (Object item : list) {
            System.out.println("列表项：" + item);
        }
    }

    @Test
    void list() {
        List<?> list = mapper.list();
        System.out.println("根据列表数据完成，列表长度：" + list.size());
        for (Object item : list) {
            System.out.println("列表项：" + item);
        }
    }

}