package pl.pabjan.bankmanagementsystem.mapper.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class CustomerRequest {

    @Email
    private String email;

    private String password;

    @Size(min = 2, max = 60)
    private String name;

    @Size(min = 2, max = 80)
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;


}
