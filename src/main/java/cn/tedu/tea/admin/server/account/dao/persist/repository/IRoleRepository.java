package cn.tedu.tea.admin.server.account.dao.persist.repository;

import cn.tedu.tea.admin.server.account.pojo.vo.RoleListItemVO;
import cn.tedu.tea.admin.server.common.pojo.vo.PageData;

/**
 * 处理角色数据的数据访问接口
 *
 * @author java@tedu.cn
 * @version 1.0
 */
public interface IRoleRepository {

    /**
     * 查询角色列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 角色列表
     */
    PageData<RoleListItemVO> list(Integer pageNum, Integer pageSize);

}
