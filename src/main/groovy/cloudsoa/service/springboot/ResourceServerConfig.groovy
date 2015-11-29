package cloudsoa.service.springboot

import org.springframework.cloud.security.oauth2.resource.EnableOAuth2Resource
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer

@Configuration
@EnableResourceServer
@EnableOAuth2Resource
class ResourceServerConfig {

}
