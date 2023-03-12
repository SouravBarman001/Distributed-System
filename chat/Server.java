package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    static HashMap<String, DataOutputStream> clients = new HashMap();

    public Server() {
    }

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(2774);
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Server started at  IP Adresse: " + ip);

        while(true) {
            Socket s = ss.accept();
            System.out.println("Socket accepted value: " + String.valueOf(s));
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            System.out.println("Creating thread for connected Client...");
            dos.writeUTF("Write you name:");
            String name = dis.readUTF();
            clients.put(name, dos);
            System.out.println(name + " is added as active client");
            sendActiveClients();
            (new Thread(() -> {
                while(true) {
                    try {
                        String received = dis.readUTF();
                        System.out.println(received);
                        if (received.equals("active clients")) {
                            sendActiveClients();
                        } else if (!received.equals("")) {
                            String[] parts = received.split(":");
                            String recipient = parts[0];
                            String message = parts[1];
                            DataOutputStream recipientDos = clients.get(recipient);
                            if (recipientDos != null) {
                                recipientDos.writeUTF(name + ": " + message);
                            } else {
                                dos.writeUTF("Error: sender is not found");
                            }
                        }
                    } catch (IOException e) {
                        clients.remove(name);
                        sendActiveClients();
                        return;
                    }
                }
            })).start();
        }
    }

    private static void sendActiveClients() {
        StringBuilder activeClients = new StringBuilder("Active Clients: ");

        for (String client : clients.keySet()) {
            activeClients.append(client).append(" ");
        }

        String activeClientsList = activeClients.toString();

        for (DataOutputStream dos : clients.values()) {
            try {
                dos.writeUTF(activeClientsList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}