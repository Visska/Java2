package java_2_homework_7.server;


import java_2_homework_7.auth.AuthenticationService;

public interface Server {
    void broadcastMessage(String message);
    boolean isLoggedIn(String nickname);
    void subscribe(ClientHandler client);
    void unsubscribe(ClientHandler client);
    AuthenticationService getAuthenticationService();
}
