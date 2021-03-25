package ui;

import controller.RequestManagerController;
import dto.AddressDto;
import dto.RequestTypeDto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditRequestGUI {
    JFrame frame;
    JList<AddressDto> addressList;
    JList<RequestTypeDto> typeList;
    DefaultListModel<RequestTypeDto> typeModel;
    DefaultListModel<AddressDto> listModel;
    JScrollPane addressScrollPane;
    JScrollPane typeScrollPane;
    JLabel title;
    JLabel selectAddressLabel;
    JLabel contentLabel;
    JLabel selectTypeLabel;
    JTextArea content;
    JPanel titlePanel;
    JPanel addressListPanel;
    JPanel typeListPanel;
    JPanel formPanel;
    JPanel buttonPanel;
    JPanel wrapperPanel;
    JButton addButton;

    public EditRequestGUI(RequestManagerController controller){
        frame = new JFrame("Edit request");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //###### TITLE #######
        title = new JLabel("Edit request", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titlePanel.add(title);

        wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new GridLayout(3,1,10,10));
        wrapperPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        //###### ADDRESS LIST PANEL ########
        addressListPanel = new JPanel();
        addressListPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        addressListPanel.setLayout(new GridLayout(1,2,10,10));
        selectAddressLabel = new JLabel("Choose address for the request", SwingConstants.CENTER);
        listModel = controller.initAddressListModel();
        addressList = new JList<AddressDto>(listModel);
        addressList.setVisibleRowCount(7);
        addressScrollPane = new JScrollPane(addressList);
        addressList.setMinimumSize(new Dimension(150,30));
        addressListPanel.add(selectAddressLabel);
        addressListPanel.add(addressScrollPane);

        //###### TYPE LIST PANEL ########
        typeListPanel = new JPanel();
        typeListPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        typeListPanel.setLayout(new GridLayout(1,2,10,10));
        selectTypeLabel = new JLabel("Select a type for the request", SwingConstants.CENTER);
        typeModel = controller.initRequestTypeListModel();
        typeList = new JList<RequestTypeDto>(typeModel);
        typeList.setVisibleRowCount(7);
        typeScrollPane = new JScrollPane(typeList);
        typeList.setMinimumSize(new Dimension(150,30));
        typeListPanel.add(selectTypeLabel);
        typeListPanel.add(typeScrollPane);

        //###### FORM PANEL
        formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(1,2,10,10));
        formPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        contentLabel = new JLabel("Request content: ", SwingConstants.CENTER);
        content = new JTextArea();
        formPanel.add(contentLabel);
        formPanel.add(content);

        wrapperPanel.add(addressListPanel);
        wrapperPanel.add(typeListPanel);
        wrapperPanel.add(formPanel);

        //######### BUTTON #########
        addButton = new JButton("Modify request");
        addButton.setPreferredSize(new Dimension(150, 40));
        addButton.setBackground(new Color(17, 153, 17));
        addButton.setForeground(new Color(255,255,255));
        addButton.addActionListener(e->controller.modifyRequest());
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(20, 0, 10, 0));
        buttonPanel.add(addButton);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(wrapperPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setMinimumSize(new Dimension(700,500));
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JList<AddressDto> getAddressList() {
        return addressList;
    }

    public JList<RequestTypeDto> getTypeList() {
        return typeList;
    }

    public DefaultListModel<RequestTypeDto> getTypeModel() {
        return typeModel;
    }

    public DefaultListModel<AddressDto> getListModel() {
        return listModel;
    }

    public JTextArea getContent() {
        return content;
    }
}
