package heap.application.controller;

import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import heap.application.security.dto.LoginDTO;
import heap.application.security.dto.TokenDTO;
import heap.application.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {
    private final AuthService authService;

    public AuthApiController(AuthService authService) {
        this.authService = authService;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/login")
    public TokenDTO login(@RequestBody LoginDTO loginDto) {
        return authService.login(loginDto);
    }
}
