package cn.tedu.tea.admin.server.content.dao.persist.mapper;


import cn.tedu.tea.admin.server.content.pojo.entity.Tag;
import cn.tedu.tea.admin.server.content.pojo.vo.list.TagListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.list.TagTypeListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.TagStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper extends BaseMapper<Tag> {
    TagStandardVO getStandardById(Long id);

    List<TagTypeListItemVO> listTagType();

    List<TagListItemVO> list();
}
