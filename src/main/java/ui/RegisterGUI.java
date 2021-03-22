package ui;

import controller.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterGUI {
    private JFrame frame;
    private JLabel title;
    private JPanel titlePanel;
    private JPanel formPanel;
    private JPanel buttonPanel;
    private JLabel emailFieldLabel;
    private JLabel passwordFieldLabel;
    private JLabel confirmPasswordFieldLabel;
    private JLabel nameFieldLabel;
    private JLabel surnameFieldLabel;
    private JLabel CNPFieldLabel;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField confirmPasswordField;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField CNPField;
    private JButton registerButton;

    public RegisterGUI(Controller controller){
        frame = new JFrame("Register");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //######## TITLE ########
        title = new JLabel("Register", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 0, 20, 0));
        titlePanel.add(title);
        //######## </TITLE> ##########

        //######## FORM ########
        formPanel = new JPanel(new GridLayout(6,2,10,10));
        formPanel.setBorder(new EmptyBorder(0,0,0,10));
        //## Surname row ##
        surnameFieldLabel = new JLabel("Nume", SwingConstants.CENTER);
        surnameField = new JTextField();
        formPanel.add(surnameFieldLabel);
        formPanel.add(surnameField);
        //## Name row ##
        nameFieldLabel = new JLabel("Prenume", SwingConstants.CENTER);
        nameField = new JTextField();
        formPanel.add(nameFieldLabel);
        formPanel.add(nameField);
        //## CNP row ##
        CNPFieldLabel = new JLabel("CNP", SwingConstants.CENTER);
        CNPField = new JTextField();
        formPanel.add(CNPFieldLabel);
        formPanel.add(CNPField);
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
        //## Confirm Password row ##
        confirmPasswordFieldLabel = new JLabel("Confirm Password", SwingConstants.CENTER);
        confirmPasswordField = new JPasswordField();
        formPanel.add(confirmPasswordFieldLabel);
        formPanel.add(confirmPasswordField);
        //#########</FORM>#######

        //######### BUTTON #########
        registerButton = new JButton("REGISTER");
        registerButton.setPreferredSize(new Dimension(150, 40));
        registerButton.addActionListener(e->controller.registerButtonPressed());
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        buttonPanel.add(registerButton);

        //######### Preparing frame ###########
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.getRootPane().setDefaultButton(registerButton); // Enter will press this button
        frame.setMinimumSize(new Dimension(520,450));
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JTextField getConfirmPasswordField() {
        return confirmPasswordField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getSurnameField() {
        return surnameField;
    }

    public JTextField getCNPField() {
        return CNPField;
    }
}
