package ui;

import controller.AdminRequestManagerController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class AdminRequestManagerGUI {
    JFrame frame;
    JLabel userRequests;
    JTable requestsTable;
    JButton approveRequestButton;
    JButton declineRequestButton;
    JButton deleteRequestButton;
    JButton openFilters;
    JPanel buttonsPanel;
    JPanel titlePanel;
    JScrollPane scrollPane;
    DefaultTableModel tableModel;
    Color buttonColor = new Color(150, 220, 255);

    public AdminRequestManagerGUI(AdminRequestManagerController controller) {
        frame = new JFrame("Manage requests");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //###### TITLE #######
        userRequests = new JLabel("Your requests", SwingConstants.CENTER);
        userRequests.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titlePanel.add(userRequests);

        //####### TABLE #######
        tableModel = controller.initRequestTableModel();
        requestsTable = new JTable(tableModel);
        scrollPane = new JScrollPane(requestsTable);
        //Bold header
        JTableHeader th = requestsTable.getTableHeader();
        th.setFont(new Font("Serif", Font.BOLD, 15));
        //Center cell data
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < tableModel.getColumnCount(); i++)
            requestsTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);

        //####### Buttons #######
        buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonsPanel.setLayout(new GridLayout(2, 2, 10, 5));
        approveRequestButton = new JButton("Approve selected request");
        declineRequestButton = new JButton("Decline selected request");
        deleteRequestButton = new JButton("Delete selected request");
        approveRequestButton.setBackground(buttonColor);
        declineRequestButton.setBackground(buttonColor);
        deleteRequestButton.setBackground(new Color(203, 88, 88));
        deleteRequestButton.setForeground(new Color(255, 255, 255));

        approveRequestButton.addActionListener(e->controller.approveSelectedRequest());
        declineRequestButton.addActionListener(e->controller.declineSelectedRequest());
        deleteRequestButton.addActionListener(e->controller.deleteSelectedRequest());

        openFilters = new JButton("Open filters panel");
        openFilters.setBackground(new Color(68, 68, 68));
        openFilters.setForeground(new Color(255, 255, 255));

        openFilters.addActionListener(e->controller.openFilterPanel());

        buttonsPanel.add(approveRequestButton);
        buttonsPanel.add(declineRequestButton);
        buttonsPanel.add(deleteRequestButton);
        buttonsPanel.add(openFilters);


        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.setMinimumSize(new Dimension(1800, 400));
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTable getRequestsTable() {
        return requestsTable;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
