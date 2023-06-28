package cn.tedu.tea.admin.server.account.service;

import cn.tedu.tea.admin.server.account.pojo.param.UserAddNewParam;
import cn.tedu.tea.admin.server.account.pojo.param.UserLoginInfoParam;
import cn.tedu.tea.admin.server.account.pojo.vo.UserStandardVO;

public interface IUserService {

    void login(UserLoginInfoParam userLoginInfoParam);

    void addNewUser(UserAddNewParam userAddNewParam);

    void delById(Long id);

    void enableById(Long id);

    void disableById(Long id);

    UserStandardVO getStandardById(Long id);
}
