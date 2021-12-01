package pl.pabjan.bankmanagementsystem.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.pabjan.bankmanagementsystem.exceptions.BankCustomerException;
import pl.pabjan.bankmanagementsystem.mapper.BankCardMapper;
import pl.pabjan.bankmanagementsystem.model.BankCard;
import pl.pabjan.bankmanagementsystem.model.Customer;
import pl.pabjan.bankmanagementsystem.model.dto.BankCardRequest;
import pl.pabjan.bankmanagementsystem.model.dto.BankCardResponse;
import pl.pabjan.bankmanagementsystem.repo.BankCardRepo;
import pl.pabjan.bankmanagementsystem.repo.CustomerRepo;

@Service
@AllArgsConstructor
public class BankCardService {
    private final BankCardRepo bankCardRepo;
    private final BankCardMapper bankCardMapper;
    private final CustomerRepo customerRepo;

    public BankCardResponse findByCurrentCustomer() {
        Customer currentCustomer = getCurrentCustomer();
        return bankCardMapper.mapToDto(currentCustomer.getBankCard());
    }

    @Transactional(readOnly = true)
    public Customer getCurrentCustomer() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return customerRepo.findByEmail(email).orElseThrow(() -> new BankCustomerException("Customer not found"));
    }

    public void createBankCard(BankCardRequest bankCardRequest) {
        Customer currentCustomer = getCurrentCustomer();
        BankCard bankCard = bankCardMapper.map(bankCardRequest, currentCustomer);
        bankCardRepo.save(bankCard);
        setCustomerCard(currentCustomer, bankCard);
    }

    @Transactional
    public void setCustomerCard(Customer currentCustomer, BankCard bankCard) {
        currentCustomer.setBankCard(bankCard);
        customerRepo.save(currentCustomer);
    }
}
