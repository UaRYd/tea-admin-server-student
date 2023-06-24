package cn.tedu.tea.admin.server.account.dao.persist.repository.impl;

import cn.tedu.tea.admin.server.account.dao.persist.mapper.UserDetailsMapper;
import cn.tedu.tea.admin.server.account.dao.persist.repository.IUserDetailsRepository;
import cn.tedu.tea.admin.server.account.pojo.entity.User;
import cn.tedu.tea.admin.server.account.pojo.vo.UserDetailsLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Repository
public class UserDetailsRepositoryImpl implements IUserDetailsRepository {

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    public UserDetailsRepositoryImpl() {
        log.info("创建存储库对象：UserRepositoryImpl");
    }

    @Override
    public UserDetailsLoginInfoVO getLoginInfoByUsername(String username) {
        log.debug("开始执行【根据用户名查询用户的登录信息】，参数：{}", username);
        return userDetailsMapper.getLoginInfoByUsername(username);
    }

    @Override
    public int addNewUser(User user) {
        log.debug("开始执行【添加用户】，参数：{}", user);
        return userDetailsMapper.insert(user);
    }
}
