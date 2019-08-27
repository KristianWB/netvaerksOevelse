import java.net.InetAddress;
import java.net.UnknownHostException;

public class FindIp   {

    public static String getIpFromHost(String host)   {

        try {
            return InetAddress.getByName(host).toString();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;

    }
}