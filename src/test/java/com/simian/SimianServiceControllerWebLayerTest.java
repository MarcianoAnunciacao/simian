package com.simian;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SimianServiceControllerWebLayerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void whenTestApp_ThenValidResponse() throws Exception {
        List<String> dnas = Arrays.asList("CTAGAA", "CAGAGC", "TGGGGT", "AGAGGG", "CCACGG", "TCACTG");
        this.mockMvc.perform( MockMvcRequestBuilders
        .post("/simian")
                .contentType("application/json")
                .content(mapper.writeValueAsString(dnas)))
        .andExpect(status().isOk());
    }

    @Test
    public void whenTestApp_ThenReturnForbiddenResponse() throws Exception{
        List<String> dnas = Arrays.asList("CTAGAC", "CAGAGC", "TGTGAT", "AGAGTG", "CGACTG", "TCACTG");
        this.mockMvc.perform( MockMvcRequestBuilders
                .post("/simian")
                .contentType("application/json")
                .content(mapper.writeValueAsString(dnas)))
                .andExpect(status().isForbidden());
    }
}
