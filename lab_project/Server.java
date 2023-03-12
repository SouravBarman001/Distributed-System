package lab_project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class Server {
    static HashMap<String, DataOutputStream> clients = new HashMap();

    public Server() {
    }

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(2774);
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Server laeuft auf folgender IP Adresse: " + ip);

        while(true) {
            Socket s = ss.accept();
            System.out.println("Neue Client Anfrage erhalten: " + String.valueOf(s));
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            System.out.println("Erstelle neuen Thread fuer diesen Client...");
            dos.writeUTF("Bitte Name eingeben:");
            String name = dis.readUTF();
            clients.put(name, dos);
            System.out.println("Fuege " + name + " zur Liste der aktiven Clients hinzu");
            sendActiveClients();
            (new Thread(() -> {
                while(true) {
                    try {
                        String received = dis.readUTF();
                        System.out.println(received);
                        if (received.equals("aktive clients")) {
                            sendActiveClients();
                        } else if (!received.equals("")) {
                            String[] parts = received.split(":");
                            String recipient = parts[0];
                            String message = parts[1];
                            DataOutputStream recipientDos = (DataOutputStream)clients.get(recipient);
                            if (recipientDos != null) {
                                recipientDos.writeUTF(name + ": " + message);
                            } else {
                                dos.writeUTF("Error: Empfaenger nicht gefunden");
                            }
                        }
                    } catch (IOException var8) {
                        clients.remove(name);
                        sendActiveClients();
                        return;
                    }
                }
            })).start();
        }
    }

    private static void sendActiveClients() {
        StringBuilder activeClients = new StringBuilder("Aktive Clients: ");
        Iterator var1 = clients.keySet().iterator();

        while(var1.hasNext()) {
            String client = (String)var1.next();
            activeClients.append(client + " ");
        }

        String activeClientsList = activeClients.toString();
        Iterator var7 = clients.values().iterator();

        while(var7.hasNext()) {
            DataOutputStream dos = (DataOutputStream)var7.next();

            try {
                dos.writeUTF(activeClientsList);
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }

    }
}