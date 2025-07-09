package heap.application.service;

import heap.application.security.dto.LoginDTO;
import heap.application.security.dto.TokenDTO;

public interface AuthService {
    public TokenDTO login(LoginDTO LoginDTO);
}
