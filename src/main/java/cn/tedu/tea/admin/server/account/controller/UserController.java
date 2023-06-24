package cn.tedu.tea.admin.server.account.controller;

import cn.tedu.tea.admin.server.account.pojo.param.UserAddNewParam;
import cn.tedu.tea.admin.server.account.pojo.param.UserLoginInfoParam;
import cn.tedu.tea.admin.server.account.service.IUserService;
import cn.tedu.tea.admin.server.common.web.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/account/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public JsonResult login(@Validated UserLoginInfoParam userLoginInfoParam) {
        log.debug("开始处理【用户登录】的请求，参数：{}", userLoginInfoParam);
        userService.login(userLoginInfoParam);
        return JsonResult.ok();
    }

    @PostMapping("/logout")
    public JsonResult logout() {
        log.debug("开始处理【用户退出登录】的请求，无参数");
        SecurityContextHolder.clearContext();
        return JsonResult.ok();
    }

    @PostMapping("/addNew")
    public JsonResult addNew(@Validated UserAddNewParam userAddNewParam) {
        log.debug("开始处理【新增用户】的请求，参数：{}", userAddNewParam);
        userService.addNewUser(userAddNewParam);
        return JsonResult.ok();
    }

}
