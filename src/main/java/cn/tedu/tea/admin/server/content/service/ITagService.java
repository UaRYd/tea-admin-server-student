package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.content.pojo.param.TagAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.param.TagUpdateInfoParam;
import cn.tedu.tea.admin.server.content.pojo.vo.list.TagListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.list.TagTypeListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.TagStandardVO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ITagService {
    String ENABLE_TEXT[] = {"禁用" , "启用"};

    void setEnable(Long id);

    void setDisable(Long id);

    void addNew(TagTypeAddNewParam tagTypeAddNewParam);

    void addNew(TagAddNewParam tagAddNewParam);

    PageData<TagTypeListItemVO> listTagType(Integer pageNum);

    PageData<TagTypeListItemVO> listTagType(Integer pageNum, Integer pageSize);

    PageData<TagListItemVO> list(Integer pageNum);

    PageData<TagListItemVO> list(Integer pageNum, Integer pageSize);

    void delete(Long id);

    TagStandardVO getStandardById(Long id);

    void updateInfoById(TagUpdateInfoParam tagUpdateInfoParam);

    void setTypeEnable(Long id);

    void setTypeDisable(Long id);
}
