package com.ycyw.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Configuration du serveur WebSocket avec le protocole STOMP.
 *
 * - Active la prise en charge de la messagerie WebSocket côté Spring.
 * - Définit l'endpoint "/ws" que les clients Angular utilisent pour se
 * connecter.
 * - Autorise toutes les origines (utile pour le PoC entre localhost:4200 et
 * localhost:8080).
 * - Active un "broker" simple en mémoire pour gérer les topics (ici "/topic").
 * - Précise que les messages envoyés par le client doivent commencer par
 * "/app".
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * Déclare le point de terminaison STOMP "/ws".
     * Les clients Angular s’y connectent avec SockJS ou WebSocket.
     *
     * @param registry registre des endpoints STOMP
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*") // autorise toutes les origines (PoC)
                .withSockJS(); // fallback si WebSocket non disponible
    }

    /**
     * Configure le "message broker" :
     * - "/app" : préfixe des destinations pour les messages envoyés par les
     * clients.
     * - "/topic" : préfixe utilisé pour les topics de diffusion aux abonnés.
     *
     * @param registry registre du broker de messages
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }
}
