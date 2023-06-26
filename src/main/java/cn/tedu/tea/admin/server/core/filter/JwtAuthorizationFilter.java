package cn.tedu.tea.admin.server.core.filter;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    public JwtAuthorizationFilter() {
        log.debug("创建过滤器对象：JwtAuthorizationFilter");
    }

    public static final Integer JWT_MIN_LENGTH = 113;

    @Value("${tea-store.jwt.key}")
    String key;

    @Value("${tea-store.jwt.duration")
    String duration;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.debug("JwtAuthorizationFilter 开始过滤请求");
        /* 逻辑处理开始 */
          // 1、获取 Header 中 jwt
        String jwt = httpServletRequest.getHeader("Authorization");

          // 2、验证 jwt 是否符合标准
        if (!StringUtils.hasText(jwt) || jwt.length() < JWT_MIN_LENGTH) {
            log.warn("jwt 数据不合法，将进入下一阶段过滤器");
            // 放行
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }

          // 3、解析 JWT 数据
        Jwt result = Jwts.parser().setSigningKey(key).parse(jwt);
        Claims claims = (Claims) result.getBody();

          // 4、获取账号 id
        Long id = claims.get("id", Long.class);

          // 5、获取账号用户名
        String username = claims.get("username", String.class);

          // 6、获取账号权限
        String authoritiesString = claims.get("authorities", String.class);
            // 6.1、打包账号权限
        List<? extends GrantedAuthority> authorities = JSON.parseArray(authoritiesString, GrantedAuthority.class);

          // 7、合并到 Authentication
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

          // 8、将Authentication对象存入到SecurityContext中
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        /* 逻辑处理结束 */

        // 放行
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}