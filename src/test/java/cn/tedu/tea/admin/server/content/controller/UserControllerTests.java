package cn.tedu.tea.admin.server.content.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@SpringBootTest
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"})
@Sql(scripts = {"classpath:/sql/truncate_table.sql", "classpath:/sql/insert_data.sql"},
        executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc; // axios

    // RequestBuilder
    // MockMvc RequestBuilder s

    @Test
    void addNewUser() throws Throwable {
        String url = "/account/users/add-new";

        String username = "test1";
        String password = "123456";
        String nickname = "test1";
        String avatar = "test1";
        String phone = "test1";
        String email = "test1";
        String description = "test1";
        String enable = "1";

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .param("username", username)
                        .param("password", password)
                        .param("nickname", nickname)
                        .param("avatar", avatar)
                        .param("phone", phone)
                        .param("email", email)
                        .param("description", description)
                        .param("enable", enable)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

}
