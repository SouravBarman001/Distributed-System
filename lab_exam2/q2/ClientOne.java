package lab_exam2.q2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientOne {




    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        System.out.println("Client Started....");

        Socket socket = new Socket("127.0.0.1", 22234);
        System.out.println("Client Connected....");

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        while (true) {

            Scanner sc = new Scanner(System.in);
            String text = sc.nextLine();

            int numofArt = countArticles(text);

            oos.writeObject(text +"\n"+ "Number Of article present in the sentence= "+ numofArt);


            if (text.equals("exit")) break;
            String serverReply = (String) ois.readObject();


            System.out.println("From Server: " + serverReply);

        }
        socket.close();
    }


    public static int countArticles(String sentence) {


        Matcher m = Pattern.compile("(?i)\\b((a)|(an)|(the))\\b").matcher(sentence);
        int count = 0;
        while(m.find()){
            count++;
        }

        return count;
    }
}