package controller;

import dto.*;
import exception.GenericExceptionMessages;
import exception.IllegalValueException;
import service.AddressService;
import service.UserService;
import ui.*;
import validator.UserValidator;

import javax.swing.*;
import java.util.List;

public class Controller {

    SecureUserDto currentUser;
    LandingGUI landing;
    LoginGUI login;
    RegisterGUI register;
    UserService userService;
    AddressService addressService;
    MainMenuGUI mainMenu;
    ChangePasswordGUI changePasswordGUI;
    AddressManagerGUI addressManagerGUI;
    NewHouseGUI newHouseGUI;
    NewApartmentGUI newApartmentGUI;

    public Controller(){
        userService = new UserService();
        addressService = new AddressService();
        landing = new LandingGUI(this);
        //register = new RegisterGUI(this);
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
                mainMenu = new MainMenuGUI(this);
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

    public void openChangePasswordPage(){
        changePasswordGUI = new ChangePasswordGUI(this);
    }

    public void openManageAddressesPage(){
        addressManagerGUI = new AddressManagerGUI(this);
    }

    public void openNewHouseAddressGUI(){
        newHouseGUI = new NewHouseGUI(this);
    }

    public void openNewApartmentAddressGUI(){
        newApartmentGUI = new NewApartmentGUI(this);
    }

    public void addNewHouse(){
        String street = newHouseGUI.getStreetField().getText();
        String number = newHouseGUI.getNumberField().getText();
        try{
            validateInput(street);
            validateInput(number);
            AddressDto addressDto = new HouseAddressDto();
            addressDto.setBuildingType(1);
            addressDto.setStreet(street);
            addressDto.setNumber(number);
            addressService.addNewAddress(addressDto, currentUser.getId());
            JOptionPane.showMessageDialog(newHouseGUI.getFrame(), "House added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            addressManagerGUI.getListModel().addElement(addressDto);
            updateListModel();
            newHouseGUI.getFrame().dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(newHouseGUI.getFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addNewApartment(){
        String street = newApartmentGUI.getStreetField().getText();
        String number = newApartmentGUI.getNumberField().getText();
        int floor;
        int apartmentNumber;
        try{
            validateInput(street);
            validateInput(number);
            validateInput(newApartmentGUI.getFloorField().getText());
            validateInput(newApartmentGUI.getApartmentNumberField().getText());
            floor = Integer.parseInt(newApartmentGUI.getFloorField().getText());
            apartmentNumber = Integer.parseInt(newApartmentGUI.getApartmentNumberField().getText());
            AddressDto addressDto = new ApartmentAddressDto();
            addressDto.setBuildingType(2);
            addressDto.setStreet(street);
            addressDto.setNumber(number);
            ((ApartmentAddressDto)addressDto).setFloor(floor);
            ((ApartmentAddressDto)addressDto).setApartmentNumber(apartmentNumber);
            addressService.addNewAddress(addressDto, currentUser.getId());
            addressManagerGUI.getListModel().addElement(addressDto);
            updateListModel();
            JOptionPane.showMessageDialog(newApartmentGUI.getFrame(), "Apartment added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            newApartmentGUI.getFrame().dispose();
        }catch(Exception e){
            JOptionPane.showMessageDialog(newApartmentGUI.getFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public DefaultListModel<AddressDto> initAddressListModel(){
        DefaultListModel<AddressDto> listModel = new DefaultListModel<AddressDto>();
        List<AddressDto> addresses = addressService.getUserAddresses(currentUser);
        for(AddressDto a : addresses)
            listModel.addElement(a);
        return listModel;
    }

    public SecureUserDto getCurrentUser() {
        return currentUser;
    }

    public void deleteSelectedAddress(){
        int index = addressManagerGUI.getAddressList().getSelectedIndex();
        System.out.println(index);
        List<AddressDto> addresses = addressService.getUserAddresses(currentUser);
        DefaultListModel<AddressDto> listModel = addressManagerGUI.getListModel();
        listModel.removeAllElements();
        for(AddressDto a : addresses)
            listModel.addElement(a);
        addressManagerGUI.getListModel().remove(index);
        addressService.deleteAddress(addresses.get(index));
    }

    private void updateListModel(){
        List<AddressDto> addresses = addressService.getUserAddresses(currentUser);
        DefaultListModel<AddressDto> listModel = addressManagerGUI.getListModel();
        listModel.removeAllElements();
        for(AddressDto a : addresses)
            listModel.addElement(a);
    }

    public void validateInput(String s) throws IllegalValueException {
        if(s.equals("") || s==null)
            throw new IllegalValueException(GenericExceptionMessages.FIELD_EMPTY);
    }
}
