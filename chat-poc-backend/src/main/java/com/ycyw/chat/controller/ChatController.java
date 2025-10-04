package com.ycyw.chat.controller;

import com.ycyw.chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Contrôleur responsable de la gestion des messages du chat.
 * - Reçoit les messages envoyés par les clients via WebSocket/STOMP.
 * - Réémet ces messages à tous les abonnés du topic public.
 * - Ajoute une réponse automatique "SupportBot" pour prouver la faisabilité.
 */
@Controller
public class ChatController {

    /**
     * Outil fourni par Spring pour envoyer des messages vers les clients connectés
     */
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * Méthode appelée lorsqu’un message est envoyé sur la destination
     * "/app/chat.sendMessage".
     * 
     * @param message le message envoyé par le client
     */
    @MessageMapping("/chat.sendMessage")
    public void receiveMessage(@Payload ChatMessage message) {
        // Diffuse le message du client à tous les abonnés du topic "/topic/public"
        messagingTemplate.convertAndSend("/topic/public", message);

        // Génère une réponse automatique envoyée par un "bot"
        ChatMessage botReply = new ChatMessage(
                "SupportBot",
                "Merci pour votre message 👋 Un conseiller va vous répondre.",
                message.getTimestamp());

        // Diffuse aussi la réponse du bot
        messagingTemplate.convertAndSend("/topic/public", botReply);
    }
}
