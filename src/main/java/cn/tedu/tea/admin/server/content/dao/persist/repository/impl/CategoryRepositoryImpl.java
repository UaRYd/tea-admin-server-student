package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.content.dao.persist.mapper.CategoryMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.ICategoryRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Category;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.CategoryStandardVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CategoryRepositoryImpl implements ICategoryRepository {

    @Autowired
    private CategoryMapper categoryMapper;

    public CategoryRepositoryImpl() {
        log.info("创建存储库对象：CategoryRepositoryImpl");
    }

    @Override
    public int insert(Category category) {
        log.debug("开始向【类别表】中插入数据：{}", category);
        return categoryMapper.insert(category);
    }

    @Override
    public int updateById(Category category) {
        log.debug("开始执行【更新类别】的数据访问，参数：{}", category);
        return categoryMapper.updateById(category);
    }

    @Override
    public CategoryStandardVO getStandardById(Long id) {
        log.debug("开始执行【根据ID查询类别信息】的数据访问，参数：{}", id);
        return categoryMapper.getStandardById(id);
    }
}
