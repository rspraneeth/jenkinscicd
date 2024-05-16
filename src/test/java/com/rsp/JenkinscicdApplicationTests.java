package com.rsp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@SpringBootTest
@WebMvcTest(JenkinscicdApplication.class)
class JenkinscicdApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired

    private MockMvc mockMvc;

    @Test
    public void testGreetEndPoint() throws Exception{
        String name = "rsp";
        mockMvc.perform(MockMvcRequestBuilders.get("/greetings/{name}", name))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Hello "+name+", Good day!"));
    }
}
