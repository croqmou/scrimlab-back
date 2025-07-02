package crm.personnal.scrimlab.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // autorise toutes les routes
                        .allowedOrigins("http://localhost:3000") // ton front
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // méthodes autorisées
                        .allowedHeaders("*") // autorise tous les headers
                        .allowCredentials(true); // si tu envoies des cookies / headers d’auth
            }
        };
    }
}