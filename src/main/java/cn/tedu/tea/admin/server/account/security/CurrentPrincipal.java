package cn.tedu.tea.admin.server.account.security;

import lombok.Data;

@Data
public class CurrentPrincipal {
    public CurrentPrincipal() {
    }

    public CurrentPrincipal(String username, Long id) {
        this.username = username;
        this.id = id;
    }

    private String username;
    private Long id;
}
