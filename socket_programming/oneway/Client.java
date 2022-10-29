package socket_programming.oneway;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
//socket have input port and output port
public class Client {
    public static void main(String[] args) throws IOException {
        String ip = "localhost";
        int port = 3000; // (0-1023) reserved port ->> 65525
        Socket s = new Socket(ip,port);

        String str = "sourav";

        DataOutputStream os = new DataOutputStream(s.getOutputStream()); // destination address printer,monitor,socket
        //PrintWriter pw = new PrintWriter(os);
        os.writeUTF(str);
        os.flush();


    }
}
