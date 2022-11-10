package LabExamOne.Q2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(4001);
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

                    InetAddress myip = InetAddress.getLocalHost();
//                    if(myip.getHostAddress().equals("16.0.0.40")){
//                        if (serverMsg%2==0){
//                            objectOutputStream.writeObject("client one sends: even");
//                        }
//                        else {
//
//                        }
//                    }

                    System.out.println(myip.getHostAddress());







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
