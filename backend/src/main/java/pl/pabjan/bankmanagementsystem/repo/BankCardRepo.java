package pl.pabjan.bankmanagementsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.pabjan.bankmanagementsystem.model.BankCard;

@Repository
public interface BankCardRepo extends JpaRepository<BankCard, Long> {
}
