import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class PortScanner {

    public static void getPortRange()  {

        for (int i=70; i <= 90 ; i++)   {
            try {
                Socket socket = new Socket("localhost", i);
                System.out.println("port" + i + "is open");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
