package pl.pabjan.bankmanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import pl.pabjan.bankmanagementsystem.model.dto.CustomerResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void saveCustomerTest() throws Exception {
//        when
        mockMvc.perform(post("/api/customer/register")
                .content("{\"email\": \"testmvc@test.com\", \"password\": \"test\", \"name\": \"test\", \"lastname\": \"test\", \"dateOfBirth\": \"2000-05-09\"}")
                        .header("Content-Type", "application/json"))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().is(201))
                    .andReturn();


//        given
        MvcResult mvcResult = mockMvc.perform(post("/login")
                        .content("{\"email\": \"testmvc@test.com\", \"password\": \"test\"}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
//        then
        assertThat(mvcResult.getResponse().getContentAsString()).isNotNull();
    }

}