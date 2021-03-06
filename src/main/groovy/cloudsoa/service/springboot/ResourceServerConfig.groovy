package cloudsoa.service.springboot

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

@Configuration
@EnableResourceServer
//@EnableOAuth2Resource - deprecated in Spring Cloud 1.1, see https://github.com/spring-cloud/spring-cloud-security
class ResourceServerConfig extends ResourceServerConfigurerAdapter  {

    @Value('${security.oauth2.resource.jwt.serviceId}')
    String resourceId

    @Autowired
    JwtAccessTokenConverter jwtTokenEnhancer

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // Force incoming tokens to have this particular "audience" claim.
        // This disqualifies tokens issued for another target service
        resources.resourceId(resourceId);

        DefaultAccessTokenConverter accessTokenConverter = jwtTokenEnhancer.getAccessTokenConverter()
        accessTokenConverter.setUserTokenConverter(new AzureUserAuthenticationConverter())
    }
}
