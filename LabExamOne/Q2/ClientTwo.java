package LabExamOne.Q2;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTwo {
    public static void main(String[] args) throws IOException {
        System.out.println("Client started..");
        Socket socket = new Socket("127.0.0.0", 4001);
        System.out.println("Client Connected..");

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

        while (true) {
            Scanner sc = new Scanner(System.in);
            String message = sc.nextLine();

            if (message.equals("Exit")) {
                break;
            }

            //sent to server...

            objectOutputStream.writeObject(message);



            try {
                //receive from server..
                Object fromServer = objectInputStream.readObject();
                System.out.println("From Server: " + (String) fromServer);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        socket.close();
    }
}
