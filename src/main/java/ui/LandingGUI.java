package ui;
import controller.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LandingGUI {
    private JFrame frame;
    private JPanel labelPanel;
    private JPanel buttonPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel landingMessage;


    public LandingGUI(Controller controller){
        frame = new JFrame("Primăria Cluj-Napoca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //######### LABEL ##########
        labelPanel = new JPanel();
        labelPanel.setBorder(new EmptyBorder(10, 10, 5, 10));
        landingMessage = new JLabel("Bine ați venit!", SwingConstants.CENTER);
        landingMessage.setFont(new Font("Serif", Font.BOLD, 20));
        labelPanel.add(landingMessage);

        //######### BUTTONS #########
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(5, 10, 10, 10));
        buttonPanel.setLayout(new GridLayout(1,2,10,0));
        loginButton = new JButton("LOGIN");
        loginButton.addActionListener(e -> controller.openLoginPage());
        registerButton = new JButton("REGISTER");
        registerButton.addActionListener(e -> controller.openRegisterPage());
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);


        frame.add(labelPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setMinimumSize(new Dimension(520,150));
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
