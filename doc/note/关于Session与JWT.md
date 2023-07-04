## 关于Session

服务器端的应用程序通常是基于HTTP协议的，HTTP协议本身是一种“无状态”协议，所以，它并不能保存客户端的状态，例如，无法识别客户端的身份，所以，即使同一个客户端多次访问同一个服务器，服务器并不能识别出它就是此前来访的客户端！

在开发实践中，大多是需要能够识别客户端身份的，通常可以使用Session机制来解决！

当某个客户端首次访问某个服务器端时，将直接发起请求，当服务器端收到此请求时，会在响应时返回一个Session ID值（本质上是一个UUID值），当客户端收到Session ID后，后续的访问都会自动携带此Session ID到服务器端，则服务器端可以根据这个Session ID值来识别客户端的身份。

在服务器端，使用K-V结构的数据表示Session，客户端携带的Session ID就是K-V结构中的Key，所以，每个客户端都可以访问到不同的Value，即每个客户端对应的Session数据。

Session是存储在服务器端的内存中的数据，而内存资源是相对有限的资源，存储空间相对较小，所以，必然存在清除Session的机制，默认的清除机制是“超时自动清除”，即某个客户端最后一次提交请求之后，在多长时间之内没有再次提交请求，服务器端就会清除此客户端对应的Session数据！至于过多久清除Session，没有明确的要求，大多软件的默认时间是15~30分钟，但是，也可以设置为更短或更长的时间。

基于Session的特征，必然存在一些不足：

- 不适合存储较大的数据
  - 可以通过规范的开发来避免此问题
- 不易于应用到集群或分布式系统中
  - 可以通过共享Session来解决此问题
- 不可以长时间存储数据
  - 无解



## Token

**Token**：票据，令牌

Token机制是用于解决服务器端识别客户端身份相关问题的。

当某个客户端向服务器端发起登录的请求时，将直接发起请求，当服务器端收到此请求时，如果判断登录成功，会在响应时返回一个Token值，当客户端收到Token后，后续的访问都会自动携带此Token到服务器端，则服务器端可以根据这个Token值来识别客户端的身份。

与Session不同，Token是由服务器端的程序（开发者自行编写的）生成的数据，此数据是一段有意义的数据，例如你可以把用户的ID、用户名都存放到Token中，则在后续的访问中，客户端携带了Token后，服务器端可以直接从Token中找到相关信息，例如用户的ID、用户名等等，从而，服务器端的内存中，并不需要持续的保存相关信息，所以，Token可以被设置一段非常长的有效期，并且不用担心持续性的消耗服务器端内存的问题。

基于Token的特征，它可以解决Session能解决的问题，并且，天生就适用于集群或分布式系统，只要集群或分布式系统中的各个服务器具有相同的检查Token和解析Token的程序即可。



## JWT

**JWT**：**J**SON **W**eb **T**oken

JWT的官网：https://jwt.io

每个JWT数据都是由3大部分组成的：

- Header：声明算法与Token类型
- Payload：数据
- Verify Signature：验证签名

在尝试生成和解析JWT之前，需要添加依赖项，可以是：

```xml
<!-- JJWT（Java JWT） -->
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.1</version>
</dependency>
```

关于JWT的测试使用：

```java
package cn.tedu.tea.admin.server;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTests {

    String secretKey = "fNesMDkqrJFdsfDSwAbFLJ8SnsHJ438AF72D73aKJSmfdsafdLKKAFKDSJ";

    @Test
    void generate() {
        // 注意：即使不知道正确的Secret Key，JWT数据也是可以被解析的，所以，不要在JWT中存放敏感数据
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 997);
        claims.put("username", "XiaoPangDun");

        Date date = new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000);
        //                                                  ↑ 注意加L，避免int溢出为负数

        String jwt = Jwts.builder()
                // Header
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                // Payload
                .setClaims(claims)
                .setExpiration(date)
                // Verify Signature
                .signWith(SignatureAlgorithm.HS256, secretKey)
                // Done
                .compact();
        System.out.println(jwt);
    }

    // eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTk3LCJleHAiOjE2ODc2NzUyNjYsInVzZXJuYW1lIjoiWGlhb1BhbmdEdW4ifQ.VxYYbbRYGaaetK3AipiPpkeyfOZBFZdyQVbhGMSFgCA

    // JWT过期的异常：io.jsonwebtoken.ExpiredJwtException
    // JWT签名错误的异常：io.jsonwebtoken.SignatureException
    // JWT格式错误的异常：io.jsonwebtoken.MalformedJwtException

    @Test
    void parse() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6OTk3LCJleHAiOjE2OTAyNzAyNjgsInVzZXJuYW1lIjoiWGlhb1BhbmdEdW4ifQ.CX7kdg7tzgbACvIyJ1bRu5Pe-2lS-Su5pvvNo3slrjM";

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
        Long id = claims.get("id", Long.class);
        String username = claims.get("username", String.class);
        System.out.println("id = " + id);
        System.out.println("username = " + username);
    }

}
```



## 在项目中使用JWT

在项目中使用JWT大致需要分为2个步骤：

- 当验证客户端的登录信息成功后，将生成JWT数据，并响应到客户端去，类似于“买票”的过程
- 客户端自主携带JWT来向服务器端发起请求，服务器端则需要尝试接收并尝试解析JWT，类似于“验票”的过程
  - 解析成功后，会得到JWT中的数据信息，需要将这些信息创建为`Authentication`对象并存入到`SecurityContext`中























