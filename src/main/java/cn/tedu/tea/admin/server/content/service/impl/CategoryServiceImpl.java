package cn.tedu.tea.admin.server.content.service.impl;

import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.common.web.ServiceCode;
import cn.tedu.tea.admin.server.content.dao.persist.repository.ICategoryRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Category;
import cn.tedu.tea.admin.server.content.pojo.param.CategoryAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.CategoryStandardVO;
import cn.tedu.tea.admin.server.content.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository categoryRepository;

    public CategoryServiceImpl() {
        log.info("创建业务对象：CategoryServiceImpl");
    }

    @Override
    public void addNew(CategoryAddNewParam categoryAddNewParam) {
        log.debug("开始处理【新增类别】的业务，参数：{}", categoryAddNewParam);

        Long parentId = categoryAddNewParam.getParentId();
        Integer depth = 1;
        CategoryStandardVO parentCategory = null;
        if (parentId != 0) {
            parentCategory = categoryRepository.getStandardById(parentId);
            if (parentCategory == null) {
                String message = "添加类别失败，父级类别不存在！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
            } else {
                depth = parentCategory.getDepth() + 1;
            }
        }

        Category category = new Category();
        BeanUtils.copyProperties(categoryAddNewParam, category);
        category.setDepth(depth);
        category.setIsParent(0);
        int rows = categoryRepository.insert(category);
        if (rows != 1) {
            String message = "新增类别失败，服务器忙，请稍后再试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }

        if (parentId != 0 && parentCategory.getIsParent() == 0) {
            Category updateParentCategory = new Category();
            updateParentCategory.setId(parentId);
            updateParentCategory.setIsParent(1);
            rows = categoryRepository.update(updateParentCategory);
            if (rows != 1){
                String message = "添加类别失败，服务器忙，请稍后再尝试！";
                log.warn(message);
                throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
            }
        }
    }

}
