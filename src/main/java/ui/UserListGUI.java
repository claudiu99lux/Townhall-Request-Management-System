package ui;

import controller.AdminMenuController;
import dto.SecureUserDto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class UserListGUI {
    JFrame frame;
    JLabel title;
    JTable userTable;
    JPanel titlePanel;
    JScrollPane scrollPane;
    DefaultTableModel tableModel;
    Color buttonColor = new Color(150, 220, 255);

    public UserListGUI(AdminMenuController controller){
        frame = new JFrame("Manage addresses");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //###### TITLE #######
        title = new JLabel("Registered Users");
        title.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titlePanel.add(title);

        //####### LIST #######
        tableModel = controller.initUserTableModel();
        userTable = new JTable(tableModel);
        //Bold header
        JTableHeader th = userTable.getTableHeader();
        th.setFont(new Font("Serif", Font.BOLD, 15));
        //Center cell data
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for(int i=0; i<tableModel.getColumnCount(); i++)
            userTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
        scrollPane = new JScrollPane(userTable);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setMinimumSize(new Dimension(1500,400));
        frame.setVisible(true);
    }
}
