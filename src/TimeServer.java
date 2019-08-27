import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer extends JFrame {   // Im setting it up for Java Swing GUI style

    private JTextArea jta = new JTextArea();   // We need a space for descriptive text

    public static void main(String[] args) {

        new TimeServer();
    }

    public TimeServer() {


        // Building the GUI
        setLayout(new BorderLayout());
        add(new JScrollPane(jta), BorderLayout.CENTER);

        setTitle("TimeServer");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            ServerSocket serverSocket = new ServerSocket(1);    // Building the ServerSocket object for connection

            System.out.println("Server online at time " + new Date() + '\n');
            jta.append("Server online at time " + new Date() + '\n');

            Socket socket = serverSocket.accept();  // Building the socket and allowing connection from client

            DataOutputStream dataOutputToClient = new DataOutputStream(socket.getOutputStream());




            while (true) {
                dataOutputToClient.writeChars(new Date().toString()); // Sending the date back to client
                System.out.println(
                        "Time requested from client at time " + new Date() + '\n'
                );

                jta.append(
                        "Time requested from client at time " + new Date() + '\n'
                );
            }
        } catch (IOException e) {
            System.out.println("Dude... it blew up!!!");
            e.printStackTrace();
        }
    }
}

