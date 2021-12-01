package pl.pabjan.bankmanagementsystem.mapper;

import org.junit.jupiter.api.Test;
import pl.pabjan.bankmanagementsystem.model.BankCard;
import pl.pabjan.bankmanagementsystem.model.Customer;
import pl.pabjan.bankmanagementsystem.model.dto.BankCardRequest;
import pl.pabjan.bankmanagementsystem.model.dto.CustomerRequest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BankCardMapperTest {

    @Test
    void mapTestAndAssertCVC_And_BankCardNumberLength() {
//        when
        BankCardMapper bankCardMapper = new BankCardMapper();
        BankCardRequest bankCardRequest = new BankCardRequest();
        bankCardRequest.setPin("4444");
        CustomerMapper customerMapper = new CustomerMapper();

        CustomerRequest customerRequest = createCustomerRequest();
        Customer customer = customerMapper.map(customerRequest);

//        given
        BankCard bankCard = bankCardMapper.map(bankCardRequest, customer);

//        then
        assertEquals(16, bankCard.getCardNumber().length());
        assertEquals(3, bankCard.getCvc().length());
        assertTrue(bankCard.isEnabled());
        assertEquals(bankCardRequest.getPin(), bankCard.getPin());
    }

    private CustomerRequest createCustomerRequest() {
        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setEmail("test@test.com");
        customerRequest.setLastname("test");
        customerRequest.setName("test");
        customerRequest.setDateOfBirth(LocalDate.parse("1999-01-01"));
        customerRequest.setPassword("test");
        return customerRequest;
    }
}