package ui;


import controller.AdminRequestManagerController;
import dto.RequestTypeDto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class FilterPanel {
    JFrame frame;
    JPanel filterPanel;
    JPanel filter1;
    JPanel filter2;
    JPanel titlePanel;
    JPanel buttonPanel;
    JList<String> dateList;
    DefaultListModel<String> dateModel;
    JList<RequestTypeDto> typeList;
    DefaultListModel<RequestTypeDto> typeModel;
    JButton clearFilters;
    JLabel dateListLabel;
    JLabel typeListLabel;
    JLabel title;
    JScrollPane sp1;
    JScrollPane sp2;
    ListSelectionModel dateSelectionModel;
    ListSelectionModel typeSelectionModel;


    public FilterPanel(AdminRequestManagerController controller){
        frame = new JFrame("Manage Document Types");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //###### TITLE #######
        title = new JLabel("Document types", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titlePanel.add(title);

        //###### DATES LIST #######
        filter1 = new JPanel();
        filter1.setLayout(new GridLayout(2,1,5,5));

        dateModel = controller.initDateListModel();
        dateList = new JList<String>(dateModel);
        dateSelectionModel = controller.initDateSelectionModel(dateList);
        dateList.setVisibleRowCount(7);
        sp1 = new JScrollPane(dateList);

        dateListLabel = new JLabel("Select date to filter", SwingConstants.CENTER);
        filter1.add(dateListLabel);
        filter1.add(sp1);

        //###### TYPE LIST #######
        filter2 = new JPanel();
        filter2.setLayout(new GridLayout(2,1,5,5));

        typeModel = controller.initTypeListModel();
        typeList = new JList<RequestTypeDto>(typeModel);
        typeSelectionModel = controller.initTypeSelectionModel(typeList);
        typeList.setVisibleRowCount(7);
        sp2 = new JScrollPane(typeList);

        typeListLabel = new JLabel("Select Document Type to filter", SwingConstants.CENTER);
        filter2.add(typeListLabel);
        filter2.add(sp2);

        filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(1,2,5,10));
        filterPanel.setBorder(new EmptyBorder(5,5,5,5));
        filterPanel.add(filter1);
        filterPanel.add(filter2);

        //##### BUTTON PANEL #####
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        clearFilters = new JButton("Reset filters");
        clearFilters.addActionListener(e->controller.resetFilters());
        buttonPanel.add(clearFilters);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(filterPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setMinimumSize(new Dimension(800, 400));
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JList<String> getDateList() {
        return dateList;
    }

    public DefaultListModel<String> getDateModel() {
        return dateModel;
    }

    public JList<RequestTypeDto> getTypeList() {
        return typeList;
    }

    public DefaultListModel<RequestTypeDto> getTypeModel() {
        return typeModel;
    }
}
