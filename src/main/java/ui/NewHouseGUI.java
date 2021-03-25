package ui;

import controller.AddressManagerController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewHouseGUI {
    JFrame frame;
    JLabel title;
    JLabel streetLabel;
    JLabel numberLabel;
    JTextField streetField;
    JTextField numberField;
    JButton addHouseButton;
    JPanel buttonPanel;
    JPanel titlePanel;
    JPanel formPanel;
    Color buttonColor = new Color(114, 255, 114);

    public NewHouseGUI(AddressManagerController controller){
        frame = new JFrame("Add new house");
        frame.setLayout(new GridLayout(3,1,10,0));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //######## TITLE ########
        title = new JLabel("Add new house", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titlePanel.add(title);
        //######## </TITLE> ##########

        //######## FORM ########
        formPanel = new JPanel(new GridLayout(2,2,10,10));
        formPanel.setBorder(new EmptyBorder(0,0,0,10));
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
        //#########</FORM>#######

        //######### BUTTON #########
        addHouseButton = new JButton("Add house");
        addHouseButton.setPreferredSize(new Dimension(150, 40));
        addHouseButton.addActionListener(e -> controller.addNewHouse());
        addHouseButton.setBackground(buttonColor);
        buttonPanel = new JPanel();
        buttonPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        buttonPanel.add(addHouseButton);

        //######### Preparing frame ###########
        frame.add(titlePanel);
        frame.add(formPanel);
        frame.add(buttonPanel);
        frame.getRootPane().setDefaultButton(addHouseButton); // Enter will press this button
        frame.setMinimumSize(new Dimension(520,300));
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
}
