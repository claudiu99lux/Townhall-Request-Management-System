package controller;

import dto.SecureUserDto;
import service.UserService;
import ui.AddressManagerGUI;
import ui.ChangePasswordGUI;
import ui.MainMenuGUI;
import ui.RequestManagerGUI;
import validator.UserValidator;

import javax.swing.*;

public class MainMenuController {
    private SecureUserDto currentUser;
    private MainMenuGUI mainMenuGUI;
    private ChangePasswordGUI changePasswordGUI;
    private AddressManagerController addressManagerController;
    private RequestManagerController requestManagerController;
    private UserService userService;

    public MainMenuController(SecureUserDto currentUser){
        this.currentUser = currentUser;
        mainMenuGUI = new MainMenuGUI(this);
        userService = new UserService();
    }

    public void openChangePasswordPage(){
        changePasswordGUI = new ChangePasswordGUI(this);
    }

    public void openManageAddressesPage(){
        addressManagerController = new AddressManagerController(currentUser);
    }

    public void openManageRequestsPage(){
        requestManagerController = new RequestManagerController(currentUser);
    }

    public void changePasswordButtonPressed(){
        UserValidator validator = new UserValidator();
        String password = changePasswordGUI.getPasswordField().getText();
        String confirmedPassword = changePasswordGUI.getConfirmPasswordField().getText();
        try{
            validator.validateMatchingPasswords(password,confirmedPassword);
            userService.updatePassword(currentUser, password);
            JOptionPane.showMessageDialog(changePasswordGUI.getFrame(), "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            changePasswordGUI.getFrame().dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(changePasswordGUI.getFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public SecureUserDto getCurrentUser() {
        return currentUser;
    }

}
