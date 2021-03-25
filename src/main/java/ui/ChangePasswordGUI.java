package ui;

import controller.MainMenuController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChangePasswordGUI {
    JFrame frame;
    JPanel formPanel;
    JPanel changeButtonPanel;
    JPanel titlePanel;
    JTextField passwordField;
    JTextField confirmPasswordField;
    JLabel passwordFieldLabel;
    JLabel confirmPasswordFieldLabel;
    JLabel title;
    JButton changeButton;
    Color buttonColor = new Color(17, 153, 17);

    public ChangePasswordGUI(MainMenuController controller){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        title = new JLabel("Change password");
        title.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titlePanel.add(title);

        //######## FORM ########
        formPanel = new JPanel(new GridLayout(2,2,10,10));
        formPanel.setBorder(new EmptyBorder(10,0,0,10));
        //## Password row ##
        passwordFieldLabel = new JLabel("New Password", SwingConstants.CENTER);
        passwordField = new JPasswordField();
        formPanel.add(passwordFieldLabel);
        formPanel.add(passwordField);
        //## Confirm Password row ##
        confirmPasswordFieldLabel = new JLabel("Repeat Password", SwingConstants.CENTER);
        confirmPasswordField = new JPasswordField();
        formPanel.add(confirmPasswordFieldLabel);
        formPanel.add(confirmPasswordField);
        //#########</FORM>#######

        changeButton = new JButton("Change password");
        changeButton.setPreferredSize(new Dimension(150, 40));
        changeButton.addActionListener(e -> controller.changePasswordButtonPressed());
        changeButton.setBackground(buttonColor);
        changeButton.setForeground(new Color(255,255,255));
        changeButtonPanel = new JPanel();
        changeButtonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        changeButtonPanel.add(changeButton);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(changeButtonPanel, BorderLayout.SOUTH);
        frame.getRootPane().setDefaultButton(changeButton); // Enter will press this button
        frame.setMinimumSize(new Dimension(400,250));
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public JTextField getConfirmPasswordField() {
        return confirmPasswordField;
    }
}
