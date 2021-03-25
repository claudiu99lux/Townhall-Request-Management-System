package filterListeners;

import controller.AdminRequestManagerController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FilterByDateListener implements ListSelectionListener {
    AdminRequestManagerController controller;

    @Override
    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
        if(!lsm.isSelectionEmpty()){
            int index = lsm.getMinSelectionIndex();
            controller.filterByDate(index);
        }
    }

    public FilterByDateListener(AdminRequestManagerController controller){
        this.controller = controller;
    }
}
