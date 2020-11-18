package java_2_homework_7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;
    private AtomicBoolean isAuth;
    private AtomicBoolean isDrop;
    private final Integer waitTimeAuth = 120;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            isDrop = new AtomicBoolean(false);
            isAuth = new AtomicBoolean(false);

            doListen();
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    public String getName() {
        return name;
    }

    private void doListen() {
        new Thread(() -> {
            try {
                waitAuth();
                doAuth();
                receiveMessage();
            } catch (Exception e) {
                System.out.println("Client exit: " + socket);
//                throw new RuntimeException("SWW", e);
            } finally {
                server.unsubscribe(this);
            }
        }).start();
    }

    private void waitAuth() {
        new Thread(() -> {
            int time = waitTimeAuth;
            int sleepTime = 30;
            while (!isAuth.get()) {

                if (time == waitTimeAuth) {
                    sendMessage("cmd auth: Need auth, type '-auth email pwd'");
                } else if (time <= 0) {
                    sendMessage("cmd auth: You have been unsubscribed from server");
                    isDrop.set(true);
                    try {
                        in.close();
                        out.close();
                        socket.close();
                        server.unsubscribe(this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                } else {
                    sendMessage("cmd auth: You have " + time + " seconds left for authorization");
                }
                try {
                    Thread.sleep(sleepTime * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time -= sleepTime;
            }
        }).start();
    }

    private void doAuth() {
        try {
            while (!isDrop.get()) {

                String credentials = in.readUTF();
                /**
                 * Input credentials sample
                 * "-auth n1@mail.com 1"
                 */
                if (credentials.startsWith("-auth")) {
                    /**
                     * After splitting sample
                     * array of ["-auth", "n1@mail.com", "1"]
                     */
                    String[] credentialValues = credentials.split("\\s");
                    server.getAuthenticationService()
                            .doAuth(credentialValues[1], credentialValues[2])
                            .ifPresentOrElse(
                                    user -> {
                                        if (!server.isLoggedIn(user.getNickname())) {
                                            sendMessage("cmd auth: Status OK");
                                            name = user.getNickname();
                                            isAuth.set(true);
                                            server.broadcastMessage(name + " is logged in.");
                                            server.subscribe(this);
                                        } else {
                                            sendMessage("Current user is already logged in.");
                                        }
                                    },
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            sendMessage("No a such user by email and password.");
                                        }
                                    }
                            );
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    /**
     * Receives input data from {@link ClientHandler#in} and then broadcast via {@link Server#broadcastMessage(String)}
     */
    private void receiveMessage() {
        try {
            while (!isDrop.get()) {
                String message = in.readUTF();
                if (message.equals("-exit")) {
                    return;
                }
                server.broadcastMessage(message);
            }
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientHandler that = (ClientHandler) o;
        return Objects.equals(server, that.server) &&
                Objects.equals(socket, that.socket) &&
                Objects.equals(in, that.in) &&
                Objects.equals(out, that.out) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(server, socket, in, out, name);
    }
}
