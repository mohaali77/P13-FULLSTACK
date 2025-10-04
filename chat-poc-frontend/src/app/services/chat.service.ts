import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Client, IMessage } from '@stomp/stompjs';

/**
 * Représente un message de chat échangé entre un client et le support.
 */
export interface ChatMessage {
    sender: string;     // Nom de l'expéditeur
    content: string;    // Contenu du message
    timestamp: string;  // Date/heure d'envoi
}

@Injectable({ providedIn: 'root' })
export class ChatService {
    /** Instance STOMP qui gère la connexion WebSocket */
    private stomp?: Client;

    /** Flux de messages (observable) auquel les composants peuvent s'abonner */
    public messages$ = new BehaviorSubject<ChatMessage[]>([]);

    /**
     * Établit une connexion WebSocket avec le serveur via STOMP.
     * - Initialise le client STOMP.
     * - Souscrit au topic public pour recevoir les messages du serveur.
     * - Met à jour la liste des messages à chaque réception.
     */
    connect() {
        if (this.stomp) return; // évite de recréer une connexion

        this.stomp = new Client({
            brokerURL: 'ws://localhost:8080/ws/websocket', // URL WebSocket exposée par le backend
            reconnectDelay: 3000, // tente de se reconnecter automatiquement
            onConnect: () => {
                // S'abonne au topic pour recevoir les messages
                this.stomp?.subscribe('/topic/public', (msg: IMessage) => {
                    const body = JSON.parse(msg.body) as ChatMessage;
                    this.messages$.next([...this.messages$.value, body]);
                });
            }
        });

        this.stomp.activate(); // démarre la connexion
    }

    /**
     * Envoie un message au serveur via le canal STOMP.
     * @param sender   L’expéditeur du message (ex: "Client").
     * @param content  Le texte du message.
     */
    send(sender: string, content: string) {
        const payload: ChatMessage = {
            sender,
            content,
            timestamp: new Date().toISOString()
        };
        this.stomp?.publish({
            destination: '/app/chat.sendMessage',
            body: JSON.stringify(payload)
        });
    }
}
