package br.orfeu.music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class CorsConfig {

    @Value("${cors.allowed-origins}")
    private String[] allowedOrigins;

    @Value("${cors.allowed-methods}")
    private String[] allowedMethods;

    @Value("${cors.allowed-headers}")
    private String[] allowedHeaders;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // Configura as origens permitidas
        for (String origin : allowedOrigins) {
            config.addAllowedOrigin(origin);
        }
        
        // Configura os métodos HTTP permitidos
        for (String method : allowedMethods) {
            config.addAllowedMethod(method);
        }
        
        // Configura os cabeçalhos permitidos
        for (String header : allowedHeaders) {
            config.addAllowedHeader(header);
        }
        
        // Permite o envio de credenciais (cookies, autenticação HTTP, etc.)
        config.setAllowCredentials(true);
        
        // Aplica a configuração a todas as rotas
        source.registerCorsConfiguration("/**", config);
        
        return new CorsFilter(source);
    }
}
