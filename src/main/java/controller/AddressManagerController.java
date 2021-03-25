package controller;

import dto.AddressDto;
import dto.ApartmentAddressDto;
import dto.HouseAddressDto;
import dto.SecureUserDto;
import exception.GenericExceptionMessages;
import exception.IllegalValueException;
import service.AddressService;
import ui.AddressManagerGUI;
import ui.NewApartmentGUI;
import ui.NewHouseGUI;

import javax.swing.*;
import java.util.List;

public class AddressManagerController {
    private SecureUserDto currentUser;
    private AddressService addressService;
    private AddressManagerGUI addressManagerGUI;
    private NewHouseGUI newHouseGUI;
    private NewApartmentGUI newApartmentGUI;

    public AddressManagerController(SecureUserDto currentUser){
        this.currentUser = currentUser;
        addressService = new AddressService();
        addressManagerGUI = new AddressManagerGUI(this);
    }

    public void openNewHouseAddressGUI(){
        newHouseGUI = new NewHouseGUI(this);
    }

    public void openNewApartmentAddressGUI(){
        newApartmentGUI = new NewApartmentGUI(this);
    }

    public void deleteSelectedAddress(){
        int index = addressManagerGUI.getAddressList().getSelectedIndex();
        List<AddressDto> addresses = addressService.getUserAddresses(currentUser);
        DefaultListModel<AddressDto> listModel = addressManagerGUI.getListModel();
        listModel.removeAllElements();
        for(AddressDto a : addresses)
            listModel.addElement(a);
        addressManagerGUI.getListModel().remove(index);
        addressService.deleteAddress(addresses.get(index));
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

    private void updateListModel(){
        List<AddressDto> addresses = addressService.getUserAddresses(currentUser);
        DefaultListModel<AddressDto> listModel = addressManagerGUI.getListModel();
        listModel.removeAllElements();
        for(AddressDto a : addresses)
            listModel.addElement(a);
    }

    public DefaultListModel<AddressDto> initAddressListModel(){
        DefaultListModel<AddressDto> listModel = new DefaultListModel<AddressDto>();
        List<AddressDto> addresses = addressService.getUserAddresses(currentUser);
        for(AddressDto a : addresses)
            listModel.addElement(a);
        return listModel;
    }

    public void validateInput(String s) throws IllegalValueException {
        if(s.equals("") || s==null)
            throw new IllegalValueException(GenericExceptionMessages.FIELD_EMPTY);
    }

    public SecureUserDto getCurrentUser() {
        return currentUser;
    }
}
