package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.content.dao.persist.mapper.UpDownLogMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.IUpDownLogRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.UpDownLog;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.UpDownLogStandardVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UpDownLogRepositoryImpl implements IUpDownLogRepository {

    @Autowired
    private UpDownLogMapper upDownLogMapper;

   
}
