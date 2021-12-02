package pl.pabjan.bankmanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pabjan.bankmanagementsystem.model.dto.BankCardRequest;
import pl.pabjan.bankmanagementsystem.model.dto.BankCardResponse;
import pl.pabjan.bankmanagementsystem.service.BankCardService;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/bank-card")
@AllArgsConstructor
public class BankCardController {

    private final BankCardService bankCardService;

    @GetMapping("/by-current-customer")
    public ResponseEntity<BankCardResponse> findByCurrentCustomer() {
        return status(HttpStatus.OK).body(bankCardService.findByCurrentCustomer());
    }

    @PostMapping("/create-card")
    public ResponseEntity<Void> createBankCard(@RequestBody BankCardRequest bankCardRequest) {
        bankCardService.createBankCard(bankCardRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
