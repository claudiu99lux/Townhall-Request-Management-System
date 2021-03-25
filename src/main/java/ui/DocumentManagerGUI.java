package ui;

import controller.AdminMenuController;
import dto.RequestTypeDto;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DocumentManagerGUI {
    JFrame frame;
    JLabel title;
    JList<RequestTypeDto> documentList;
    JButton newTypeButton;
    JButton deleteTypeButton;
    JPanel buttonsPanel;
    JPanel titlePanel;
    JScrollPane scrollPane;
    DefaultListModel<RequestTypeDto> listModel;
    Color buttonColor = new Color(150, 220, 255);

    public DocumentManagerGUI(AdminMenuController controller){
        frame = new JFrame("Manage Document Types");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //###### TITLE #######
        title = new JLabel("Document types");
        title.setFont(new Font("Serif", Font.BOLD, 22));
        titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        titlePanel.add(title);

        //####### LIST #######
        listModel = controller.initDocumentListModel();
        documentList = new JList<>(listModel);
        documentList.setVisibleRowCount(7);
        scrollPane = new JScrollPane(documentList);

        //####### Buttons #######
        buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonsPanel.setLayout(new GridLayout(1,2,10,0));
        newTypeButton = new JButton("Add new document");
        deleteTypeButton = new JButton("Delete selected document");
        newTypeButton.addActionListener(e->controller.newDocument());
        newTypeButton.setBackground(buttonColor);
        deleteTypeButton.addActionListener(e->controller.deleteSelectedDocument());
        deleteTypeButton.setBackground(new Color(203, 88, 88));
        deleteTypeButton.setForeground(new Color(255,255,255));
        buttonsPanel.add(newTypeButton);
        buttonsPanel.add(deleteTypeButton);

        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
        frame.setMinimumSize(new Dimension(520,400));
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JList<RequestTypeDto> getDocumentList() {
        return documentList;
    }

    public DefaultListModel<RequestTypeDto> getListModel() {
        return listModel;
    }
}
