package jwtrest;

import io.jsonwebtoken.ExpiredJwtException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.CallerPrincipal;
import javax.security.enterprise.credential.RememberMeCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import javax.security.enterprise.identitystore.RememberMeIdentityStore;

@ApplicationScoped
@Named
public class JWTRememberMeIdentityStore implements RememberMeIdentityStore {

    private static final Logger LOGGER = Logger.getLogger(JWTRememberMeIdentityStore.class.getName());

   // @Resource
    @Inject
    private TokenProvider tokenProvider;

    @Override
    public CredentialValidationResult validate(RememberMeCredential rememberMeCredential) {
        try {
            if (tokenProvider.validateToken(rememberMeCredential.getToken())) {
                JWTCredential credential = tokenProvider.getCredential(rememberMeCredential.getToken());
             System.out.println("JWTRememberMeIdentityStore - Validate Token and return validation result in remember me");
                return new CredentialValidationResult(credential.getPrincipal(), credential.getAuthorities());
            
            }
            // if token invalid, response with invalid result status
            return INVALID_RESULT;
        } catch (ExpiredJwtException eje) {
            LOGGER.log(Level.INFO, "Security exception for user {0} - {1}", new Object[]{eje.getClaims().getSubject(), eje.getMessage()});
            return INVALID_RESULT;
        }
    }

    @Override
    public String generateLoginToken(CallerPrincipal callerPrincipal, Set<String> groups) {
       System.out.println("JWTRememberMeIdentityStore-Generate Login Token");
        return tokenProvider.createToken(callerPrincipal.getName(), groups, true);
    }

    @Override
    public void removeLoginToken(String token) {
        // Stateless authentication means at server side we don't maintain the state
    }

}
