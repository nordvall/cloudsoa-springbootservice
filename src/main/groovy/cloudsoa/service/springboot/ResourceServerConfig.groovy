package cloudsoa.service.springboot

//import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter

@Configuration
@EnableResourceServer
//@EnableOAuth2Resource - deprecated in Spring Cloud 1.1, see https://github.com/spring-cloud/spring-cloud-security
class ResourceServerConfig {//extends ResourceServerConfigurerAdapter  {

    /*@Override
    public void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        // @formatter:off
        //resources.resourceId('http://mattiasnkuai.onmicrosoft.com/spring');
        // @formatter:on
    }*/
}
