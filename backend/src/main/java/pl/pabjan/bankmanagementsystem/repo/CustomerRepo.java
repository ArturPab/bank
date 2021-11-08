package pl.pabjan.bankmanagementsystem.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pabjan.bankmanagementsystem.model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
