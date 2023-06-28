package cn.tedu.tea.admin.server.content.dao.persist.mapper;


import cn.tedu.tea.admin.server.content.pojo.entity.Comment;
import cn.tedu.tea.admin.server.content.pojo.vo.list.CommentListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.CommentStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    CommentStandardVO getStandardById(Long id);

}
