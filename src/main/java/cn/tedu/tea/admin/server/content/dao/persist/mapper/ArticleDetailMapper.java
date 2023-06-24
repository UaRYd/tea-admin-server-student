package cn.tedu.tea.admin.server.content.dao.persist.mapper;


import cn.tedu.tea.admin.server.content.pojo.entity.ArticleDetail;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.ArticleDetailStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDetailMapper extends BaseMapper<ArticleDetail> {
    ArticleDetailStandardVO getStandardById(Long id);
}
