package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ClientTwo {
    public static String getCurrentTime() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh-mm-ss a, MMM dd, y");
        return dateFormat.format(currentDate);
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write dns server {IP}: ");
        String serverAddress = scanner.nextLine();
        Socket socket = new Socket(serverAddress, 2774);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        System.out.println(dis.readUTF());
        String name = scanner.nextLine();
        dos.writeUTF(name);
        System.out.println(dis.readUTF());
        (new Thread(() -> {
            while (true) {
                try {
                    String received = dis.readUTF();
                    System.out.println(received);
                } catch (IOException var2) {
                    return;
                }
            }
        })).start();


        while (true) {
            String message = scanner.nextLine();
            if (message.equals("active clients")) {
                System.out.println(dis.readUTF());
            } else {
                dos.writeUTF(message + " [ sent at - " + getCurrentTime() +" ]");
            }
        }

    }
}
