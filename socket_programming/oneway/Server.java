package socket_programming.oneway;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server is started");
        ServerSocket ss= new ServerSocket(3000);
        System.out.println("Server is waiting for client request");
        Socket s = ss.accept();
        System.out.println("Client is connected");

        DataInputStream br= new DataInputStream(s.getInputStream());
    //    String str = String.valueOf(br.read());
        System.out.println("Client data :"+br.readUTF().toUpperCase());
    }
}
