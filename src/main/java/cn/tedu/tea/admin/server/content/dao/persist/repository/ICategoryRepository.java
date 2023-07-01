package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.content.pojo.entity.Category;
import cn.tedu.tea.admin.server.content.pojo.vo.list.CategoryListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.CategoryStandardVO;

import java.util.Collection;
import java.util.List;

public interface ICategoryRepository {
    int insert(Category category);

    //int insertBatch(List<Category> categoryList);

    //int deleteById(Long id);

    //int deleteByIds(Collection<Long> idList);

    int updateById(Category category);

    //int count();

    //int countByName(String name);

    //int countByParentId(Long parentId);

    CategoryStandardVO getStandardById(Long id);

    List<CategoryListItemVO> list();

    //PageData<CategoryListItemVO> list(Integer pageNum, Integer pageSize);

    //PageData<CategoryListItemVO> listByParentId(Long parentId, Integer pageNum, Integer pageSize);
}
