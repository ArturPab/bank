package pl.pabjan.bankmanagementsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.bankmanagementsystem.model.Transaction;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCustomerId(Long id);

    List<Transaction> findByRecipientAccountNumber(String bankAccountNumber);
}
