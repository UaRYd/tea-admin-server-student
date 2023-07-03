package cn.tedu.tea.admin.server.content.dao.persist.mapper;


import cn.tedu.tea.admin.server.content.pojo.entity.Category;
import cn.tedu.tea.admin.server.content.pojo.vo.list.CategoryListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.CategoryStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    CategoryStandardVO getStandardById(Long id);

    List<CategoryListItemVO> list();
}
