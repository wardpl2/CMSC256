package Games.BattleshipWithGUI;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class SimpleServer {
    public static void main(String[] args) {
        int port = 1234;
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket s1 = ss.accept(); //client1
            Socket s2 = ss.accept(); //client2
            ObjectInputStream ois1 = new ObjectInputStream(s1.getInputStream()); //read a general object sent from client1
            ObjectInputStream ois2 = new ObjectInputStream(s2.getInputStream()); //read a general object sent from client2
            ObjectOutputStream oos1 = new ObjectOutputStream(s1.getOutputStream());
            ObjectOutputStream oos2 = new ObjectOutputStream(s2.getOutputStream());
            ArrayList<String[]> al1 = (ArrayList<String[]>) ois1.readObject(); //create the ArrayList of String arrays from client1
            ArrayList<String[]> al2 = (ArrayList<String[]>) ois2.readObject(); //create the ArrayList of String arrays from client2
            oos1.writeObject(al2); //send client2's ArrayList to client1
            oos2.writeObject(al1); //send client1's ArrayList to client2
            //print client1 ArrayList
            for (String[] S : al1) {
                System.out.println(Arrays.toString(S));
            }
            System.out.println("\n"); // separation
            //print client2 ArrayList
            for (String[] S : al2) {
                System.out.println(Arrays.toString(S));
            }
//            while (!s1.isClosed() && !s2.isClosed()) {
//                readCoords();
//            }
            //close everything
            ois1.close();
            ois2.close();
            oos1.close();
            oos2.close();
            s1.close();
            s2.close();
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//    private static String[] readCoords() {
//        //
//        return new String[0];
//    }
}
