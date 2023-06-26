package cn.tedu.tea.admin.server;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class JWTTests {
    String key = "AAxs3s4vd5bf6gnh8uui,9i,l.LP-0,IJ8Uh7hgb6tfr54eSx3Wwza3wxsr5nhu8MJ,I";

    Map<String, Object> header = new HashMap<>();
    Map<String, Object> claims = new HashMap<>();

    @Test
    void generate() {
        header.put("alg", "HS256");
        header.put("typ", "JWT");

        claims.put("id", 997);
        claims.put("username", "XiaoPangDun");

        Date date = new Date(System.currentTimeMillis() + 60 * 60 * 1000);


        String jwt = Jwts.builder()
                // Header
                .setHeaderParams(header)

                // Payload
                .setClaims(claims)
                .setExpiration(date)

                // Verify Signature
                .signWith(SignatureAlgorithm.HS256, key)

                // Done
                .compact();

        System.out.println(jwt);
    }

    @Test
    void parse() {
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6OTk3LCJleHAiOjE2ODc3ODM2NjMsInVzZXJuYW1lIjoiWGlhb1BhbmdEdW4ifQ.34Ho_2Z7WhEztAxSj1lGhu1rLeSWUsvpOySQqdAgkEw";

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt)
                .getBody();

        String authorities1 = claims.get("authorities", String.class);
        String username = claims.get("username", String.class);
        String avatar = claims.get("avatar", String.class);
        Long id = claims.get("id", Long.class);

        System.out.println("authorities = " + JSON.parseArray(authorities1));
        System.out.println("username = " + username);
        System.out.println("avatar = " + avatar);
        System.out.println("id = " + id);

        List<SimpleGrantedAuthority> authorities = JSON.parseArray(authorities1, SimpleGrantedAuthority.class);
        for (SimpleGrantedAuthority authority:
             authorities) {
            System.out.println(authority);
        }
    }
}