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
    private JButton timeRequestButton = new JButton("TimeRequest");

    public static void main(String[] args) {
        new TimeRequestClient();
    }

    public TimeRequestClient() {

        setLayout(new BorderLayout());
        add(new JScrollPane(jTextArea), BorderLayout.CENTER);
        add(timeRequestButton, BorderLayout.SOUTH);

        setTitle("Time request Client");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        timeRequestButton.addActionListener(e -> {
            jTextArea.append("can't touch me!!!" + '\n');

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
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Failed to connect");
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });

    }

    /*
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            jTextArea.append("can't touch me!!!");

            try {
                Socket socket = new Socket("localhost", 1);
                jTextArea.append(
                        "Server contacted at time " + new Date() + '\n');

                DataInputStream fromServer = new DataInputStream(socket.getInputStream());
                jTextArea.append(
                        "Server contacted at time " + fromServer + '\n');

            } catch (UnknownHostException ex) {
                ex.printStackTrace();
                System.out.println("Failed to connect");
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Failed to connect");
            }

        }

    }

     */
}





