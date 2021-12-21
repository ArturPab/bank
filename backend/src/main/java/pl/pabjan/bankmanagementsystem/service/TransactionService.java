package pl.pabjan.bankmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.pabjan.bankmanagementsystem.exceptions.TransactionException;
import pl.pabjan.bankmanagementsystem.mapper.TransactionMapper;
import pl.pabjan.bankmanagementsystem.model.Customer;
import pl.pabjan.bankmanagementsystem.model.Transaction;
import pl.pabjan.bankmanagementsystem.model.dto.TransactionRequest;
import pl.pabjan.bankmanagementsystem.model.dto.TransactionResponse;
import pl.pabjan.bankmanagementsystem.repo.TransactionRepo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class TransactionService {

    private final TransactionRepo transactionRepo;
    private final TransactionMapper transactionMapper;
    private final CustomerService customerService;

//    finds transactions by current customer
    public List<TransactionResponse> findByCurrentCustomer() {
        Customer customer = customerService.getCurrentCustomer();
        List<Transaction> transactionsAsSender = transactionRepo.findByCustomerId(customer.getId());
        List<Transaction> transactionsAsRecipient = transactionRepo.findByRecipientAccountNumber(customer.getBankAccountNumber());
        Set<Long> customerIds = transactionsAsRecipient.stream().map(Transaction::getCustomerId).collect(Collectors.toSet());
        List<Customer> senders = customerService.findAllById(customerIds);
        List<TransactionResponse> transactionAsSenderResponse = transactionsAsSender.stream().map(transaction -> transactionMapper.mapToDto(transaction, customer)).collect(Collectors.toList());
        List<TransactionResponse> transactionAsRecipientResponse = transactionsAsRecipient.stream().map(transaction -> transactionMapper.mapToDto(transaction, senders)).collect(Collectors.toList());

        return Stream.concat(transactionAsRecipientResponse.stream(), transactionAsSenderResponse.stream()).collect(Collectors.toList());
    }

//    creates transaction
    public void createTransaction(TransactionRequest transactionRequest) throws TransactionException {
        Customer customer = customerService.getCurrentCustomer();
        Customer recipient = customerService.findByAccountNumber(transactionRequest.getRecipientAccountNumber());
        transferFunds(customer, recipient, transactionRequest.getAmount());
        Transaction transaction = transactionMapper.map(transactionRequest, customer.getId());
        transactionRepo.save(transaction);
    }

//    transfers money from sender to recipient
    private void transferFunds(Customer customer, Customer recipient, BigDecimal amount) throws TransactionException {
        if (!isAmountGreaterThanZero(amount)) {
            throw new TransactionException("Amount must be greater than 0!");
        }
        customer.setBalance(customer.getBalance().subtract(amount));
        recipient.setBalance(recipient.getBalance().add(amount));
        customerService.editCustomerBalance(customer);
        customerService.editCustomerBalance(recipient);

    }

    private boolean isAmountGreaterThanZero(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public TransactionResponse findById(Long id) throws TransactionException {
        Customer customer = customerService.getCurrentCustomer();
        return transactionMapper.mapToDto(transactionRepo.findById(id).orElseThrow(() -> new TransactionException("Transaction not found")), customer);
    }
}
