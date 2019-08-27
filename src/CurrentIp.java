import java.net.InetAddress;
import java.net.UnknownHostException;

public class CurrentIp {

    public static String getCurrentIp ()   {
        try {
            return InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }

}
