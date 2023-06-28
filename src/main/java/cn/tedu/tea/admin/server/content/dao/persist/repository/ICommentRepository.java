package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.content.pojo.entity.Comment;
import cn.tedu.tea.admin.server.content.pojo.vo.list.CommentListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.CommentStandardVO;

import java.util.Collection;

public interface ICommentRepository {
    int insert(Comment comment);

}
