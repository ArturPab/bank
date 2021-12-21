package pl.pabjan.bankmanagementsystem.mapper;

import org.springframework.stereotype.Component;
import pl.pabjan.bankmanagementsystem.exceptions.BankCustomerException;
import pl.pabjan.bankmanagementsystem.model.Customer;
import pl.pabjan.bankmanagementsystem.model.Transaction;
import pl.pabjan.bankmanagementsystem.model.dto.TransactionRequest;
import pl.pabjan.bankmanagementsystem.model.dto.TransactionResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
public class TransactionMapper {
//    maps transactions made by current customer as sender
    public TransactionResponse mapToDto(Transaction transaction, Customer customer) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setTitle(transaction.getTitle());
        transactionResponse.setAmount(BigDecimal.valueOf(0).subtract(transaction.getAmount()));
        transactionResponse.setDate(transaction.getDate());
        transactionResponse.setSenderName(customer.getName() + " " + customer.getLastname());
        transactionResponse.setRecipient(transaction.getRecipient());
        transactionResponse.setRecipientAccountNumber(transaction.getRecipientAccountNumber());
        transactionResponse.setId(transaction.getId());
        transactionResponse.setSenderBankNumber(customer.getBankAccountNumber());
        transactionResponse.setId(transaction.getId());

        return transactionResponse;
    }

//    maps transaction by current customer as recipient
    public TransactionResponse mapToDto(Transaction transaction, List<Customer> customers) {
        Customer sender = customers.stream().filter(customer -> customer.getId().equals(transaction.getCustomerId())).findFirst().orElseThrow(() -> new BankCustomerException("Customer not found"));
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setSenderBankNumber(sender.getBankAccountNumber());
        transactionResponse.setDate(transaction.getDate());
        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setTitle(transaction.getTitle());
        transactionResponse.setRecipient(transaction.getRecipient());
        transactionResponse.setSenderName(sender.getName() + " " + sender.getLastname());
        transactionResponse.setId(transaction.getId());
        transactionResponse.setRecipientAccountNumber(transaction.getRecipientAccountNumber());

        return transactionResponse;
    }

//    maps transaction request to transaction model
    public Transaction map(TransactionRequest transactionRequest, Long customerId) {
        Transaction transaction = new Transaction();
        transaction.setTitle(transactionRequest.getTitle());
        transaction.setRecipient(transactionRequest.getRecipient());
        transaction.setRecipientAccountNumber(transactionRequest.getRecipientAccountNumber());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDate(Instant.now());
        transaction.setCustomerId(customerId);

        return transaction;
    }
}
