package ui;

import controller.AddressManagerController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewApartmentGUI {
    JFrame frame;
    JLabel title;
    JLabel streetLabel;
    JLabel numberLabel;
    JLabel floorLabel;
    JLabel apartmentNumberLabel;
    JTextField streetField;
    JTextField numberField;
    JTextField floorField;
    JTextField apartmentNumberField;
    JButton addApartmentButton;
    JPanel buttonPanel;
    JPanel titlePanel;
    JPanel formPanel;
    Color buttonColor = new Color(114, 255, 114);

    public NewApartmentGUI(AddressManagerController controller){
        frame = new JFrame("Add new apartment");
        //frame.setLayout(new GridLayout(3,1,10,0));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //######## TITLE ########
        title = new JLabel("Add new apartment", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titlePanel.add(title);
        //######## </TITLE> ##########

        //######## FORM ########
        formPanel = new JPanel(new GridLayout(4,2,10,10));
        formPanel.setBorder(new EmptyBorder(15,0,15,10));
        //## Street row ##
        streetLabel = new JLabel("Street", SwingConstants.CENTER);
        streetField = new JTextField();
        formPanel.add(streetLabel);
        formPanel.add(streetField);
        //## Number row ##
        numberLabel = new JLabel("Number", SwingConstants.CENTER);
        numberField = new JTextField();
        formPanel.add(numberLabel);
        formPanel.add(numberField);
        //## Floor row ##
        floorLabel = new JLabel("Floor", SwingConstants.CENTER);
        floorField = new JTextField();
        formPanel.add(floorLabel);
        formPanel.add(floorField);
        //## Apartment number row ##
        apartmentNumberLabel = new JLabel("Apartment number", SwingConstants.CENTER);
        apartmentNumberField = new JTextField();
        formPanel.add(apartmentNumberLabel);
        formPanel.add(apartmentNumberField);
        //#########</FORM>#######

        //######### BUTTON #########
        addApartmentButton = new JButton("Add apartment");
        addApartmentButton.setPreferredSize(new Dimension(150, 40));
        addApartmentButton.addActionListener(e -> controller.addNewApartment());
        addApartmentButton.setBackground(buttonColor);
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        buttonPanel.add(addApartmentButton);

        //######### Preparing frame ###########
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.getRootPane().setDefaultButton(addApartmentButton); // Enter will press this button
        frame.setMinimumSize(new Dimension(520,400));
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JTextField getStreetField() {
        return streetField;
    }

    public JTextField getNumberField() {
        return numberField;
    }

    public JTextField getFloorField() {
        return floorField;
    }

    public JTextField getApartmentNumberField() {
        return apartmentNumberField;
    }
}
