package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.content.dao.persist.mapper.ArticleDetailMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.IArticleDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class ArticleDetailRepositoryImpl implements IArticleDetailRepository {

    @Autowired
    private ArticleDetailMapper articleDetailMapper;

    public ArticleDetailRepositoryImpl() {
        log.info("创建存储库对象：ArticleDetailRepositoryImpl");
    }

}
