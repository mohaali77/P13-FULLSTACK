# Chat PoC – YourCarYourWay

## 📌 Description
Preuve de concept (PoC) d’un **chat en ligne client ↔ support** en temps réel.  
Le but est de démontrer la faisabilité technique de la fonctionnalité.

## 🛠️ Technologies utilisées
- **Back-end** : Spring Boot 3 (Web, WebSocket, Security)  
- **Front-end** : Angular 17 (standalone) + STOMP.js (WebSocket)  

## 🚀 Lancement

### Back-end
```bash
cd chat-poc-backend
mvn spring-boot:run
# Démarre sur http://localhost:8080
```

### Front-end
```bash
cd chat-poc-frontend
npm install
npm start
# Ouvre http://localhost:4200
```

Ensuite, ouvrir deux onglets sur le front, envoyer un message → il apparaît en temps réel avec une réponse automatique du bot.
