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

        String str = br.readUTF().toUpperCase();
        System.out.println("Uppper case :"+str);
        DataOutputStream os = new DataOutputStream(s.getOutputStream()); // destination address printer,monitor,socket
        //PrintWriter pw = new PrintWriter(os);
        os.writeUTF(str);
        os.flush();
    }
}
