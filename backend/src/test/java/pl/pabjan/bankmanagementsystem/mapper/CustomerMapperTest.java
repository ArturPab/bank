package pl.pabjan.bankmanagementsystem.mapper;

import org.junit.jupiter.api.Test;
import pl.pabjan.bankmanagementsystem.mapper.dto.CustomerRequest;
import pl.pabjan.bankmanagementsystem.model.Customer;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    @Test
    void generateBankAccountNumberTest() {
//        when
        CustomerMapper customerMapper = new CustomerMapper();

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setEmail("test@test.com");
        customerRequest.setLastName("test");
        customerRequest.setName("test");
        customerRequest.setDateOfBirth(LocalDate.parse("1999-01-01"));
//        given
        Customer customer = customerMapper.map(customerRequest);
//        then

        System.out.println(customer.getBankAccountNumber());
        assertEquals(26, customer.getBankAccountNumber().length());
    }
}