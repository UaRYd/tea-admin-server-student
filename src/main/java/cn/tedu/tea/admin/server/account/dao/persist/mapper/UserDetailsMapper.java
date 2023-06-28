package cn.tedu.tea.admin.server.account.dao.persist.mapper;

import cn.tedu.tea.admin.server.account.pojo.entity.User;
import cn.tedu.tea.admin.server.account.pojo.vo.UserDetailsLoginInfoVO;
import cn.tedu.tea.admin.server.account.pojo.vo.UserStandardVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 处理用户数据的Mapper接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Repository
public interface UserDetailsMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户的登录信息
     *
     * @param username 用户名
     * @return 匹配的登录信息，如果没有匹配的数据，则返回null
     */
    UserDetailsLoginInfoVO getLoginInfoByUsername(String username);

    UserStandardVO getStandardById(Long id);
}
