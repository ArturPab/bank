package pl.pabjan.bankmanagementsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

//Class with model of customer without id and password
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private String email;

    private String name;

    private String lastname;

    private LocalDate dateOfBirth;

    private String bankAccountNumber;

    private boolean enabled;

    private String token;
}
