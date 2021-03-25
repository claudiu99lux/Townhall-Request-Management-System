package controller;

import dto.AddressDto;
import dto.RequestDto;
import dto.RequestTypeDto;
import dto.SecureUserDto;
import exception.GenericExceptionMessages;
import exception.IllegalValueException;
import service.RequestTypeService;
import service.UserService;
import ui.AdminMainMenuGUI;
import ui.DocumentManagerGUI;
import ui.UserListGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AdminMenuController {
    private SecureUserDto currentUser;
    private UserService userService;
    private RequestTypeService requestTypeService;
    private AdminMainMenuGUI adminMainMenuGUI;
    private UserListGUI userListGUI;
    private DocumentManagerGUI documentManagerGUI;
    private AdminRequestManagerController adminRequestManagerController;

    public AdminMenuController(SecureUserDto currentUser){
        this.currentUser = currentUser;
        userService = new UserService();
        requestTypeService = new RequestTypeService();
        adminMainMenuGUI = new AdminMainMenuGUI(this);
    }

    public void openUsersPage(){
        userListGUI = new UserListGUI(this);
    }

    public void openManageDocumentsPage(){
        documentManagerGUI = new DocumentManagerGUI(this);
    }

    public void openManageRequestsPage(){
        adminRequestManagerController = new AdminRequestManagerController();
    }

    public void newDocument(){
        String name = JOptionPane.showInputDialog("New Document Type Name:");
        try{
            validateInput(name);
            RequestTypeDto dto = new RequestTypeDto();
            dto.setName(name);
            requestTypeService.addNewRequestType(dto);
            updateListModel();
        }catch(Exception e){
            JOptionPane.showMessageDialog(documentManagerGUI.getFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteSelectedDocument(){
        int index = documentManagerGUI.getDocumentList().getSelectedIndex();
        List<RequestTypeDto> requestTypes = requestTypeService.getAllRequestTypes();
        DefaultListModel<RequestTypeDto> listModel = documentManagerGUI.getListModel();
        listModel.removeAllElements();
        for(RequestTypeDto r : requestTypes)
            listModel.addElement(r);
        documentManagerGUI.getListModel().remove(index);
        requestTypeService.deleteRequestType(requestTypes.get(index).getId());
    }

    public DefaultTableModel initUserTableModel(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("CNP");
        tableModel.addColumn("Role");

        List<SecureUserDto> userList = userService.getAllUsers();
        for(SecureUserDto u : userList){
            tableModel.addRow(new Object[] {u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), u.getCNP(), u.getRole()});
        }
        return tableModel;
    }

    public DefaultListModel<RequestTypeDto> initDocumentListModel(){
        DefaultListModel<RequestTypeDto> listModel = new DefaultListModel<RequestTypeDto>();
        List<RequestTypeDto> requestTypes = requestTypeService.getAllRequestTypes();
        for(RequestTypeDto r : requestTypes)
            listModel.addElement(r);
        return listModel;
    }

    private void updateListModel(){
        List<RequestTypeDto> requestTypes = requestTypeService.getAllRequestTypes();
        DefaultListModel<RequestTypeDto> listModel = documentManagerGUI.getListModel();
        listModel.removeAllElements();
        for(RequestTypeDto r : requestTypes)
            listModel.addElement(r);
    }

    private void validateInput(String s) throws IllegalValueException {
        if(s.equals("")||s==null)
            throw new IllegalValueException(GenericExceptionMessages.DOCUMENT_NAME_EMPTY);
    }
}
