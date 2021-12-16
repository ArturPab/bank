package pl.pabjan.bankmanagementsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.pabjan.bankmanagementsystem.model.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    @Query("select distinct c from Customer c join fetch c.bankCard")
    List<Customer> findAllWithCard();

    List<Customer> findByLastname(String lastname);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByBankAccountNumber(String bankAccountNumber);
}
