package controller;

import dto.SecureUserDto;
import dto.UserDto;
import service.UserService;
import ui.LandingGUI;
import ui.LoginGUI;
import ui.MainMenuGUI;
import ui.RegisterGUI;
import validator.UserValidator;

import javax.swing.*;

public class LandingController {
    private SecureUserDto currentUser;
    private LandingGUI landing;
    private LoginGUI login;
    private RegisterGUI register;
    private UserService userService;
    private MainMenuController mainMenuController;

    public LandingController(){
        userService = new UserService();
        landing = new LandingGUI(this);
    }

    public void openLoginPage(){
        login = new LoginGUI(this);
    }

    public void openRegisterPage(){
        register = new RegisterGUI(this);
    }

    public void loginButtonPressed(){
        JFrame frame = login.getFrame();
        String enteredEmail = login.getEmailField().getText();
        String enteredPassword = login.getPasswordField().getText();
        if(enteredEmail=="" || enteredEmail==null || enteredPassword=="" || enteredPassword==null)
            JOptionPane.showMessageDialog(frame, "Both fields are compulsory!", "Access denied", JOptionPane.ERROR_MESSAGE);
        else
        {
            try{
                currentUser = userService.loginUser(enteredEmail, enteredPassword);
                if(currentUser.getRole()==1)
                    mainMenuController = new MainMenuController(currentUser);
                frame.dispose();
                landing.getFrame().dispose();
            }catch(Exception e){
                JOptionPane.showMessageDialog(frame, e.getMessage(), "Access denied", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void registerButtonPressed(){
        JFrame frame = register.getFrame();
        String confirmPassword = register.getConfirmPasswordField().getText();
        UserDto user = new UserDto();
        user.setFirstName(register.getNameField().getText());
        user.setLastName(register.getSurnameField().getText());
        user.setEmail(register.getEmailField().getText());
        user.setPassword(register.getPasswordField().getText());
        user.setCNP(register.getCNPField().getText());
        UserValidator validator = new UserValidator();
        try {
            validator.validateRegister(user);
            validator.validateMatchingPasswords(user.getPassword(), confirmPassword);
            userService.registerUser(user);
            JOptionPane.showMessageDialog(frame, "Success! You may now login!", "Registration successful", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Registration denied", JOptionPane.ERROR_MESSAGE);
        }
    }
}
