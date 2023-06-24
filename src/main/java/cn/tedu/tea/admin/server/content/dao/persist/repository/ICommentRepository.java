package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.content.pojo.entity.Comment;

public interface ICommentRepository {
    int insert(Comment comment);
}
