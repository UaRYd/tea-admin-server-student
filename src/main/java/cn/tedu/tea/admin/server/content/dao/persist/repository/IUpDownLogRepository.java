package cn.tedu.tea.admin.server.content.dao.persist.repository;

import cn.tedu.tea.admin.server.content.pojo.entity.UpDownLog;

public interface IUpDownLogRepository {

    int insert(UpDownLog upDownLog);

    int countByName(String name);
}
