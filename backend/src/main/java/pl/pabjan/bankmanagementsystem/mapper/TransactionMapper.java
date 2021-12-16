package pl.pabjan.bankmanagementsystem.mapper;

import org.springframework.stereotype.Component;
import pl.pabjan.bankmanagementsystem.model.Customer;
import pl.pabjan.bankmanagementsystem.model.Transaction;
import pl.pabjan.bankmanagementsystem.model.dto.TransactionRequest;
import pl.pabjan.bankmanagementsystem.model.dto.TransactionResponse;

import java.time.Instant;

@Component
public class TransactionMapper {
    public TransactionResponse mapToDto(Transaction transaction, Customer customer) {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setSenderName(customer.getName() + customer.getLastname());
        transactionResponse.setSenderBankNumber(customer.getBankAccountNumber());
        transactionResponse.setTitle(transaction.getTitle());
        transactionResponse.setRecipient(transaction.getRecipient());
        transactionResponse.setRecipientAccountNumber(transaction.getRecipientAccountNumber());
        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setDate(transaction.getDate());

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
