package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.content.pojo.param.CommentAddNewParam;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ICommentService {
    void addNew(CommentAddNewParam commentAddNewParam);
}
