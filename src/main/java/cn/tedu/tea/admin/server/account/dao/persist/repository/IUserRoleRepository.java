package cn.tedu.tea.admin.server.account.dao.persist.repository;

import cn.tedu.tea.admin.server.account.pojo.entity.UserRole;

import java.util.List;

/**
 * 处理用户角色数据的数据访问接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
public interface IUserRoleRepository {

    /**
     * 批量插入用户与角色的关联数据
     *
     * @param userRoleList 若干个用户与角色的关联数据的集合
     * @return 受影响的行数
     */
    int insertBatch(List<UserRole> userRoleList);

    /**
     * 根据用户id删除用户与角色的关联数据
     *
     * @param adminId 用户id
     * @return 受影响的行数
     */
    int deleteByUserId(Long adminId);
}
