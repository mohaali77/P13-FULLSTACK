package com.ycyw.chat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration de la sécurité Spring Security pour le PoC du chat.
 *
 * - Désactive la protection CSRF (inutile ici car pas de formulaire classique).
 * - Autorise toutes les requêtes sans authentification (plus simple pour le
 * PoC).
 * - Active l'authentification basique par défaut (non utilisée mais fournie par
 * Spring).
 *
 * Dans une application réelle, on remplacerait cette config par une sécurité
 * avec JWT/OAuth2.
 */
@Configuration
public class SecurityConfig {

    /**
     * Définit la chaîne de filtres de sécurité.
     *
     * @param http l'objet de configuration HttpSecurity fourni par Spring
     * @return la chaîne de filtres configurée
     * @throws Exception en cas de problème de configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Désactivation du CSRF (Cross-Site Request Forgery)
        http.csrf(csrf -> csrf.disable());

        // Toutes les requêtes sont autorisées sans authentification
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        // Authentification basique activée par défaut (mais inutile dans ce PoC)
        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
