package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.content.pojo.param.CategoryAddNewParam;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ICategoryService {
    void addNew(CategoryAddNewParam categoryAddNewParam);
}
