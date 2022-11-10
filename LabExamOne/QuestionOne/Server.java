package LabExamOne.QuestionOne;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4000);
        System.out.println("Server Started..");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected..");

            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                while (true) {
                    //read from client...
                    Object cMsg = objectInputStream.readObject();
                    if(cMsg==null)
                        break;
                    System.out.println("From Client: " + (String) cMsg);


                    String serverMsg = (String) cMsg;

                    if(serverMsg.matches("[a-z]+")) {
                        objectOutputStream.writeObject(serverMsg);
                    }else {
                       break; //objectOutputStream.writeObject("Enter lowercase letter");
                    }

                }

            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
