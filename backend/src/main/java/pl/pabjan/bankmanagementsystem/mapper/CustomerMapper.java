package pl.pabjan.bankmanagementsystem.mapper;

import org.springframework.stereotype.Component;
import pl.pabjan.bankmanagementsystem.mapper.dto.CustomerRequest;
import pl.pabjan.bankmanagementsystem.mapper.dto.CustomerResponse;
import pl.pabjan.bankmanagementsystem.model.Customer;

import java.time.Instant;

@Component
public class CustomerMapper {

    public CustomerResponse mapToDto(Customer customer) {

        return new CustomerResponse(
                customer.getEmail(),
                customer.getName(),
                customer.getLastname(),
                customer.getDateOfBirth(),
                customer.getBankAccountNumber(),
                customer.getCreated(),
                customer.isEnabled()
        );
    }

    public Customer map(CustomerRequest customerRequest) {
        Customer customer = new Customer();
        customer.setEmail(customerRequest.getEmail());
        customer.setName(customerRequest.getName());
        customer.setLastname(customerRequest.getLastName());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());
        customer.setBankAccountNumber(generateAccountNumber());
        customer.setEnabled(false);
        customer.setCreated(Instant.now());

        return customer;
    }

    private String generateAccountNumber() {
        String randomNumber = getGeneratedRandomNumber();
        return "4811110000" + randomNumber;
    }

    private String getGeneratedRandomNumber() {
        long randomNumber = 1000000000000000L + (long) (Math.random() * (9999999999999999L-1000000000000000L));
        return String.valueOf(randomNumber);
    }
}
