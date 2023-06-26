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
        claims.put("username", "programmer");

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
        String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiYXZhdGFyIjoiaHR0cHM6Ly9pbWcxLmJhaWR1LmNvbS9pdC91PTE3MjAzMzc1NywyMzk4MjkwNzY3JmZtPTI1MyZmbXQ9YXV0byZhcHA9MTM4JmY9SlBFRz93PTUwMCZoPTUwMCIsImV4cCI6MTY4NzcwNDE1MCwiYXV0aG9yaXRpZXMiOiJbe1wiYXV0aG9yaXR5XCI6XCIvYWNjb3VudC91c2VyL2FkZC1uZXdcIn0se1wiYXV0aG9yaXR5XCI6XCIvYWNjb3VudC91c2VyL2RlbGV0ZVwifSx7XCJhdXRob3JpdHlcIjpcIi9hY2NvdW50L3VzZXIvcmVhZFwifSx7XCJhdXRob3JpdHlcIjpcIi9hY2NvdW50L3VzZXIvdXBkYXRlXCJ9LHtcImF1dGhvcml0eVwiOlwiL2NvbnRlbnQvY2F0ZWdvcnkvYWRkLW5ld1wifSx7XCJhdXRob3JpdHlcIjpcIi9jb250ZW50L2NhdGVnb3J5L2RlbGV0ZVwifSx7XCJhdXRob3JpdHlcIjpcIi9jb250ZW50L2NhdGVnb3J5L3JlYWRcIn0se1wiYXV0aG9yaXR5XCI6XCIvY29udGVudC9jYXRlZ29yeS91cGRhdGVcIn0se1wiYXV0aG9yaXR5XCI6XCIvY29udGVudC90YWcvYWRkLW5ld1wifSx7XCJhdXRob3JpdHlcIjpcIi9jb250ZW50L3RhZy9kZWxldGVcIn0se1wiYXV0aG9yaXR5XCI6XCIvY29udGVudC90YWcvcmVhZFwifSx7XCJhdXRob3JpdHlcIjpcIi9jb250ZW50L3RhZy91cGRhdGVcIn1dIiwidXNlcm5hbWUiOiJyb290In0.W1yCO-m1oRYdbvdB66Jx9_5iVosYhWd_O1f7OSHNxms";

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