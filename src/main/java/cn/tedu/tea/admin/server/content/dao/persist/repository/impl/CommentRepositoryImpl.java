package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.util.PageInfoToPageDataConverter;
import cn.tedu.tea.admin.server.content.dao.persist.mapper.CommentMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.ICommentRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Comment;
import cn.tedu.tea.admin.server.content.pojo.vo.list.CommentListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.CommentStandardVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Slf4j
@Repository
public class CommentRepositoryImpl implements ICommentRepository {

    @Autowired
    private CommentMapper commentMapper;

    public CommentRepositoryImpl() {
        log.info("创建存储库对象：CommentRepositoryImpl");
    }

    @Override
    public int insert(Comment comment) {
        log.debug("开始向【评论表】中插入数据：{}", comment);
        return commentMapper.insert(comment);
    }

}
