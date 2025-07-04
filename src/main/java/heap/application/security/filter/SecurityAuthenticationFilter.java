package heap.application.security.filter;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import heap.application.security.authentication.UserAuthentication;
import heap.application.security.exception.TokenAuthenticationException;
import heap.application.security.service.JwtService;
import heap.application.security.user.AuthUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public SecurityAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal (
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{

        String authenticationHeader = request.getHeader("Authorization");

        if (authenticationHeader == null) {
            // Authentication token is not present, let's rely on anonymous authentication
            filterChain.doFilter(request, response);
            return;
        }

        
        try {
            String jwtToken = stripBearerPrefix(authenticationHeader);
            AuthUser authUser = jwtService.resolveJwtToken(jwtToken);

            UserAuthentication authentication = new UserAuthentication(authUser);

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);

            filterChain.doFilter(request, response);

        } catch(TokenAuthenticationException e) {
            throw new ServletException("Unable to get proper token", e);
        }
    }

    /*
     * Knowing that the Jwt Token has it's header being damn near irrelevant, we need to get
     * rid of it
     */
    String stripBearerPrefix(String token) throws TokenAuthenticationException{
        if (!token.startsWith("Bearer")) {
            throw new TokenAuthenticationException("Unsupported authentication scheme");
        }

        // here we remove the tag we dont care about and compare it when trying to log in
        return token.substring(7);
    }
}
