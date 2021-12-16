package pl.pabjan.bankmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pabjan.bankmanagementsystem.mapper.TransactionMapper;
import pl.pabjan.bankmanagementsystem.model.Customer;
import pl.pabjan.bankmanagementsystem.model.Transaction;
import pl.pabjan.bankmanagementsystem.model.dto.TransactionRequest;
import pl.pabjan.bankmanagementsystem.model.dto.TransactionResponse;
import pl.pabjan.bankmanagementsystem.repo.CustomerRepo;
import pl.pabjan.bankmanagementsystem.repo.TransactionRepo;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepo transactionRepo;
    private final TransactionMapper transactionMapper;
    private final CustomerService customerService;

//    finds transactions by current customer
    public List<TransactionResponse> findByCurrentCustomer() {
        Customer customer = customerService.getCurrentCustomer();
        List<Transaction> transactions = transactionRepo.findByCustomerId(customer.getId());

        return transactions.stream().map(transaction -> transactionMapper.mapToDto(transaction, customer)).collect(Collectors.toList());
    }

//    creates transaction
    public void createTransaction(TransactionRequest transactionRequest) {
        Customer customer = customerService.getCurrentCustomer();
        Customer recipient = customerService.findByAccountNumber(transactionRequest.getRecipientAccountNumber());
        transferFunds(customer, recipient, transactionRequest.getAmount());
        Transaction transaction = transactionMapper.map(transactionRequest, customer.getId());
        transactionRepo.save(transaction);
    }

//    transfers money from sender to recipient
    private void transferFunds(Customer customer, Customer recipient, BigDecimal amount) {
        customer.setBalance(customer.getBalance().subtract(amount));
        recipient.setBalance(recipient.getBalance().add(amount));
        customerService.editCustomerBalance(customer);
        customerService.editCustomerBalance(recipient);
    }
}
