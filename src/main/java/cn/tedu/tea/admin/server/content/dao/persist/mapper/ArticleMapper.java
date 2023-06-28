package cn.tedu.tea.admin.server.content.dao.persist.mapper;


import cn.tedu.tea.admin.server.content.pojo.entity.Article;
import cn.tedu.tea.admin.server.content.pojo.vo.list.ArticleListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.ArticleStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    ArticleStandardVO getStandardById(Long id);

}
