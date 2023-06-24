package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.content.pojo.param.TagAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.param.TagUpdateInfoParam;
import cn.tedu.tea.admin.server.content.pojo.vo.list.TagListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.list.TagTypeListItemVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class TagServicesTests {

    @Autowired
    ITagService service;

    @Test
    void addNew() {
        TagTypeAddNewParam tagTypeAddNewParam = new TagTypeAddNewParam();
        tagTypeAddNewParam.setName("茶叶标签test");
        tagTypeAddNewParam.setEnable(1);
        tagTypeAddNewParam.setSort(88);
        try {
            service.addNew(tagTypeAddNewParam);
            System.out.println("添加数据完成！");
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

    @Test
    void addNewTag() {
        TagAddNewParam tagAddNewParam = new TagAddNewParam();
        tagAddNewParam.setName("红茶");
        tagAddNewParam.setTypeId(1L);
        tagAddNewParam.setEnable(1);
        tagAddNewParam.setSort(88);

        try {
            service.addNew(tagAddNewParam);
            System.out.println("添加数据完成！");
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

    @Test
    void listTagType1() {
        Integer pageNum = 1;

        try {
            PageData<TagTypeListItemVO> pageData = service.listTagType(pageNum);
            System.out.println(pageData);
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

    @Test
    void listTagType2() {
        Integer pageNum = 1;
        Integer pageSize = 3;

        try {
            PageData<TagTypeListItemVO> pageData = service.listTagType(pageNum, pageSize);
            System.out.println(pageData);
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

    @Test
    void list1() {
        Integer pageNum = 1;

        try {
            PageData<TagListItemVO> pageData = service.list(pageNum);
            System.out.println(pageData);
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

    @Test
    void list2() {
        Integer pageNum = 1;
        Integer pageSize = 3;

        try {
            PageData<TagListItemVO> pageData = service.list(pageNum, pageSize);
            System.out.println(pageData);
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

    @Test
    void getStandardById() {
        Long id = 10L;

        try {
            Object queryResult = service.getStandardById(id);
            System.out.println("根据ID查询数据完成，结果：" + queryResult);
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }

    @Test
    void updateInfoById() {
        TagUpdateInfoParam tagUpdateInfoParam = new TagUpdateInfoParam();
        tagUpdateInfoParam.setId(8L);
        tagUpdateInfoParam.setName("新绿茶");
        tagUpdateInfoParam.setSort(88);

        try {
            service.updateInfoById(tagUpdateInfoParam);
            System.out.println("修改数据完成！");
        } catch (ServiceException e) {
            System.out.println(e.getServiceCode().getValue());
            System.out.println(e.getMessage());
        }
    }
}
