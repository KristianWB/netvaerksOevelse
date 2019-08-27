import javax.swing.*;
import java.awt.*;

public class TimeRequestClient extends JFrame {

    private JTextArea jTextArea = new JTextArea();
    private JButton timeRequestButton = new JButton("TimeRequest");

    public static void main(String[] args)   {
        new TimeRequestClient();
    }

    public TimeRequestClient()  {

        setLayout(new BorderLayout());
        add(new JScrollPane(jTextArea), BorderLayout.CENTER);
        add(timeRequestButton, BorderLayout.CENTER);

        setTitle("Time request Client");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }




}
