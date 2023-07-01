package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.content.pojo.param.CategoryAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.vo.CategoryTreeItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface ICategoryService {
    void addNew(CategoryAddNewParam categoryAddNewParam);

    List<CategoryTreeItemVO> listTree();
}
