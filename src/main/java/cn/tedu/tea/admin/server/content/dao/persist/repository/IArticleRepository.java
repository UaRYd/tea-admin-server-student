package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.content.pojo.entity.Article;
import cn.tedu.tea.admin.server.content.pojo.vo.list.ArticleListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.ArticleStandardVO;

import java.util.Collection;

public interface IArticleRepository {
    int insert(Article article);

}
