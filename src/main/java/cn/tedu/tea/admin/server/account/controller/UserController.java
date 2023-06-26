package cn.tedu.tea.admin.server.account.controller;

import cn.tedu.tea.admin.server.account.pojo.param.UserAddNewParam;
import cn.tedu.tea.admin.server.account.pojo.param.UserLoginInfoParam;
import cn.tedu.tea.admin.server.account.pojo.vo.UserListItemVO;
import cn.tedu.tea.admin.server.account.pojo.vo.UserStandardVO;
import cn.tedu.tea.admin.server.account.service.IUserService;
import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.web.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 处理用户相关请求的控制器类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/account/users")
@Validated
@Api(tags = "1.1. 用户管理模块")
public class UserController {

    @Autowired
    private IUserService userService;

    public UserController() {
        log.info("创建控制器对象：UserController");
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    @ApiOperationSupport(order = 10)
    public JsonResult login(@Validated UserLoginInfoParam userLoginInfoParam) {
        log.debug("开始处理【用户登录】的请求，参数：{}", userLoginInfoParam);
        String jwt = userService.login(userLoginInfoParam);
        return JsonResult.ok(jwt);
    }

    @PostMapping("/logout")
    @ApiOperation("用户退出登录")
    @ApiOperationSupport(order = 11)
    public JsonResult logout() {
        log.debug("开始处理【用户退出登录】的请求，无参数");
        SecurityContextHolder.clearContext();
        return JsonResult.ok();
    }

    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/account/user/add-new')")
    @ApiOperation("添加用户")
    @ApiOperationSupport(order = 100)
    public JsonResult addNew(@Valid UserAddNewParam userAddNewParam) {
        log.debug("开始处理【添加用户】的请求，参数：{}", userAddNewParam);
        userService.addNewUser(userAddNewParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/delete")
    @PreAuthorize("hasAuthority('/account/user/delete')")
    @ApiOperation("根据ID删除用户")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    })
    public JsonResult delete(@PathVariable Long id) {
        log.debug("开始处理【根据ID删除用户】的请求，参数：{}", id);
        userService.delete(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/enable")
    @PreAuthorize("hasAuthority('/account/user/update')")
    @ApiOperation("启用用户")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    })
    public JsonResult setEnable(@PathVariable Long id) {
        log.debug("开始处理【启用用户】的请求，参数：{}", id);
        userService.setEnable(id);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/disable")
    @PreAuthorize("hasAuthority('/account/user/update')")
    @ApiOperation("禁用用户")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    })
    public JsonResult setDisable(@PathVariable Long id) {
        log.debug("开始处理【禁用用户】的请求，参数：{}", id);
        userService.setDisable(id);
        return JsonResult.ok();
    }

    @ApiOperation("根据ID查询用户")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    })
    @GetMapping("/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('/account/user/read')")
    public JsonResult getStandardById(@PathVariable @Range(min = 1, message = "获取用户详情失败，请提交合法的ID值！") Long id) {
        log.debug("开始处理【根据ID查询用户】的请求，参数：{}", id);
        UserStandardVO tag = userService.getStandardById(id);
        return JsonResult.ok(tag);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('/account/user/read')")
    @ApiOperation("查询用户列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", dataType = "long")
    })
    public JsonResult list(Integer page) {
        log.debug("开始处理【查询用户列表】的请求，页码：{}", page);
        Integer pageNum = page == null ? 1 : page;
        PageData<UserListItemVO> pageData = userService.list(pageNum);
        return JsonResult.ok(pageData);
    }

}