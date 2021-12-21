package pl.pabjan.bankmanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import pl.pabjan.bankmanagementsystem.model.dto.BankCardRequest;
import pl.pabjan.bankmanagementsystem.model.dto.BankCardResponse;
import pl.pabjan.bankmanagementsystem.service.BankCardService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "test@test.com", password = "test")
class BankCardControllerTest {

    @Autowired
    private BankCardService bankCardService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Transactional
    void createBankCardAndFindByCurrentCustomerTest() throws Exception {
//        when
        BankCardRequest bankCardRequest = new BankCardRequest();
        bankCardRequest.setPin("1111");
        bankCardService.createBankCard(bankCardRequest);

//        given
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/bank-card/by-current-customer"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
//        then
        BankCardResponse bankCardResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), BankCardResponse.class);
        assertThat(bankCardResponse).isNotNull();
        assertEquals(bankCardRequest.getPin(), bankCardResponse.getPin());


    }
}