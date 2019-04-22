package hu.due.document.management.ui.main.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import hu.due.document.management.dto.UserDTO;
import hu.due.document.management.ui.component.BaseView;

public interface UserAdministrationView extends BaseView {

    public Grid<UserDTO> getUserGrid();

    public VerticalLayout getRoot();

    public Button getBtnDeleteUser();

    public Button getBtnEditUser();

    public Button getBtnNewUser();

}
