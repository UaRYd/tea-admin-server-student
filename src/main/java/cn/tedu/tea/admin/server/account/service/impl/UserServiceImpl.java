package cn.tedu.tea.admin.server.account.service.impl;

import cn.tedu.tea.admin.server.account.dao.persist.mapper.UserDetailsMapper;
import cn.tedu.tea.admin.server.account.dao.persist.repository.IUserDetailsRepository;
import cn.tedu.tea.admin.server.account.pojo.entity.User;
import cn.tedu.tea.admin.server.account.pojo.param.UserAddNewParam;
import cn.tedu.tea.admin.server.account.pojo.param.UserLoginInfoParam;
import cn.tedu.tea.admin.server.account.service.IUserService;
import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.common.web.ServiceCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserDetailsRepository userDetailsRepository;

    public UserServiceImpl() {
        log.info("创建业务对象：UserServiceImpl");
    }

    @Override
    public void login(UserLoginInfoParam userLoginInfoParam) {
        log.debug("开始处理【用户登录】的业务，参数：{}", userLoginInfoParam);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userLoginInfoParam.getUsername(), userLoginInfoParam.getPassword());
        log.debug("准备调用 AuthenticationManager 的认证方法，判断此用户名、密码是否可以成功登录……");
        log.debug("即将触发 UserDetailsService 组件类");
        Authentication authenticateResult
                = authenticationManager.authenticate(authentication);
        log.debug("UserDetailsService 组件类触发完毕");

        log.debug("验证用户登录成功，返回的认证结果：{}", authenticateResult);

        log.debug("准备将认证信息结果存入到 SecurityContext 中……");

        log.debug("验证用户登录成功，准备将认证信息结果存入到 SecurityContext 中");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authenticateResult);
        log.debug("已经将认证信息存入到 SecurityContext 中，登录业务处理完成！");
    }

    @Override
    public void addNewUser(UserAddNewParam userAddNewParam) {
        log.debug("开始处理【用户登录】的业务，参数：{}", userAddNewParam);

        /* 逻辑判断环节开始 */

        /* 逻辑判断环节结束 */
        User user = new User();
        BeanUtils.copyProperties(userAddNewParam, user);
        int rows = userDetailsRepository.addNewUser(user);
        if (rows != 1) {
            String message = "新增用户失败，服务器忙，请稍后再试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }
}
