package pl.pabjan.bankmanagementsystem.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pabjan.bankmanagementsystem.model.dto.LoginRequest;

@RestController
public class AuthController {

    @PostMapping("/login")
    public void login(@RequestBody LoginRequest loginRequest) {
    }
}
