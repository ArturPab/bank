package pl.pabjan.bankmanagementsystem.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private String email;

    private String name;

    private String lastname;

    private LocalDate dateOfBirth;

    private Instant created;
}
