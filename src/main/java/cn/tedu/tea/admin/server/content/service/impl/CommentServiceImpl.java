package cn.tedu.tea.admin.server.content.service.impl;

import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.common.web.ServiceCode;
import cn.tedu.tea.admin.server.content.dao.persist.repository.ICommentRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Comment;
import cn.tedu.tea.admin.server.content.pojo.param.CommentAddNewParam;
import cn.tedu.tea.admin.server.content.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    public CommentServiceImpl() {
        log.info("创建业务对象：CommentServiceImpl");
    }

    @Override
    public void addNew(CommentAddNewParam commentAddNewParam) {
        log.debug("开始处理【新增评论】的业务，参数：{}", commentAddNewParam);

        Comment comment = new Comment();
        BeanUtils.copyProperties(commentAddNewParam, comment);
        int rows = commentRepository.insert(comment);
        if (rows != 1) {
            String message = "新增评论失败，服务器忙，请稍后再试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }
}
