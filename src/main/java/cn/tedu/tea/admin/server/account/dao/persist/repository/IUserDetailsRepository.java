package cn.tedu.tea.admin.server.account.dao.persist.repository;

import cn.tedu.tea.admin.server.account.pojo.entity.User;
import cn.tedu.tea.admin.server.account.pojo.param.UserAddNewParam;
import cn.tedu.tea.admin.server.account.pojo.vo.UserDetailsLoginInfoVO;
import cn.tedu.tea.admin.server.account.pojo.vo.UserListItemVO;
import cn.tedu.tea.admin.server.account.pojo.vo.UserStandardVO;
import cn.tedu.tea.admin.server.common.pojo.vo.PageData;

import java.util.Collection;
import java.util.List;

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

    /**
     * 批量插入用户数据
     *
     * @param userList 若干个用户数据的集合
     * @return 受影响的行数
     */
    int insertBatch(List<User> userList);

    /**
     * 根据用户id删除用户数据
     *
     * @param id 用户id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 批量删除用户
     *
     * @param idList 需要删除的若干个用户的id
     * @return 受影响的行数
     */
    int deleteByIds(Collection<Long> idList);

    /**
     * 根据用户id修改用户的数据
     *
     * @param user 封装了用户id和新的数据的对象
     * @return 受影响的行数
     */
    int updateById(User user);

    /**
     * 统计用户数据的数量
     *
     * @return 用户数据的数量
     */
    int count();

    /**
     * 根据用户名统计用户数据的数量
     *
     * @param username 用户名
     * @return 匹配用户名的用户数据的数据
     */
    int countByUsername(String username);

    /**
     * 根据手机号码统计用户数据的数量
     *
     * @param phone 手机号码
     * @return 匹配手机号码的用户数据的数据
     */
    int countByPhone(String phone);

    /**
     * 根据电子邮箱统计用户数据的数量
     *
     * @param email 电子邮箱
     * @return 匹配电子邮箱的用户数据的数据
     */
    int countByEmail(String email);

    /**
     * 根据用户id查询用户数据详情
     *
     * @param id 用户id
     * @return 匹配的用户数据详情，如果没有匹配的数据，则返回null
     */
    UserStandardVO getStandardById(Long id);

    /**
     * 查询用户数据列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 用户数据列表
     */
    PageData<UserListItemVO> list(Integer pageNum, Integer pageSize);
}
