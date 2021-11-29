package pl.pabjan.bankmanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pabjan.bankmanagementsystem.model.dto.CustomerRequest;
import pl.pabjan.bankmanagementsystem.model.dto.CustomerResponse;
import pl.pabjan.bankmanagementsystem.service.CustomerService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable Long id) {
        return status(HttpStatus.OK).body(customerService.findById(id));
    }

    @GetMapping("/all-with-card")
    public ResponseEntity<List<CustomerResponse>> findAllWithCard() {
        return status(HttpStatus.OK).body(customerService.findAllWithCard());
    }

    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<List<CustomerResponse>> findByLastname(@PathVariable String lastname) {
        return status(HttpStatus.OK).body(customerService.findByLastName(lastname));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> saveCustomer(@RequestBody CustomerRequest customerRequest) {
        customerService.save(customerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
