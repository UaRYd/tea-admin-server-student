package cn.tedu.tea.admin.server.account.pojo.vo;

import lombok.Data;

@Data
public class UserLoginResultVO {
    private Long id;
    private String username;
    private String avatar;
    private String jwt;
}
