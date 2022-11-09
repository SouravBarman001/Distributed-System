package socket_programming.oneway;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

//socket have input port and output port
public class Client {

    public void showInfo() throws IOException {
        System.out.println("Client one---------");
        String ip = "localhost";
        int port = 3000; // (0-1023) reserved port ->> 65525
        Socket s = new Socket(ip,port);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        String str = scanner.nextLine();

        DataOutputStream os = new DataOutputStream(s.getOutputStream()); // destination address printer,monitor,socket
        //PrintWriter pw = new PrintWriter(os);
        os.writeUTF(str);
        os.flush();

//        DataInputStream br= new DataInputStream(s.getInputStream());
//        //    String str = String.valueOf(br.read());
//        System.out.println("Client data from server:"+br.readUTF());
    }
    public static void main(String[] args) throws IOException {

        Client objOne = new Client();
        objOne.showInfo();





    }
}
