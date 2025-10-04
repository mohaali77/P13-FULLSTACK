import { Component, OnInit } from '@angular/core';
import { ChatService } from '../../services/chat.service';
import { FormsModule } from '@angular/forms';
import { DatePipe, NgFor, AsyncPipe } from '@angular/common';

/**
 * Composant Angular qui représente le widget de chat affiché à l’écran.
 * - Permet à l’utilisateur de saisir et d’envoyer des messages.
 * - Affiche les messages reçus depuis le service ChatService.
 */
@Component({
  selector: 'app-chat-widget',
  standalone: true,
  imports: [FormsModule, DatePipe, NgFor, AsyncPipe], // ✅ modules Angular nécessaires
  templateUrl: './chat-widget.component.html',
  styleUrls: ['./chat-widget.component.scss']
})
export class ChatWidgetComponent implements OnInit {
  /** Nom de l’utilisateur (ici fixé en "Client" pour le PoC) */
  username = 'Client';

  /** Texte en cours de saisie dans l’input */
  draft = '';

  /** Injection du service qui gère la logique du chat */
  constructor(public chat: ChatService) { }

  /**
   * Méthode exécutée à l’initialisation du composant.
   * - Ouvre la connexion WebSocket via le service ChatService.
   */
  ngOnInit(): void {
    this.chat.connect();
  }

  /**
   * Envoie le message saisi :
   * - Vérifie que le champ n’est pas vide.
   * - Appelle la méthode `send()` du ChatService.
   * - Réinitialise le champ de saisie.
   */
  send() {
    const text = this.draft.trim();
    if (!text) return;
    this.chat.send(this.username, text);
    this.draft = '';
  }
}
