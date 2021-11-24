package pl.pabjan.bankmanagementsystem.mapper;

import org.springframework.stereotype.Component;
import pl.pabjan.bankmanagementsystem.mapper.dto.CustomerResponse;
import pl.pabjan.bankmanagementsystem.model.Customer;

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
}
