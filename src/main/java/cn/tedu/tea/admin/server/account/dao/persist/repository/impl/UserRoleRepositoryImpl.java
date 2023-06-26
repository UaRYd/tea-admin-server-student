package cn.tedu.tea.admin.server.account.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.account.dao.persist.mapper.UserRoleMapper;
import cn.tedu.tea.admin.server.account.dao.persist.repository.IUserRoleRepository;
import cn.tedu.tea.admin.server.account.pojo.entity.UserRole;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理用户与角色关联数据的数据访问实现类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class UserRoleRepositoryImpl implements IUserRoleRepository {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public UserRoleRepositoryImpl() {
        log.debug("创建存储库对象：AdminRoleRepositoryImpl");
    }

    @Override
    public int insertBatch(List<UserRole> userRoleList) {
        log.debug("开始执行【批量插入用户与角色的关联数据】的数据访问，参数：{}", userRoleList);
        return userRoleMapper.insertBatch(userRoleList);
    }

    @Override
    public int deleteByAdminId(Long adminId) {
        log.debug("开始执行【根据用户ID删除用户与角色的关联数据】的数据访问，参数：{}", adminId);
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", adminId);
        return userRoleMapper.delete(queryWrapper);
    }
}
