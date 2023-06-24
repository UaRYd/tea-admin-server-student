package cn.tedu.tea.admin.server;

import cn.tedu.tea.admin.server.content.dao.persist.mapper.TagMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PageHelperTests {

    @Autowired
    TagMapper mapper;

    @Test
    void listTagType() {
        Integer pageNum = 2; // 页码，从1开始顺序编号
        Integer pageSize = 2; // 每页多少条数据
        PageHelper.startPage(pageNum, pageSize); // 设计分页参数
            List<?> list = mapper.listTagType(); // 【注意】必须紧随“PageHelper.startPage()”之后，否则，可能产生线程安全问题
        System.out.println("查询列表完成，列表类型：" + list.getClass().getName());
        System.out.println(list);
        System.out.println("列表项的数量：" + list.size());
        for (Object item : list) {
            System.out.println("列表项：" + item);
        }

        System.out.println();
        PageInfo<?> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo);
    }

}
