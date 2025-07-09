package heap.application.security.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import heap.application.security.exception.TokenAuthenticationException;
import heap.application.security.user.AuthUser;
import heap.application.user.Roles;
import io.github.cdimascio.dotenv.Dotenv;

@Service
public class JwtService {
    
    private static final String ROLES_CLAIM = "roles";

    private final Algorithm signingAlgorithm;

    public JwtService() {
        Dotenv env = Dotenv.load();
        String secretCode = env.get("SECRET_PASSCODE");
        if (secretCode == null) {
            throw new IllegalArgumentException("Code not found");
        }
        this.signingAlgorithm = Algorithm.HMAC256(secretCode);
    }
    
    /*
     * This function acts as the manual token manager. We do not need to store the 
     * token in a HashSet as we are generating it only when it is required
     */
    public AuthUser resolveJwtToken(String token) throws TokenAuthenticationException {
        try {
            JWTVerifier verifier = JWT.require(signingAlgorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            String userId = decodedJWT.getSubject();
            List<Roles> roles = decodedJWT.getClaim(ROLES_CLAIM).asList(Roles.class);

            return new AuthUser(userId, roles);

        } catch(JWTVerificationException e) {
            throw new TokenAuthenticationException(token);
        }
    }

    public String createJwtToken(AuthUser authUser) {
        /*
         * A token requires a start and end creation inorder to ensure
         * it doesnt break
         */
        long nowMilles = System.currentTimeMillis();
        Date now = new Date(nowMilles);
        long expMilles = System.currentTimeMillis() + 360000;
        Date exp = new Date(expMilles);

        List<String> roles = authUser.roles().stream()
                                            .map(Roles::name)
                                            .toList();

        return JWT.create()
                  .withSubject(authUser.userId())
                  .withClaim(ROLES_CLAIM, roles)
                  .withIssuedAt(now)
                  .withExpiresAt(exp)
                  .sign(signingAlgorithm);
    }


}
