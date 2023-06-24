package cn.tedu.tea.admin.server.account.dao.persist.repository;

import cn.tedu.tea.admin.server.account.pojo.entity.User;
import cn.tedu.tea.admin.server.account.pojo.param.UserAddNewParam;
import cn.tedu.tea.admin.server.account.pojo.vo.UserDetailsLoginInfoVO;

/**
 * 处理用户数据的存储库接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
public interface IUserDetailsRepository {

    /**
     * 根据用户名查询用户的登录信息
     *
     * @param username 用户名
     * @return 匹配的登录信息，如果没有匹配的数据，则返回null
     */
    UserDetailsLoginInfoVO getLoginInfoByUsername(String username);

    int addNewUser(User user);
}
