package pl.pabjan.bankmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pabjan.bankmanagementsystem.exceptions.BankCustomerException;
import pl.pabjan.bankmanagementsystem.mapper.CustomerMapper;
import pl.pabjan.bankmanagementsystem.mapper.dto.CustomerRequest;
import pl.pabjan.bankmanagementsystem.mapper.dto.CustomerResponse;
import pl.pabjan.bankmanagementsystem.model.Customer;
import pl.pabjan.bankmanagementsystem.repo.CustomerRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;

    public CustomerResponse findById(Long id) {
        return customerMapper.mapToDto(customerRepo.findById(id).orElseThrow(() -> new BankCustomerException("Customer not found")));
    }

    public List<CustomerResponse> findAllWithCard() {
        return customerRepo.findAllWithCard().stream().map(customerMapper::mapToDto).collect(Collectors.toList());
    }

    public List<CustomerResponse> findByLastName(String lastname) {
        return customerRepo.findByLastname(lastname).stream().map(customerMapper::mapToDto).collect(Collectors.toList());
    }

//    saving customer account in database
    public void save(CustomerRequest customerRequest) {
        Customer customer = customerMapper.map(customerRequest);
        enableAccount(customer);
        customerRepo.save(customer);
    }

//    setting account to enabled
    private void enableAccount(Customer customer) {
        customer.setEnabled(true);
    }

}
