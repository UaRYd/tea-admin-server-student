package cn.tedu.tea.admin.server.account.service.impl;

import cn.tedu.tea.admin.server.account.dao.persist.repository.IUserDetailsRepository;
import cn.tedu.tea.admin.server.account.dao.persist.repository.IUserRoleRepository;
import cn.tedu.tea.admin.server.account.pojo.entity.User;
import cn.tedu.tea.admin.server.account.pojo.param.UserAddNewParam;
import cn.tedu.tea.admin.server.account.pojo.param.UserLoginInfoParam;
import cn.tedu.tea.admin.server.account.pojo.vo.UserListItemVO;
import cn.tedu.tea.admin.server.account.pojo.vo.UserStandardVO;
import cn.tedu.tea.admin.server.account.security.CustomUserDetails;
import cn.tedu.tea.admin.server.account.service.IUserService;
import cn.tedu.tea.admin.server.common.ex.ServiceException;
import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.web.ServiceCode;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    Map<String, Object> header = new HashMap();
    Map<String, Object> claims = new HashMap();

    @Value("${tea-store.dao.default-query-page-size}")
    private Integer defaultQueryPageSize;

    @Value("${tea-store.jwt.key}")
    private String key;

    @Value("${tea-store.jwt.durationInMinute}")
    private Long durationInMinute;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserDetailsRepository userDetailsRepository;

    @Autowired
    private IUserRoleRepository userRoleRepository;

    public UserServiceImpl() {
        log.info("创建业务对象：UserServiceImpl");
    }

    @Override
    public String login(UserLoginInfoParam userLoginInfoParam) {
        log.debug("开始处理【用户登录】的业务，参数：{}", userLoginInfoParam);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userLoginInfoParam.getUsername(), userLoginInfoParam.getPassword());
        log.debug("准备调用 AuthenticationManager 的认证方法，判断此用户名、密码是否可以成功登录……");
        log.debug("即将触发 UserDetailsService 组件类");
        Authentication authenticateResult
                = authenticationManager.authenticate(authentication);
        log.debug("UserDetailsService 组件类触发完毕");

        log.debug("验证用户登录成功，返回的认证结果：{}", authenticateResult);

        header.put("typ", "JWT");
        header.put("alg", "HS256");

        Object principal = authenticateResult.getPrincipal();
        CustomUserDetails userDetails = (CustomUserDetails) principal;
        
        Collection<? extends GrantedAuthority> authoritiesTemp = userDetails.getAuthorities();
        String authorities = JSON.toJSONString(authoritiesTemp);

        claims.put("id", userDetails.getId());
        claims.put("username", userDetails.getUsername());
        claims.put("avatar", userDetails.getAvatar());
        claims.put("authorities", authorities);

        Date date = new Date(System.currentTimeMillis() + 1L * 60 * 1000 * durationInMinute);

        String jwt = Jwts.builder()
                .setHeaderParams(header)
                .setClaims(claims)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        return jwt;

//        log.debug("准备将认证信息结果存入到 SecurityContext 中……");
//
//        log.debug("验证用户登录成功，准备将认证信息结果存入到 SecurityContext 中");
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        securityContext.setAuthentication(authenticateResult);
//        log.debug("已经将认证信息存入到 SecurityContext 中，登录业务处理完成！");
    }

    @Override
    public void addNewUser(UserAddNewParam userAddNewParam) {
        log.debug("开始处理【用户登录】的业务，参数：{}", userAddNewParam);

        /* 逻辑判断环节开始 */

        /* 逻辑判断环节结束 */
        User user = new User();
        BeanUtils.copyProperties(userAddNewParam, user);

        /* 密码加密开始 */
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        /* 密码加密结束 */

        int rows = userDetailsRepository.addNewUser(user);
        if (rows != 1) {
            String message = "新增用户失败，服务器忙，请稍后再试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_INSERT, message);
        }
    }

    @Override
    public void delete(Long id) {
        log.debug("开始处理【根据ID删除用户】的业务，参数：{}", id);
        Object queryResult = userDetailsRepository.getStandardById(id);
        if (queryResult == null) {
            String message = "删除用户失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        log.debug("即将执行删除数据，参数：{}", id);
        int rows = userDetailsRepository.deleteById(id);
        if (rows != 1) {
            String message = "删除用户失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }

        log.debug("即将执行删除关联数据，参数：{}", id);
        rows = userRoleRepository.deleteByAdminId(id);
        if (rows < 1) {
            String message = "删除用户失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_DELETE, message);
        }
    }

    @Override
    public void setEnable(Long id) {
        updateEnableById(id, 1);
    }

    @Override
    public void setDisable(Long id) {
        updateEnableById(id, 0);
    }

    @Override
    public UserStandardVO getStandardById(Long id) {
        log.debug("开始处理【根据ID查询用户】业务，参数：{}", id);
        UserStandardVO currentUser = userDetailsRepository.getStandardById(id);
        if (currentUser == null) {
            String message = "获取用户详情失败，尝试访问的用户数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }
        return currentUser;
    }

    @Override
    public PageData<UserListItemVO> list(Integer pageNum) {
        log.debug("开始处理【查询用户列表】的业务，页码：{}", pageNum);
        PageData<UserListItemVO> pageData = userDetailsRepository.list(pageNum, defaultQueryPageSize);
        return pageData;
    }

    @Override
    public PageData<UserListItemVO> list(Integer pageNum, Integer pageSize) {
        log.debug("开始处理【查询用户列表】的业务，页码：{}，每页记录数：{}", pageNum, pageSize);
        PageData<UserListItemVO> pageData = userDetailsRepository.list(pageNum, pageSize);
        return pageData;
    }

    private void updateEnableById(Long id, Integer enable) {
        log.debug("开始处理【{}用户】的业务，ID：{}，目标状态：{}", ENABLE_TEXT[enable], id, enable);
        UserStandardVO queryResult = userDetailsRepository.getStandardById(id);
        if (queryResult == null) {
            String message = ENABLE_TEXT[enable] + "用户失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_NOT_FOUND, message);
        }

        if (queryResult.getEnable().equals(enable)) {
            String message = ENABLE_TEXT[enable] + "用户失败，当前用户已经处于"
                    + ENABLE_TEXT[enable] + "状态！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_CONFLICT, message);
        }

        User user = new User();
        user.setId(id);
        user.setEnable(enable);
        log.debug("即将修改数据，参数：{}", user);
        int rows = userDetailsRepository.updateById(user);
        if (rows != 1) {
            String message = ENABLE_TEXT[enable] + "用户失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERROR_UPDATE, message);
        }
    }
}
