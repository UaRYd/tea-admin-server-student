package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.util.PageInfoToPageDataConverter;
import cn.tedu.tea.admin.server.content.dao.persist.mapper.ArticleMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.IArticleRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.Article;
import cn.tedu.tea.admin.server.content.pojo.vo.list.ArticleListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.ArticleStandardVO;
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
public class ArticleRepositoryImpl implements IArticleRepository {

    @Autowired
    private ArticleMapper articleMapper;

    public ArticleRepositoryImpl() {
        log.info("创建存储库对象：ArticleRepositoryImpl");
    }

    @Override
    public int insert(Article article) {
        log.debug("开始向【文章表】中插入数据：{}", article);
        return articleMapper.insert(article);
    }

}
