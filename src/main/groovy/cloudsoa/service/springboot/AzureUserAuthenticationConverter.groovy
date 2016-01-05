package cloudsoa.service.springboot

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter

class AzureUserAuthenticationConverter extends DefaultUserAuthenticationConverter {
    final String user_claim = "unique_name"

    @Override
    Authentication extractAuthentication(Map<String, ?> map) {
        if (map.containsKey(user_claim)) {
            String username = map.get(user_claim);
            def authorities = [ new SimpleGrantedAuthority("user") ]
            return new UsernamePasswordAuthenticationToken(username, "N/A", authorities);
        }
        return null;
    }
}
