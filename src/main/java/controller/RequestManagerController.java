package controller;

import dto.AddressDto;
import dto.RequestDto;
import dto.RequestTypeDto;
import dto.SecureUserDto;
import exception.ConstraintExceptionMessages;
import exception.RequestNotEditable;
import exception.TooManyRequestsException;
import service.AddressService;
import service.RequestService;
import service.RequestTypeService;
import service.UserService;
import ui.EditRequestGUI;
import ui.NewRequestGUI;
import ui.RequestManagerGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class RequestManagerController {
    private SecureUserDto currentUser;
    private RequestService requestService;
    private AddressService addressService;
    private NewRequestGUI newRequestGUI;
    private EditRequestGUI editRequestGUI;
    private RequestManagerGUI requestManagerGUI;
    private RequestTypeService requestTypeService;

    public RequestManagerController(SecureUserDto currentUser){
        this.currentUser = currentUser;
        requestService = new RequestService();
        addressService = new AddressService();
        requestTypeService = new RequestTypeService();
        requestManagerGUI = new RequestManagerGUI(this);
    }

    public void openNewRequestGUI(){
        newRequestGUI = new NewRequestGUI(this);
    }

    public void openEditRequestGUI(){

        int selected = requestManagerGUI.getRequestsTable().getSelectedRow();
        List<RequestDto> requests = requestService.getRequestsByUserID(currentUser.getId());
        RequestDto selectedRequest = requests.get(selected);
        try{
            validateEditableRequest(selectedRequest);
            editRequestGUI = new EditRequestGUI(this);
            editRequestGUI.getAddressList().setSelectedIndex(getSelectedRequestAddressIndex());
            editRequestGUI.getContent().setText(selectedRequest.getContent());
            editRequestGUI.getTypeList().setSelectedIndex(getSelectedRequestTypeIndex());
        }catch(Exception e){
            JOptionPane.showMessageDialog(requestManagerGUI.getFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addNewRequest(){
        int selectedAddress = newRequestGUI.getAddressList().getSelectedIndex();
        int selectedType = newRequestGUI.getTypeList().getSelectedIndex();
        List<AddressDto> addresses = addressService.getUserAddresses(currentUser);
        List<RequestTypeDto> types = requestTypeService.getAllRequestTypes();
        String addressID = addresses.get(selectedAddress).getId();
        String requestTypeID = types.get(selectedType).getId();

        try{
            validateNewRequestConstraint(addressID, requestTypeID);
            String content = newRequestGUI.getContent().getText();
            RequestDto requestDto = new RequestDto();
            requestDto.setContent(content);
            requestDto.setApproved(0);
            requestService.insertNewRequest(requestDto, addressID, requestTypeID);
            requestManagerGUI.getTableModel().addRow(new Object[] {requestDto.getId(), types.get(selectedType).getName(), requestDto.getFormattedDate(), addresses.get(selectedAddress).toString(), requestDto.getStatus()});
            updateTableModel();
            JOptionPane.showMessageDialog(newRequestGUI.getFrame(), "Request added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            newRequestGUI.getFrame().dispose();
            requestManagerGUI.getFrame().requestFocus();
        }catch(Exception e){
            JOptionPane.showMessageDialog(newRequestGUI.getFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void modifyRequest(){
        int selectedAddress = editRequestGUI.getAddressList().getSelectedIndex();
        int selectedType = editRequestGUI.getTypeList().getSelectedIndex();
        List<AddressDto> addresses = addressService.getUserAddresses(currentUser);
        List<RequestTypeDto> types = requestTypeService.getAllRequestTypes();
        String addressID = addresses.get(selectedAddress).getId();
        String requestTypeID = types.get(selectedType).getId();

        int selectedRequestIndex = requestManagerGUI.getRequestsTable().getSelectedRow();
        List<RequestDto> requests = requestService.getRequestsByUserID(currentUser.getId());
        RequestDto selectedRequest = requests.get(selectedRequestIndex);

        try{
            validateNewRequestConstraint(addressID, requestTypeID);
            String content = editRequestGUI.getContent().getText();
            selectedRequest.setContent(content);
            requestService.editRequest(selectedRequest, addressID, requestTypeID);
            updateTableModel();
            JOptionPane.showMessageDialog(editRequestGUI.getFrame(), "Request modified successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            editRequestGUI.getFrame().dispose();
            requestManagerGUI.getFrame().requestFocus();
        }catch(Exception e){
            JOptionPane.showMessageDialog(editRequestGUI.getFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void deleteRequest(){
        int index = requestManagerGUI.getRequestsTable().getSelectedRow();
        List<RequestDto> requests = requestService.getRequestsByUserID(currentUser.getId());
        DefaultTableModel tableModel = requestManagerGUI.getTableModel();
        requestService.deleteRequest(requests.get(index));
        updateTableModel();
    }

    public DefaultListModel<RequestTypeDto> initRequestTypeListModel(){
        DefaultListModel<RequestTypeDto> listModel = new DefaultListModel<RequestTypeDto>();
        List<RequestTypeDto> types = requestTypeService.getAllRequestTypes();
        for(RequestTypeDto t : types)
            listModel.addElement(t);
        return listModel;
    }

    public DefaultTableModel initRequestTableModel(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Content Preview");
        tableModel.addColumn("Type");
        tableModel.addColumn("Date");
        tableModel.addColumn("Address");
        tableModel.addColumn("Status");

        List<RequestDto> requestDtoList = requestService.getRequestsByUserID(currentUser.getId());
        for(RequestDto r : requestDtoList){
            tableModel.addRow(new Object[] {r.getContentPreview(), r.getType().getName(), r.getFormattedDate(), r.getOwner_address().toString(), r.getStatus()});
        }
        return tableModel;
    }

    public DefaultListModel<AddressDto> initAddressListModel(){
        DefaultListModel<AddressDto> listModel = new DefaultListModel<AddressDto>();
        List<AddressDto> addresses = addressService.getUserAddresses(currentUser);
        for(AddressDto a : addresses)
            listModel.addElement(a);
        return listModel;
    }

    private void updateTableModel(){
        List<RequestDto> requests = requestService.getRequestsByUserID(currentUser.getId());
        DefaultTableModel tableModel = requestManagerGUI.getTableModel();
        for(int i=tableModel.getRowCount()-1; i>=0; i--)
            tableModel.removeRow(i);
        for(RequestDto r : requests){
            tableModel.addRow(new Object[] {r.getContentPreview(), r.getType().getName(), r.getFormattedDate(), r.getOwner_address().toString(), r.getStatus()});
        }
    }

    public int getSelectedRequestAddressIndex(){
        RequestDto selectedRequest = getSelectedRequest();
        String selectedAddressID = selectedRequest.getOwner_address().getId();
        List<AddressDto> addresses = addressService.getUserAddresses(currentUser);
        int returnIndex=0;
        for(int i=0; i<addresses.size(); i++) {
            if (selectedAddressID.equals(addresses.get(i).getId()))
                returnIndex = i;
        }
        return returnIndex;
    }

    public int getSelectedRequestTypeIndex(){
        RequestDto selectedRequest = getSelectedRequest();
        String selectedTypeID = selectedRequest.getType().getId();
        List<RequestTypeDto> types = requestTypeService.getAllRequestTypes();
        int returnIndex=0;
        for(int i=0; i<types.size(); i++) {
            if (selectedTypeID.equals(types.get(i).getId()))
                returnIndex = i;
        }
        return returnIndex;
    }

    public RequestDto getSelectedRequest(){
        int selectedRequestIndex = requestManagerGUI.getRequestsTable().getSelectedRow();
        List<RequestDto> requests = requestService.getRequestsByUserID(currentUser.getId());
        RequestDto selectedRequest = requests.get(selectedRequestIndex);
        return selectedRequest;
    }

    public void validateEditableRequest(RequestDto request) throws RequestNotEditable {
        if(request.getApproved()!=0)
            throw new RequestNotEditable(ConstraintExceptionMessages.REQUEST_NOT_EDITABLE);
    }

    public void validateNewRequestConstraint(String addressID, String typeID) throws TooManyRequestsException {
        if(requestService.countCurrentYearRequestsByAddressAndType(addressID,typeID) >= 3)
            throw new TooManyRequestsException(ConstraintExceptionMessages.TOO_MANY_REQUESTS_CURRENT_YEAR);
    }
}
