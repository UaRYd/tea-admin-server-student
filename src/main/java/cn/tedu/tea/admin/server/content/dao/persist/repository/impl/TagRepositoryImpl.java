package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.util.PageInfoToPageDataConverter;
import cn.tedu.tea.admin.server.content.dao.persist.mapper.TagMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.ITagRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import cn.tedu.tea.admin.server.content.pojo.vo.list.TagListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.list.TagTypeListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.TagStandardVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class TagRepositoryImpl implements ITagRepository {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public int insert(Tag tag) {
        log.debug("开始向【标签表】中插入数据：{}", tag);
        return tagMapper.insert(tag);
    }

    @Override
    public int deleteById(Long id) {
        log.debug("开始执行【根据ID删除标签数据】，参数：{}", id);
        return tagMapper.deleteById(id);
    }

    @Override
    public int updateById(Tag tag) {
        log.debug("开始执行【根据ID修改标签数据】，参数：{}", tag);
        return tagMapper.updateById(tag);
    }

    @Override
    public int countByName(String name) {
        log.debug("开始执行【根据名称\"{}\"统计标签表中数据的数量】，参数：", name);
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return tagMapper.selectCount(queryWrapper);
    }

    @Override
    public int countByNameAndNotId(Long id, String name) {
        log.debug("开始执行【统计匹配名称查不匹配ID的标签数量】，ID：{}，名称：{}", id, name);
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name).ne("id", id);
        return tagMapper.selectCount(queryWrapper);
    }

    @Override
    public TagStandardVO getStandardById(Long id) {
        log.debug("开始执行【根据ID查询标签】，参数：{}", id);
        return tagMapper.getStandardById(id);
    }

    @Override
    public PageData<TagTypeListItemVO> listTagType(Integer pageNum, Integer pageSize) {
        log.debug("开始执行【查询标签类别列表】，页码：{}，每页记录数：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<TagTypeListItemVO> tagTypeList = tagMapper.listTagType();
        PageInfo<TagTypeListItemVO> pageInfo = new PageInfo<>(tagTypeList);
        PageData<TagTypeListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

    @Override
    public PageData<TagListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始执行【查询标签列表】，页码：{}，每页记录数：{}", pageNum, pageSize);
        PageHelper.startPage(pageNum, pageSize);
        List<TagListItemVO> list = tagMapper.list();
        PageInfo<TagListItemVO> pageInfo = new PageInfo<>(list);
        PageData<TagListItemVO> pageData = PageInfoToPageDataConverter.convert(pageInfo);
        return pageData;
    }

    @Override
    public int updateEnableByParentId(Long parentId, Integer enable) {
        log.debug("开始执行【根据parentId修改启用状态】，parentId：{}，新的启用状态：{}", parentId, enable);
        Tag tag = new Tag();
        tag.setEnable(enable);
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        return tagMapper.update(tag, wrapper);
    }
}
