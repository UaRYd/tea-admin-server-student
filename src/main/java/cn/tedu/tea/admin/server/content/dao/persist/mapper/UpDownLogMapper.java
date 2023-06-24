package cn.tedu.tea.admin.server.content.dao.persist.mapper;


import cn.tedu.tea.admin.server.content.pojo.entity.UpDownLog;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.UpDownLogStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UpDownLogMapper extends BaseMapper<UpDownLog> {
    UpDownLogStandardVO getStandardById(Long id);
}
