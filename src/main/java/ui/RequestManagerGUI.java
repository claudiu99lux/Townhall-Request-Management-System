package ui;

import controller.RequestManagerController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class RequestManagerGUI {
    JFrame frame;
    JLabel userRequests;
    JTable requestsTable;
    JButton addRequestButton;
    JButton editRequestButton;
    JButton deleteSelectedButton;
    JPanel buttonsPanel;
    JPanel titlePanel;
    JPanel buttonWrapperPanel;
    JScrollPane scrollPane;
    DefaultTableModel tableModel;
    Color buttonColor = new Color(150, 220, 255);

    public RequestManagerGUI(RequestManagerController controller){
        frame = new JFrame("Manage requests");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //###### TITLE #######
        userRequests = new JLabel("Your requests");
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
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int i=0; i<tableModel.getColumnCount(); i++)
            requestsTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );

        //####### Buttons #######
        buttonsPanel = new JPanel();
        //buttonsPanel.setBorder(new EmptyBorder(5, 10, 10, 10));
        buttonsPanel.setLayout(new GridLayout(1,2,10,0));
        addRequestButton = new JButton("Add new request");
        editRequestButton = new JButton("Edit selected request");
        addRequestButton.setBackground(buttonColor);
        editRequestButton.setBackground(buttonColor);
        addRequestButton.addActionListener(e->controller.openNewRequestGUI());
        editRequestButton.addActionListener(e->controller.openEditRequestGUI());
        buttonsPanel.add(addRequestButton);
        buttonsPanel.add(editRequestButton);

        buttonWrapperPanel = new JPanel();

        deleteSelectedButton = new JButton("Delete selected request");
        deleteSelectedButton.addActionListener(e->controller.deleteRequest());
        deleteSelectedButton.setBackground(new Color(203, 88, 88));
        deleteSelectedButton.setForeground(new Color(255,255,255));
        buttonWrapperPanel.setLayout(new GridLayout(2,1,10,5));
        buttonWrapperPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonWrapperPanel.add(buttonsPanel);
        buttonWrapperPanel.add(deleteSelectedButton);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonWrapperPanel, BorderLayout.SOUTH);
        frame.setMinimumSize(new Dimension(1500,400));
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
