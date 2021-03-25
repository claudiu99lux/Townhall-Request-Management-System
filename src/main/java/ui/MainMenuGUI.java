package ui;

import controller.MainMenuController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenuGUI {
    private JFrame frame;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JLabel welcomeMessage;
    private JButton changePasswordButton;
    private JButton manageAddressesButton;
    private JButton manageRequestsButton;
    Color buttonColor = new Color(150, 220, 255);

    public MainMenuGUI(MainMenuController controller){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //######### Welcome message ###########
        welcomeMessage = new JLabel("Bine ai venit, "+ controller.getCurrentUser().getFirstName(), SwingConstants.CENTER);
        welcomeMessage.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titlePanel.add(welcomeMessage);
        //######### </Welcome message> ############

        //######### BUTTONS #########
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(5, 10, 10, 10));
        buttonPanel.setLayout(new GridLayout(3,1,10,0));
        changePasswordButton = new JButton("Change Password");
        changePasswordButton.addActionListener(e -> controller.openChangePasswordPage());
        manageAddressesButton = new JButton("Manage Addresses");
        manageAddressesButton.addActionListener(e -> controller.openManageAddressesPage());
        manageRequestsButton = new JButton("Manage Requests");
        manageRequestsButton.addActionListener(e->controller.openManageRequestsPage());
        changePasswordButton.setBackground(buttonColor);
        manageAddressesButton.setBackground(buttonColor);
        manageRequestsButton.setBackground(buttonColor);

        buttonPanel.add(changePasswordButton);
        buttonPanel.add(manageAddressesButton);
        buttonPanel.add(manageRequestsButton);

        frame.add(welcomeMessage, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setMinimumSize(new Dimension(400,300));
        frame.setVisible(true);
    }


}
