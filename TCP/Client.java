package TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Client-One started..");
        Socket socket = new Socket("127.0.0.1", 32341);
        System.out.println("Client-One Connected..");

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        while (true) {
            Scanner sc = new Scanner(System.in);

            String message = sc.nextLine();

            if(message.equals("exit")){
                break;
            }

            //sent to server...
            oos.writeObject(message);

            try {
                //receive from server..
                Object fromServer = ois.readObject();
                String serverMsg = (String) fromServer;

//                String input = "A and Andy then the are a";
                Matcher m = Pattern.compile("(?i)\\b((a)|(an)|(the))\\b").matcher(serverMsg);
                int count = 0;
                while(m.find()){
                    count++;
                }
                System.out.println(serverMsg+" article count:"+count);



//                if (serverMsg.equals("a") || serverMsg.equals("an") || serverMsg.equals("the")){
//                    System.out.println("From Server: Article");
//                }


            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        socket.close();

    }
}