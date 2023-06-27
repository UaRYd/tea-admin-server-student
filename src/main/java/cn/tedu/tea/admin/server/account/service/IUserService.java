package cn.tedu.tea.admin.server.account.service;

import cn.tedu.tea.admin.server.account.pojo.param.UserAddNewParam;
import cn.tedu.tea.admin.server.account.pojo.param.UserLoginInfoParam;
import cn.tedu.tea.admin.server.account.pojo.vo.UserListItemVO;
import cn.tedu.tea.admin.server.account.pojo.vo.UserLoginResultVO;
import cn.tedu.tea.admin.server.account.pojo.vo.UserStandardVO;
import cn.tedu.tea.admin.server.common.pojo.vo.PageData;

public interface IUserService {

    /**
     * 启用状态的显示文本
     */
    String ENABLE_TEXT[] = {"禁用", "启用"};

    UserLoginResultVO login(UserLoginInfoParam userLoginInfoParam);

    void addNewUser(UserAddNewParam userAddNewParam);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void delete(Long id);

    /**
     * 启用用户
     *
     * @param id 用户ID
     */
    void setEnable(Long id);

    /**
     * 禁用用户
     *
     * @param id 用户ID
     */
    void setDisable(Long id);

    /**
     * 根据ID查询用户
     *
     * @param id 用户ID
     * @return 匹配的用户信息
     */
    UserStandardVO getStandardById(Long id);

    /**
     * 查询用户列表，将使用默认的每页记录数
     *
     * @param pageNum 页码
     * @return 用户列表
     */
    PageData<UserListItemVO> list(Integer pageNum);

    /**
     * 查询用户列表
     *
     * @param pageNum  页码
     * @param pageSize 每页记录数
     * @return 用户列表
     */
    PageData<UserListItemVO> list(Integer pageNum, Integer pageSize);
}
