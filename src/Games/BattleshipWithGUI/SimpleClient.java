package Games.BattleshipWithGUI;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class SimpleClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost",1234);
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            ArrayList al = new ArrayList();
            al.add(new Integer(1));
            al.add(new Integer(2));
            al.add(new Integer(3));
            al.add(new Integer(4));
            al.add(new Integer(5));
            oos.writeObject(al);
            oos.close();
            os.close();
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
