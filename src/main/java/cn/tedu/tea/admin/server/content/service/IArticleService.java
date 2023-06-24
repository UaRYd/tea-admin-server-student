package cn.tedu.tea.admin.server.content.service;

import cn.tedu.tea.admin.server.content.pojo.param.ArticleAddNewParam;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IArticleService {
    void addNew(ArticleAddNewParam articleAddNewParam);
}
