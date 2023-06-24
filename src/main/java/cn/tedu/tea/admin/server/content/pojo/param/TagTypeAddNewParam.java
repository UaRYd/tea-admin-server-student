package cn.tedu.tea.admin.server.content.pojo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 新增标签类型的参数类
 *
 * @author java@tedu.cn
 * @version 1.0
 */
@Data
public class TagTypeAddNewParam implements Serializable {

    /**
     * 标签类别名称
     */
    @NotNull(message = "请提交标签类别名称")
    @Pattern(regexp = "^[a-zA-Z\\u4e00-\\u9fa5]{2,10}$",
            message = "标签类别必须是2~10长度的字符组成，且不允许使用标点符号")
    @ApiModelProperty(value = "标签类别名称", required = true)
    private String name;
    /**
     * 是否启用，1=启用，0=未启用
     */
    @NotNull(message = "请提交标签类别是否启用")
    @Range(max = 1, message = "是否启用的值必须是0或1")
    @ApiModelProperty(value = "是否启用，1=启用，0=未启用", required = true, example = "1")
    private Integer enable;
    /**
     * 排序序号
     */
    @NotNull(message = "请提交标签类别排序序号")
    @Range(max = 99, message = "排序序号必须是0~99之间的值")
    @ApiModelProperty(value = "排序序号", required = true)
    private Integer sort;

}
