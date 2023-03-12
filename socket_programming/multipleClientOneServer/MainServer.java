//package socket_programming.multipleClientOneServer;
//
//import java.io.DataInputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//
//public class MainServer {
//    public static void main(String[] args) throws Exception{
//        System.out.println("Server class run");
//        while (true){
//            ServerSocket serverSocket = new ServerSocket(4000);
//            Socket socket = serverSocket.accept();
//            System.out.println("Client is connected");
//            ServerThread serverThread = new ServerThread(socket);
//            serverThread.start();
//        }
//    }
//}
//class ServerThread extends Thread{
//Socket socket  =  null;
//String line = null;
//    ServerThread(Socket socket){
//this.socket = socket;
//    }
//   public void run() {
//        try{
//            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
//            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
//            line = dataInputStream.readUTF();
//          while (line.compareTo("Quite")!=0){
//              printWriter.println(line);
//              printWriter.flush();
//              System.out.println("response: "+line);
//          }
//              dataInputStream.close();
//              printWriter.close();
//              socket.close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//   }
//
//}