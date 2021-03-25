package ui;

import controller.AdminMenuController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminMainMenuGUI {
    private JFrame frame;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JLabel title;
    private JButton viewUsersButton;
    private JButton manageDocumentsButton;
    private JButton manageRequestsButton;
    Color buttonColor = new Color(150, 220, 255);

    public AdminMainMenuGUI(AdminMenuController controller){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //######### Welcome message ###########
        title = new JLabel("Administrator panel", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titlePanel.add(title);
        //######### </Welcome message> ############

        //######### BUTTONS #########
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(5, 10, 10, 10));
        buttonPanel.setLayout(new GridLayout(3,1,10,0));
        viewUsersButton = new JButton("View User List");
        viewUsersButton.addActionListener(e -> controller.openUsersPage());
        manageDocumentsButton = new JButton("Manage Document Types");
        manageDocumentsButton.addActionListener(e -> controller.openManageDocumentsPage());
        manageRequestsButton = new JButton("Manage Requests");
        manageRequestsButton.addActionListener(e->controller.openManageRequestsPage());
        viewUsersButton.setBackground(buttonColor);
        manageDocumentsButton.setBackground(buttonColor);
        manageRequestsButton.setBackground(buttonColor);

        buttonPanel.add(viewUsersButton);
        buttonPanel.add(manageDocumentsButton);
        buttonPanel.add(manageRequestsButton);

        frame.add(title, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setMinimumSize(new Dimension(400,300));
        frame.setVisible(true);
    }
}
