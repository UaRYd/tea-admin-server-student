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

        CategoryStandardVO parentCategoryStandardVO = null;
        if (parentId != 0) {
            parentCategoryStandardVO = categoryRepository.getStandardById(parentId);
            if (parentCategoryStandardVO == null) {
                String message = "指定父级不存在，请查证后再添加！";
                log.debug(message);
                throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
            } else {
                depth = parentCategoryStandardVO.getDepth() + 1;
            }
        }

        Category category = new Category();
        BeanUtils.copyProperties(categoryAddNewParam, category);
        category.setDepth(depth);
        category.setIsParent(0);
        categoryRepository.insert(category);

        if (parentId != 0 && parentCategoryStandardVO.getIsParent() == 0) {
            parentCategoryStandardVO.setIsParent(1);
            BeanUtils.copyProperties(parentCategoryStandardVO, category);
            categoryRepository.updateById(category);
        }
    }

}
