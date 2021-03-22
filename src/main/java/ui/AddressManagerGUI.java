package ui;

import controller.Controller;
import dto.AddressDto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddressManagerGUI {
    JFrame frame;
    JLabel userAddresses;
    JList<AddressDto> addressList;
    JButton newHouseAddressButton;
    JButton newApartmentAddressButton;
    JButton deleteSelectedButton;
    JPanel buttonsPanel;
    JPanel titlePanel;
    JPanel buttonWrapperPanel;
    JScrollPane scrollPane;
    DefaultListModel<AddressDto> listModel;

    public AddressManagerGUI(Controller controller){
        frame = new JFrame("Manage addresses");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //###### TITLE #######
        userAddresses = new JLabel("Your addresses");
        userAddresses.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titlePanel.add(userAddresses);

        //####### LIST #######
        listModel = controller.initAddressListModel();
        addressList = new JList<>(listModel);
        addressList.setVisibleRowCount(7);
        scrollPane = new JScrollPane(addressList);

        //####### Buttons #######
        buttonsPanel = new JPanel();
        //buttonsPanel.setBorder(new EmptyBorder(5, 10, 10, 10));
        buttonsPanel.setLayout(new GridLayout(1,2,10,0));
        newHouseAddressButton = new JButton("Add new house");
        newApartmentAddressButton = new JButton("Add new apartment");
        newHouseAddressButton.addActionListener(e->controller.openNewHouseAddressGUI());
        newApartmentAddressButton.addActionListener(e->controller.openNewApartmentAddressGUI());
        buttonsPanel.add(newHouseAddressButton);
        buttonsPanel.add(newApartmentAddressButton);

        buttonWrapperPanel = new JPanel();

        deleteSelectedButton = new JButton("Delete selected address");
        deleteSelectedButton.addActionListener(e->controller.deleteSelectedAddress());
        buttonWrapperPanel.setLayout(new GridLayout(2,1,10,5));
        buttonWrapperPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonWrapperPanel.add(buttonsPanel);
        buttonWrapperPanel.add(deleteSelectedButton);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonWrapperPanel, BorderLayout.SOUTH);
        frame.setMinimumSize(new Dimension(520,400));
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public DefaultListModel<AddressDto> getListModel() {
        return listModel;
    }

    public void setListModel(DefaultListModel<AddressDto> listModel) {
        this.listModel = listModel;
    }

    public JList<AddressDto> getAddressList() {
        return addressList;
    }
}
