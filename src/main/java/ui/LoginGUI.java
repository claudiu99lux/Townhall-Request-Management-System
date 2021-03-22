package ui;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginGUI {
    private JFrame frame;
    private JLabel title;
    private JPanel titlePanel;
    private JPanel formPanel;
    private JPanel buttonPanel;
    private JLabel emailFieldLabel;
    private JLabel passwordFieldLabel;
    private JTextField emailField;
    private JTextField passwordField;
    private JButton loginButton;

    public LoginGUI(Controller controller){
        frame = new JFrame("Login");
        frame.setLayout(new GridLayout(3,1,10,0));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //######## TITLE ########
        title = new JLabel("Login", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titlePanel.add(title);
        //######## </TITLE> ##########

        //######## FORM ########
        formPanel = new JPanel(new GridLayout(2,2,10,10));
        formPanel.setBorder(new EmptyBorder(0,0,0,10));
        //## Email row ##
        emailFieldLabel = new JLabel("Email", SwingConstants.CENTER);
        emailField = new JTextField();
        formPanel.add(emailFieldLabel);
        formPanel.add(emailField);
        //## Password row ##
        passwordFieldLabel = new JLabel("Password", SwingConstants.CENTER);
        passwordField = new JPasswordField();
        formPanel.add(passwordFieldLabel);
        formPanel.add(passwordField);
        //#########</FORM>#######

        //######### BUTTON #########
        loginButton = new JButton("LOGIN");
        loginButton.setPreferredSize(new Dimension(150, 40));
        loginButton.addActionListener(e -> controller.loginButtonPressed());
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        buttonPanel.add(loginButton);

        //######### Preparing frame ###########
        frame.add(titlePanel);
        frame.add(formPanel);
        frame.add(buttonPanel);
        frame.getRootPane().setDefaultButton(loginButton); // Enter will press this button
        frame.setMinimumSize(new Dimension(520,300));
        frame.setVisible(true);
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JFrame getFrame() {
        return frame;
    }
}
