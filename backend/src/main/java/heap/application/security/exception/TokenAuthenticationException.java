package heap.application.security.exception;

import javax.naming.AuthenticationException;

public class TokenAuthenticationException extends AuthenticationException{
    public TokenAuthenticationException(String message) {
        super(message);
    }
}
