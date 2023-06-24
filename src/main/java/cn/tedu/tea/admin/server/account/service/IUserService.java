package cn.tedu.tea.admin.server.account.service;

import cn.tedu.tea.admin.server.account.pojo.param.UserAddNewParam;
import cn.tedu.tea.admin.server.account.pojo.param.UserLoginInfoParam;

public interface IUserService {

    void login(UserLoginInfoParam userLoginInfoParam);

    void addNewUser(UserAddNewParam userAddNewParam);

}
