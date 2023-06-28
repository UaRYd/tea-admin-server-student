package cn.tedu.tea.admin.server.account.controller;

import cn.tedu.tea.admin.server.account.pojo.param.UserAddNewParam;
import cn.tedu.tea.admin.server.account.pojo.param.UserLoginInfoParam;
import cn.tedu.tea.admin.server.account.pojo.vo.UserStandardVO;
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

    @PostMapping("/delById")
    public JsonResult delById(Long id) {
        log.debug("开始处理【根据 ID 删除用户】的请求，参数：{}", id);
        userService.delById(id);
        return JsonResult.ok();
    }

    @PostMapping("/enableById")
    public JsonResult enableById(Long id) {
        log.debug("开始处理【根据 ID 开启用户】的请求，参数：{}", id);
        userService.enableById(id);
        return JsonResult.ok();
    }

    @PostMapping("/disableById")
    public JsonResult disableById(Long id) {
        log.debug("开始处理【根据 ID 删除用户】的请求，参数：{}", id);
        userService.disableById(id);
        return JsonResult.ok();
    }

    @PostMapping("/getStandardById")
    public JsonResult getStandardById(Long id) {
        log.debug("开始处理【根据 ID 查询用户】的请求，参数：{}", id);
        UserStandardVO userStandardVO = userService.getStandardById(id);
        return JsonResult.ok(userStandardVO);
    }
}
