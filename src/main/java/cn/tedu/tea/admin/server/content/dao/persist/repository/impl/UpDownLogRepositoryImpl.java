package cn.tedu.tea.admin.server.content.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.content.dao.persist.mapper.UpDownLogMapper;
import cn.tedu.tea.admin.server.content.dao.persist.repository.IUpDownLogRepository;
import cn.tedu.tea.admin.server.content.pojo.entity.UpDownLog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UpDownLogRepositoryImpl implements IUpDownLogRepository {

    @Autowired
    private UpDownLogMapper upDownLogMapper;

    @Override
    public int insert(UpDownLog upDownLog) {
        log.debug("开始向【顶踩日志表】中插入数据：{}", upDownLog);
        return upDownLogMapper.insert(upDownLog);
    }

    @Override
    public int countByName(String name) {
        log.debug("根据名称【{}】统计【顶踩日志表】中的数据的数量", name);
        QueryWrapper<UpDownLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return upDownLogMapper.selectCount(queryWrapper);
    }
}
