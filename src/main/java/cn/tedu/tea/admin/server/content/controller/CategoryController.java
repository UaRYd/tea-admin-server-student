package cn.tedu.tea.admin.server.content.controller;

import cn.tedu.tea.admin.server.common.web.JsonResult;
import cn.tedu.tea.admin.server.content.pojo.param.CategoryAddNewParam;
import cn.tedu.tea.admin.server.content.service.ICategoryService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "类别管理")
@RequestMapping("/content/category")
@Validated
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/content/category/add-new')")
    @ApiOperation("添加类别")
    @ApiOperationSupport(order = 100)
    public JsonResult addNew(CategoryAddNewParam categoryAddNewParam) {
        categoryService.addNew(categoryAddNewParam);
        return JsonResult.ok();
    }
}
