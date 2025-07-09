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
import jakarta.servlet.http.HttpServletRequest;

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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    // The bottom needs to be redone otherwise it is fucked
    // @SecurityRequirement(name = OpenApiConstants.TOKEN_SECURITY_REQUIREMENT)
    public void logout(HttpServletRequest httpServletRequest) {

        // this token needs to be changed later -> Just incase we need more constants
        String token = Optional.ofNullable(httpServletRequest.getHeader("Authorization"))
                .orElseThrow();

        // authService.logout(token);
    }
}
