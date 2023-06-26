package cn.tedu.tea.admin.server.account.security;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class CustomUserDetails extends User {

    private Long id;
    private String avatar;


    public CustomUserDetails(Long id, String username, String password, String avatar, Boolean enable, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enable, true, true, true, authorities);
        this.id = id;
        this.avatar = avatar;
    }
}
