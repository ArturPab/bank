package pl.pabjan.bankmanagementsystem.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pabjan.bankmanagementsystem.exceptions.TransactionException;
import pl.pabjan.bankmanagementsystem.model.dto.TransactionRequest;
import pl.pabjan.bankmanagementsystem.model.dto.TransactionResponse;
import pl.pabjan.bankmanagementsystem.service.TransactionService;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/transactions")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> findByCurrentCustomer() {
        return status(HttpStatus.OK).body(transactionService.findByCurrentCustomer());
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionRequest transactionRequest) throws TransactionException {
        transactionService.createTransaction(transactionRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
