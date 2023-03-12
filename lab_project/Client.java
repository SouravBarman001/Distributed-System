package lab_project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
        
    public Client() {
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte die DNS Adresse des Servers eingeben: ");
        String serverAddress = scanner.nextLine();
        Socket socket = new Socket(serverAddress, 2774);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        System.out.println(dis.readUTF());
        String name = scanner.nextLine();
        dos.writeUTF(name);
        System.out.println(dis.readUTF());
        (new Thread(() -> {
            while(true) {
                try {
                    String received = dis.readUTF();
                    System.out.println(received);
                } catch (IOException var2) {
                    return;
                }
            }
        })).start();

        while(true) {
            while(true) {
                String message = scanner.nextLine();
                if (message.equals("aktive clients")) {
                    dos.writeUTF(message);
                    System.out.println(dis.readUTF());
                } else {
                    dos.writeUTF(message);
                }
            }
        }
    }
}
