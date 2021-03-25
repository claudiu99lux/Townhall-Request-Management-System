package filterListeners;

import controller.AdminRequestManagerController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FilterByTypeListener implements ListSelectionListener {
    AdminRequestManagerController controller;

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    public FilterByTypeListener(AdminRequestManagerController controller){
        this.controller = controller;
    }
}
