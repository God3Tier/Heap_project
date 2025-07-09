package heap.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import heap.application.dto.UserResponse;
import heap.application.dto.UserResponseWithCredentials;
import heap.application.security.dto.LoginDTO;
import heap.application.security.dto.TokenDTO;
import heap.application.security.exception.ApplicationAuthenticationException;
import heap.application.security.service.JwtService;
import heap.application.security.user.AuthUser;

@Service("AuthService")
public class AuthServiceImpl implements AuthService{

    private final UserReviewService userReviewService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(UserReviewService userReviewService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userReviewService = userReviewService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService; 
    }

    public TokenDTO login(LoginDTO loginDto) {

        UserResponseWithCredentials userCredentials = userReviewService.getUserCredentialsByUsername(loginDto.username());

        if (!passwordEncoder.matches(loginDto.password(), userCredentials.passwordHash())) {
            throw new ApplicationAuthenticationException("Password is incorrect");
        }

        // String token = UUID.randomUUID().toString();
        UserResponse userResponse = userCredentials.userResponse();
        AuthUser authUser = new AuthUser(userResponse.getId(), userResponse.getRoles());

        String jwtToken = jwtService.createJwtToken(authUser);

        return new TokenDTO(jwtToken);
    }

    // logout not necessary as it will just forget it 
    // public void logout(String DTO) {
    //     authUserCache.logout(DTO);
    // }

}
