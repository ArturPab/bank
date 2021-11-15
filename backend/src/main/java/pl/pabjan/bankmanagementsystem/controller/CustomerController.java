package pl.pabjan.bankmanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pabjan.bankmanagementsystem.mapper.dto.CustomerResponse;
import pl.pabjan.bankmanagementsystem.service.CustomerService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(customerService.findById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return status(HttpStatus.OK).body(customerService.findAll());
    }

    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<CustomerResponse> findByLastname(@PathVariable String lastname) {
        return status(HttpStatus.OK).body(customerService.findByLastName(lastname));
    }
}
