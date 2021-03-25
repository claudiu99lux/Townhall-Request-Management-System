package controller;

import dto.RequestDto;
import dto.RequestTypeDto;
import entity.Request;
import entity.RequestType;
import filterListeners.FilterByDateListener;
import filterListeners.FilterByTypeListener;
import service.RequestService;
import service.RequestTypeService;
import ui.AdminRequestManagerGUI;
import ui.FilterPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdminRequestManagerController {
    private AdminRequestManagerGUI adminRequestManagerGUI;
    private RequestService requestService;
    private RequestTypeService requestTypeService;
    private FilterPanel filterPanel;

    public AdminRequestManagerController(){
        requestService = new RequestService();
        requestTypeService = new RequestTypeService();
        adminRequestManagerGUI = new AdminRequestManagerGUI(this);
    }

    public void approveSelectedRequest(){
        int selectionIndex = adminRequestManagerGUI.getRequestsTable().getSelectedRow();
        List<RequestDto> requests = requestService.getAllRequests();
        requests.get(selectionIndex).setApproved(1);
        requestService.updateRequest(requests.get(selectionIndex));
        adminRequestManagerGUI.getRequestsTable().setValueAt("Approved", selectionIndex,7);
    }

    public void declineSelectedRequest(){
        int selectionIndex = adminRequestManagerGUI.getRequestsTable().getSelectedRow();
        List<RequestDto> requests = requestService.getAllRequests();
        requests.get(selectionIndex).setApproved(2);
        requestService.updateRequest(requests.get(selectionIndex));
        adminRequestManagerGUI.getRequestsTable().setValueAt("Declined", selectionIndex,7);
    }

    public void deleteSelectedRequest(){
        int selectionIndex = adminRequestManagerGUI.getRequestsTable().getSelectedRow();
        List<RequestDto> requests = requestService.getAllRequests();
        requestService.deleteRequest(requests.get(selectionIndex));
        updateTableModel();
    }

    public void openFilterPanel(){
        filterPanel = new FilterPanel(this);
    }

    public void resetFilters(){
        filterPanel.getDateList().clearSelection();
        filterPanel.getTypeList().clearSelection();
        updateTableModel();
    }

    public void filterByDate(int selectedDateIndex){
        filterPanel.getTypeList().clearSelection();
        Set<LocalDate> dates = requestService.getRequestsDateSet();
        List<LocalDate> dateList = new ArrayList<LocalDate>(dates);
        LocalDate selectedDate = dateList.get(selectedDateIndex);
        List<RequestDto> requests = requestService.getRequestsByDate(selectedDate);
        DefaultTableModel tableModel = adminRequestManagerGUI.getTableModel();
        for(int i=tableModel.getRowCount()-1; i>=0; i--)
            tableModel.removeRow(i);
        for(RequestDto r : requests){
            tableModel.addRow(new Object[] {r.getOwner_address().getUser().getId(), r.getOwner_address().getUser().getFirstName(), r.getOwner_address().getUser().getLastName(), r.getContentPreview(), r.getType().getName(), r.getFormattedDate(), r.getOwner_address().toString(), r.getStatus()});
        }
    }

    public DefaultTableModel initRequestTableModel(){
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("User ID");
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Content Preview");
        tableModel.addColumn("Type");
        tableModel.addColumn("Date");
        tableModel.addColumn("Address");
        tableModel.addColumn("Status");

        List<RequestDto> requestDtoList = requestService.getAllRequests();
        for(RequestDto r : requestDtoList){
            tableModel.addRow(new Object[] {r.getOwner_address().getUser().getId(), r.getOwner_address().getUser().getFirstName(), r.getOwner_address().getUser().getLastName(), r.getContentPreview(), r.getType().getName(), r.getFormattedDate(), r.getOwner_address().toString(), r.getStatus()});
        }
        return tableModel;
    }

    public DefaultListModel<String> initDateListModel(){
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        Set<LocalDate> dates = requestService.getRequestsDateSet();
        for(LocalDate d : dates){
            listModel.addElement(d.format(DateTimeFormatter.ofPattern("dd-MMM-YYYY")));
        }
        return listModel;
    }

    public DefaultListModel<RequestTypeDto> initTypeListModel(){
        DefaultListModel<RequestTypeDto> listModel = new DefaultListModel<RequestTypeDto>();
        List<RequestTypeDto> types = requestTypeService.getAllRequestTypes();
        for(RequestTypeDto t : types)
            listModel.addElement(t);
        return listModel;
    }

    public ListSelectionModel initDateSelectionModel(JList dateList){
        ListSelectionModel model = dateList.getSelectionModel();
        model.addListSelectionListener(new FilterByDateListener(this));
        return model;
    }

    public ListSelectionModel initTypeSelectionModel(JList typeList){
        ListSelectionModel model = typeList.getSelectionModel();
        model.addListSelectionListener(new FilterByTypeListener(this));
        return model;
    }

    private void updateTableModel(){
        List<RequestDto> requests = requestService.getAllRequests();
        DefaultTableModel tableModel = adminRequestManagerGUI.getTableModel();
        for(int i=tableModel.getRowCount()-1; i>=0; i--)
            tableModel.removeRow(i);
        for(RequestDto r : requests){
            tableModel.addRow(new Object[] {r.getOwner_address().getUser().getId(), r.getOwner_address().getUser().getFirstName(), r.getOwner_address().getUser().getLastName(), r.getContentPreview(), r.getType().getName(), r.getFormattedDate(), r.getOwner_address().toString(), r.getStatus()});
        }
    }
}
