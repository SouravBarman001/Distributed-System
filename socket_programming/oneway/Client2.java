package socket_programming.oneway;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

//socket have input port and output port
public class Client2 {

    public void showInfo() throws IOException {
        System.out.println("Client two---------");

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
    }
    public static void main(String[] args) throws IOException {

        Client2 objOne = new Client2();
        objOne.showInfo();



    }
}
