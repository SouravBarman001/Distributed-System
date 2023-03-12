package TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class ClientTwo {
    public static void main(String[] args) throws IOException {
        System.out.println("Client-Two started..");
        Socket socket = new Socket("127.0.0.1", 32341);
        System.out.println("Client-Two Connected..");

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

                String[] words = serverMsg.split(" ");

                int counter = 0;

                for (int i = 0; i < words.length; i++) {
                    char firstChar = words[i].charAt(0);

                    // check if first character
                    // of word starts with either
                    // upper or lower case vowels
                    if (firstChar == 'A' || firstChar == 'E' || firstChar == 'I'
                            || firstChar == 'O' || firstChar == 'U'
                            || firstChar == 'a' || firstChar == 'e' || firstChar == 'i'
                            || firstChar == 'o' || firstChar == 'u') {
                        // increase the counter
                        counter++;
                        System.out.println(words[i]);

                    }
                }
                System.out.println("Number of Words Starting with vowel = " + counter);

             //   System.out.println("From Server: " + (String) fromServer);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        socket.close();

    }
}