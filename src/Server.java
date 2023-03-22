import java.net.*;
import java.io.*;

public class Server {

    public static void main(String args[]){

        try {
            ServerSocket server;
            Socket player1;
            Socket player2;

            server = new ServerSocket(6666);
            player1 = server.accept();
            player2 = server.accept();

            DataInputStream dis1 = new DataInputStream(player1.getInputStream());
            DataInputStream dis2 = new DataInputStream(player2.getInputStream());
            DataOutputStream dout1 = new DataOutputStream(player1.getOutputStream());
            DataOutputStream dout2 = new DataOutputStream(player2.getOutputStream());

            String message1 = (String)dis1.readUTF();
            String message2 = (String)dis2.readUTF();

            dout1.writeUTF(message2);
            dout1.flush();
            dout2.writeUTF(message1);
            dout2.flush();

            dout1.writeUTF("1");
            dout1.flush();
            dout2.writeUTF("2");
            dout2.flush();

            while (true) {
                message1 = (String)dis1.readUTF();
                message2 = (String)dis2.readUTF();
                dout1.writeUTF(message2);
                dout1.flush();
                dout2.writeUTF(message1);
                dout2.flush();

                if (message1.equals("Q") || message2.equals("Q")) {
                    server.close();
                }
                server.close();
            }
        } catch (Exception e){
            System.out.println("One of the users disconnected");
        }








    }
}