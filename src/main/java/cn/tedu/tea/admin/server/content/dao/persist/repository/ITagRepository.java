package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import cn.tedu.tea.admin.server.content.pojo.vo.list.TagListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.list.TagTypeListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.TagStandardVO;

public interface ITagRepository {

    int insert(Tag tag);

    int countByName(String name);

    PageData<TagTypeListItemVO> listTagType(Integer pageNum, Integer pageSize);

    PageData<TagListItemVO> list(Integer pageNum, Integer pageSize);

    TagStandardVO getStandardById(Long id);

    int deleteById(Long id);

    int updateById(Tag tag);

    int countByNameAndNotId(Long id, String name);

    /**
     * 根据parentId修改启用状态
     *
     * @param parentId 标签类别ID
     * @param enable   新的启用状态
     * @return 受影响的行数
     */
    int updateEnableByParentId(Long parentId, Integer enable);
}
