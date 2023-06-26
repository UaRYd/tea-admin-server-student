package cn.tedu.tea.admin.server.account.service.impl;

import cn.tedu.tea.admin.server.account.dao.persist.repository.IRoleRepository;
import cn.tedu.tea.admin.server.account.pojo.vo.RoleListItemVO;
import cn.tedu.tea.admin.server.account.service.IRoleService;
import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 处理角色数据的业务实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {

    @Value("${tea-store.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;
    @Autowired
    private IRoleRepository roleRepository;

    public RoleServiceImpl() {
        log.debug("创建业务类对象：RoleServiceImpl");
    }

    @Override
    public PageData<RoleListItemVO> list(Integer pageNum) {
        log.debug("开始处理【查询角色列表】的业务，页码：{}", pageNum);
        PageData<RoleListItemVO> pageData = roleRepository.list(pageNum, defaultQueryPageSize);
        return pageData;
    }

    @Override
    public PageData<RoleListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始处理【查询角色列表】的业务，页码：{}，每页记录数：{}", pageNum, pageSize);
        PageData<RoleListItemVO> pageData = roleRepository.list(pageNum, pageSize);
        return pageData;
    }
}
