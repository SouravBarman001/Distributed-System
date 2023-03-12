package lab_exam2.q2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientTwo {



    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("Client Started....");

        Socket socket = new Socket("127.0.0.1", 22234);
        System.out.println("Client Connected....");

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        while (true) {

            Scanner sc = new Scanner(System.in);
            String text = sc.nextLine();

            int numberOfvowel = countWordsStartingWithVowel(text);

            oos.writeObject(text +"\n"+ "Number Of word that started with Vowel = "+ numberOfvowel);


            if (text.equals("exit")) break;
            String serverReply = (String) ois.readObject();


            System.out.println("From Server: " + serverReply);
            System.out.println("\n");

        }
        socket.close();
    }


    public static int countWordsStartingWithVowel(String sentence) {


        String[] words = sentence.split(" ");

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

        return counter;

    }
}