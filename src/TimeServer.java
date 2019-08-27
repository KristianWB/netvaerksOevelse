import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
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

            // Numbering the client
            int clientNo = 1;

            while (true) {
                Socket socket = serverSocket.accept();  // Building the socket and allowing connection from client

                // Im copying Liams book at listing 33.4, because it fits perfectly to the task
                // Display the client number
                jta.append(
                        "Starting thread for client " + clientNo + " at " + new Date() + '\n'
                );

                // Find the clients host name and IP address
                InetAddress inetAddress = socket.getInetAddress();
                jta.append(
                        "Client " + clientNo + "'s host name is " + inetAddress.getHostName() + "\n"
                );
                jta.append(
                        "Client " + clientNo + "'s IP Address is " + inetAddress.getHostAddress() + "\n"
                );

                // Create a new thread for the connection
                HandleAClient task = new HandleAClient(socket);

                // Start the new thread
                new Thread(task).start();
                System.out.println("check_2");

                // Increment clientNo
                clientNo++;


            }
        } catch (IOException e) {
            System.out.println("Dude... it blew up!!!");
            e.printStackTrace();
        }
    }

    // Inner class
    // Define the thread class for handling new connection
    class HandleAClient implements Runnable {
        private Socket socket; // A connected socket

        /** Construct a thread */
        public HandleAClient(Socket socket) {
            this.socket = socket;
            System.out.println("check_1");
        }

        @Override /** Run a thread */
        public void run() {
            try {
                System.out.println("Check_3");
                // Create data input and output streams
                DataInputStream dataInputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputToClient = new DataOutputStream(socket.getOutputStream());
                System.out.println("Check_3_1");

                // ObjectInputStream objectInputFromClient = new ObjectInputStream(socket.getInputStream());
                System.out.println("Check_3_2");
                ObjectOutputStream objectOutputToClient = new ObjectOutputStream(socket.getOutputStream());
                System.out.println("Check_3_3");

                // Continuously serve the client
                while (true) {
                    System.out.println("Check_4");
                    // Inserting the logic machinery the server has to perform on the client requests

                    objectOutputToClient.writeObject("" + new Date()); // Sending the date back to client
                    System.out.println(
                            "Time requested from client at time " + new Date() + '\n'
                    );

                    jta.append(
                            "Time requested from client at time " + new Date() + '\n'
                    );
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

