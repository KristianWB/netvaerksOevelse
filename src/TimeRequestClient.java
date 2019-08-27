import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class TimeRequestClient extends JFrame {

    private JTextArea jTextArea = new JTextArea();

    public static void main(String[] args) {
        new TimeRequestClient();
    }

    public TimeRequestClient() {

        setLayout(new BorderLayout());
        JTextField jTextField = new JTextField();
        add(jTextField, BorderLayout.NORTH);
        add(new JScrollPane(jTextArea), BorderLayout.CENTER);
        JButton timeRequestButton = new JButton("TimeRequest");
        add(timeRequestButton, BorderLayout.SOUTH);
        JButton eccoButton = new JButton("Ecco");
        add(eccoButton, BorderLayout.WEST);

        setTitle("Multipurpose Client");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        timeRequestButton.addActionListener(e -> {
            jTextArea.append("Date and time requested" + '\n');

            try {
                Socket socket = new Socket("localhost", 1);
                jTextArea.append(
                        "Server contacted at time " + new Date() + '\n');

                ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());

                // Get Date from the server
                Object date = fromServer.readObject();

                jTextArea.append(
                        "Server info received at time " + date + '\n');

            } catch (UnknownHostException ex) {
                ex.printStackTrace();
                System.out.println("Failed to connect");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });


        eccoButton.addActionListener(e -> {

            jTextArea.append("Ecco requested" + '\n');

            try {
                Socket socket = new Socket("localhost", 1);
                jTextArea.append(
                        "Server contact initialized at time " + new Date() + '\n');

                ObjectInputStream fromServer = new ObjectInputStream(socket.getInputStream());

                // Get Date from the server
                Object date = fromServer.readObject();

                jTextArea.append(
                        "Server info received at time " + date + '\n');

            } catch (UnknownHostException ex) {
                ex.printStackTrace();
                System.out.println("Failed to connect");
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Failed to connect");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });



    }
}





