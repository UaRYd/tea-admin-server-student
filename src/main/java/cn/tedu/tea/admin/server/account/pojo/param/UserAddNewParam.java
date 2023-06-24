package cn.tedu.tea.admin.server.account.pojo.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class UserAddNewParam implements Serializable {

    @NotNull(message = "请提交用户名")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,15}$",
            message = "用户名必须是4~15长度的字符组成，且不允许使用标点符号")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotNull(message = "请提交密码")
    @Pattern(regexp = "^.{4,15}$",
            message = "密码必须是4~15长度的字符组成")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    private String nickname;
    private String avatar;
    private String phone;
    private String email;
    private String description;
}
