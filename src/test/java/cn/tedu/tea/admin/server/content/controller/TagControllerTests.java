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
public class TagControllerTests {

    @Autowired
    MockMvc mockMvc; // axios

    // RequestBuilder
    // MockMvc RequestBuilder s

    @Test
    void addNewTagType() throws Throwable {
        String url = "/content/tags/type/add-new";

        String name = "茶叶标签";
        String enable = "1";
        String sort = "88";

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .param("name", name)
                        .param("enable", enable)
                        .param("sort", sort)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void addNewTag() throws Throwable {
        String url = "/content/tags/add-new";

        String name = "茶叶标签";
        String parentId = "1";
        String enable = "1";
        String sort = "88";

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                        .param("name", name)
                        .param("enable", enable)
                        .param("sort", sort)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void listTagType() throws Throwable {
        String url = "/content/tags/type/list";

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void list() throws Throwable {
        String url = "/content/tags";

        mockMvc.perform(MockMvcRequestBuilders.get(url)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print());
    }

}
