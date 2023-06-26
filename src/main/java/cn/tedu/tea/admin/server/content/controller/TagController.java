package cn.tedu.tea.admin.server.content.controller;

import cn.tedu.tea.admin.server.common.pojo.vo.PageData;
import cn.tedu.tea.admin.server.common.web.JsonResult;
import cn.tedu.tea.admin.server.content.pojo.param.TagAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.param.TagTypeAddNewParam;
import cn.tedu.tea.admin.server.content.pojo.param.TagUpdateInfoParam;
import cn.tedu.tea.admin.server.content.pojo.vo.list.TagListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.list.TagTypeListItemVO;
import cn.tedu.tea.admin.server.content.pojo.vo.standard.TagStandardVO;
import cn.tedu.tea.admin.server.content.service.ITagService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/content/tags")
@Api(tags = "2.1. 内容管理-标签管理")
@Validated
public class TagController {

    @Autowired
    private ITagService tagService;

    public TagController() {
        log.info("创建控制器对象：TagController");
    }

    /**
     * 为什么不加 @RequestBody ?
     *   添加 @RequestBody: 接收对象参数
     *   不添加 @RequestBody: 接收 FormData 参数
     */
    @ApiOperation("新增标签类别")
    @ApiOperationSupport(order = 100)
    @PostMapping("/type/add-new")
    @PreAuthorize("hasAuthority('/content/tag/add-new')")
    public JsonResult addNew(@Validated TagTypeAddNewParam tagTypeAddNewParam) {
        log.debug("开始处理【新增标签类别】的请求，参数：{}", tagTypeAddNewParam);
        tagService.addNew(tagTypeAddNewParam);
        return JsonResult.ok();
    }

    @ApiOperation("新增标签")
    @ApiOperationSupport(order = 110)
    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/content/tag/add-new')") // 执行方法之前检查权限
    // @PostAuthorize() // 执行方法之后检查权限
    public JsonResult addNew(@Validated TagAddNewParam tagAddNewParam) {
        log.debug("开始处理【新增标签】的请求，参数：{}", tagAddNewParam);
        tagService.addNew(tagAddNewParam);
        return JsonResult.ok();
    }

    @ApiOperation("删除标签")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签ID", required = true, dataType = "long")
    })
    @PostMapping("/{id:[0-9]+}/delete")
    @PreAuthorize("hasAuthority('/content/tag/delete')")
    public JsonResult delete(@PathVariable @Range(min = 1, message = "删除标签失败，请提交合法的 ID 值！") Long id) {
        log.debug("开始处理【删除标签】的请求，参数：{}", id);
        tagService.delete(id);
        return JsonResult.ok();
    }

    @ApiOperation("修改标签")
    @ApiOperationSupport(order = 300)
    @PostMapping("/{id:[0-9]+}/update/info")
    @PreAuthorize("hasAuthority('/content/tag/update')")
    public JsonResult updateInfoById(@Validated TagUpdateInfoParam tagUpdateInfoParam) {
        log.debug("开始处理【修改标签】的请求，参数：{}", tagUpdateInfoParam);
        tagService.updateInfoById(tagUpdateInfoParam);
        return JsonResult.ok();
    }

    @ApiOperation("根据 ID 查询标签")
    @ApiOperationSupport(order = 410)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签 ID", required = true, dataType = "Long")
    })
    @GetMapping("/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('/content/tag/read')")
    public JsonResult getStandardById(@PathVariable @Range(min = 1, message = "获取标签详情失败，请提交合法的 ID 值") Long id) {
        log.debug("开始处理【根据 ID 查询标签】的请求，参数：{}", id);
        TagStandardVO tag = tagService.getStandardById(id);
        return JsonResult.ok(tag);
    }

    @ApiOperation("查询标签类别列表")
    @ApiOperationSupport(order = 420)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "queryType", value = "查询类型，当需要查询全部数据时，此参数值应该是 all")
    })
    @GetMapping("/type/list")
    @PreAuthorize("hasAuthority('/content/tag/read')")
    public JsonResult listTagType(Integer page, String queryType) {
        log.debug("开始处理【查询标签类别列表】请求，页码：{}", page);
        if (page == null) {
            page = 1;
        }
        Integer pageNum = page > 0 ? page : 1;
        PageData<TagTypeListItemVO> pageData;
        if ("all".equals(queryType)) {
            pageData = tagService.listTagType(pageNum, Integer.MAX_VALUE);
        } else {
            pageData = tagService.listTagType(pageNum);
        }
        return JsonResult.ok(pageData);
    }

    @ApiOperation("查询标签列表")
    @ApiOperationSupport(order = 421)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", dataType = "int"),
            @ApiImplicitParam(name = "queryType", value = "查询类型，当需要查询全部数据时，此参数值应该是 all")
    })
    @GetMapping("")
    @PreAuthorize("hasAuthority('/content/tag/read')")
    public JsonResult list(Integer page, String queryType) {
        log.debug("开始处理【查询标签列表】请求，页码：{}", page);
        if (page == null) {
            page = 1;
        }
        Integer pageNum = page > 0 ? page : 1;
        PageData<TagListItemVO> pageData ;
        if ("all".equals(queryType)) {
            pageData = tagService.list(1, Integer.MAX_VALUE);
        } else {
            pageData = tagService.list(pageNum);
        }
        return JsonResult.ok(pageData);
    }

    @ApiOperation("启用标签")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签ID", required = true, dataType = "long")
    })
    @PostMapping("/{id:[0-9]+}/enable")
    @PreAuthorize("hasAuthority('/content/tag/update')")
    public JsonResult setEnable(@PathVariable @Range(min = 1, message = "启用标签失败，请提交合法的ID值！") Long id) {
        log.debug("开始处理【启用标签】的请求，参数：{}", id);
        tagService.setEnable(id);
        return JsonResult.ok();
    }

    @ApiOperation("禁用标签")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签ID", required = true, dataType = "long")
    })
    @PostMapping("/{id:[0-9]+}/disable")
    @PreAuthorize("hasAuthority('/content/tag/update')")
    public JsonResult setDisable(@PathVariable @Range(min = 1, message = "禁用标签失败，请提交合法的ID值！") Long id) {
        log.debug("开始处理【禁用标签】的请求，参数：{}", id);
        tagService.setDisable(id);
        return JsonResult.ok();
    }

    @ApiOperation("启用类别标签")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签ID", required = true, dataType = "long")
    })
    @PostMapping("/type/{id:[0-9]+}/enable")
    public JsonResult setTypeEnable(@PathVariable @Range(min = 1, message = "启用标签失败，请提交合法的ID值！") Long id) {
        log.debug("开始处理【启用标签】的请求，参数：{}", id);
        tagService.setTypeEnable(id);
        return JsonResult.ok();
    }

    @ApiOperation("禁用类别标签")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签ID", required = true, dataType = "long")
    })
    @PostMapping("/type/{id:[0-9]+}/disable")
    public JsonResult setTypeDisable(@PathVariable @Range(min = 1, message = "禁用标签失败，请提交合法的ID值！") Long id) {
        log.debug("开始处理【禁用标签】的请求，参数：{}", id);
        tagService.setTypeDisable(id);
        return JsonResult.ok();
    }
}
