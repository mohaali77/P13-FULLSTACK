package com.ycyw.chat.model;

/**
 * Représente un message échangé dans le chat entre un client et le support.
 * 
 * - Contient le nom de l'expéditeur, le contenu du message et l'horodatage.
 * - Utilisé par Spring pour convertir automatiquement les données JSON
 * 
 */
public class ChatMessage {
    private String sender; // Nom de l'expéditeur du message
    private String content; // Texte du message
    private String timestamp; // Date/heure d'envoi

    /**
     * Constructeur vide
     */
    public ChatMessage() {
    }

    /**
     * Constructeur pratique :
     * - Permet de créer un message directement en Java avec ses 3 champs.
     * 
     * @param sender    l'expéditeur
     * @param content   le contenu du message
     * @param timestamp la date/heure d'envoi
     */
    public ChatMessage(String sender, String content, String timestamp) {
        this.sender = sender;
        this.content = content;
        this.timestamp = timestamp;
    }

    // --- Getters et setters (utilisés par Spring/Jackson) ---
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
