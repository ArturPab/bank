package pl.pabjan.bankmanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import pl.pabjan.bankmanagementsystem.exceptions.TransactionException;
import pl.pabjan.bankmanagementsystem.model.Customer;
import pl.pabjan.bankmanagementsystem.model.dto.CustomerResponse;
import pl.pabjan.bankmanagementsystem.model.dto.TransactionRequest;
import pl.pabjan.bankmanagementsystem.service.CustomerService;
import pl.pabjan.bankmanagementsystem.service.TransactionService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "test@test.com", password = "tester")
class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerService customerService;

    @Transactional
    @Test
    void shouldCreateTransactionAndTransferFunds() throws Exception {

//        when
        Customer customer = customerService.getCurrentCustomer();
        BigDecimal balanceBeforeTransaction = customer.getBalance();
        BigDecimal transferAmount = BigDecimal.valueOf(10000);

        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(transferAmount);
        transactionRequest.setTitle("test");
        transactionRequest.setRecipient("test");
        transactionRequest.setRecipientAccountNumber("48111198549854985498549866");

//        given
        transactionService.createTransaction(transactionRequest);
        MvcResult mvcResult = mockMvc.perform(get("/api/customer"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andReturn();
//        then
        CustomerResponse customerResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), CustomerResponse.class);
        assertEquals(balanceBeforeTransaction.subtract(transferAmount), customerResponse.getBalance());

    }

    @Test
    void shouldCatchExceptionIfTransferAmountIsNotGreaterThanZero() {
        try {
            // when
            Customer customer = customerService.getCurrentCustomer();
            BigDecimal balanceBeforeTransaction = customer.getBalance();
            BigDecimal transferAmount = BigDecimal.valueOf(0);

            TransactionRequest transactionRequest = new TransactionRequest();
            transactionRequest.setAmount(transferAmount);
            transactionRequest.setTitle("test");
            transactionRequest.setRecipient("test");
            transactionRequest.setRecipientAccountNumber("48111198549854985498549866");

//          given
            transactionService.createTransaction(transactionRequest);
        } catch (TransactionException e) {
//            then
            assertEquals("Amount must be greater than 0!", e.getMessage());
        }

    }
}