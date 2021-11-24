package pl.pabjan.bankmanagementsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pabjan.bankmanagementsystem.model.Customer;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    List<Customer> findByLastname(String lastname);
}
