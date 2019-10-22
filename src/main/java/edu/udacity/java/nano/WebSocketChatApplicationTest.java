package edu.udacity.java.nano;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WebSocketChatApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    private static final String MOCK_URL = "/index?username=stephanie";

    @Test
    public void login() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/login"));
    }

    @Test
    public void index() throws Exception {
        mockMvc.perform(get(MOCK_URL))
                .andExpect(status().isOk())
                .andExpect(view().name("/chat"))
                .andExpect(model().attribute("username", "stephanie"));
    }
}