package pl.pabjan.bankmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pabjan.bankmanagementsystem.exceptions.BankCustomerException;
import pl.pabjan.bankmanagementsystem.mapper.CustomerMapper;
import pl.pabjan.bankmanagementsystem.mapper.dto.CustomerResponse;
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

    public List<CustomerResponse> findAll() {
        return customerRepo.findAll().stream().map(customerMapper::mapToDto).collect(Collectors.toList());
    }

    public CustomerResponse findByLastName(String lastname) {
        return customerMapper.mapToDto(customerRepo.findByLastname(lastname).orElseThrow(() -> new BankCustomerException("Customer not found")));
    }
}
