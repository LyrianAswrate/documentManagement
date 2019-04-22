package hu.due.document.management.ui.main.view;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import hu.due.document.management.dto.UserDTO;

public class UserAdministrationViewImpl extends CustomComponent implements UserAdministrationView {

    private static final long serialVersionUID = 1L;

    private VerticalLayout root;
    private Grid<UserDTO> userGrid;

    private Button btnNewUser;
    private Button btnEditUser;
    private Button btnDeleteUser;

    @Override
    public void buildUI() {
        Label headerLabel = new Label("<h2>Felhasználók adminisztrálása</h2>", ContentMode.HTML);

        userGrid = buildGrid();
        HorizontalLayout hlButtonContainer = buildButtonContainer();

        root = createContent(headerLabel, hlButtonContainer, userGrid);
        root.setExpandRatio(userGrid, 1f);
        setCompositionRoot(root);
    }

    private HorizontalLayout buildButtonContainer() {
        HorizontalLayout container = new HorizontalLayout();

        btnNewUser = new Button("Új");
        btnEditUser = new Button("Szerkesztés");
        btnDeleteUser = new Button("Törlés");

        btnNewUser.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnEditUser.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnDeleteUser.addStyleName(ValoTheme.BUTTON_DANGER);

        btnEditUser.setEnabled(false);
        btnDeleteUser.setEnabled(false);

        container.addComponent(btnNewUser);
        container.addComponent(btnEditUser);
        container.addComponent(btnDeleteUser);

        container.setComponentAlignment(btnNewUser, Alignment.MIDDLE_LEFT);
        container.setComponentAlignment(btnEditUser, Alignment.MIDDLE_LEFT);
        container.setComponentAlignment(btnDeleteUser, Alignment.MIDDLE_LEFT);
        return container;
    }

    private Grid<UserDTO> buildGrid() {
        Grid<UserDTO> grid = new Grid<>();
        grid.setSizeFull();
        grid.addColumn(UserDTO::getId).setCaption("Azonosító").setSortable(false).setResizable(false);
        grid.addColumn(UserDTO::getUsername).setCaption("Felhasználó név").setSortable(false).setResizable(false);
        grid.addColumn(UserDTO::getFullname).setCaption("Teljes név").setSortable(false).setResizable(false);
        grid.addColumn(UserDTO::getEmail).setCaption("E-mail").setSortable(false).setResizable(false);
        grid.addColumn(dto -> dto.getRole().getCaption()).setCaption("Jogosúltság").setSortable(false).setResizable(false);
        grid.addColumn(dto -> dto.getEnabled() ? "Igen" : "Nem").setCaption("Fiók aktív?").setSortable(false).setResizable(false);
        return grid;
    }

    private VerticalLayout createContent(Component... component) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setSpacing(true);
        layout.addComponents(component);

        return layout;
    }

    @Override
    public Grid<UserDTO> getUserGrid() {
        return userGrid;
    }

    @Override
    public VerticalLayout getRoot() {
        return root;
    }

    @Override
    public Button getBtnDeleteUser() {
        return btnDeleteUser;
    }

    @Override
    public Button getBtnEditUser() {
        return btnEditUser;
    }

    @Override
    public Button getBtnNewUser() {
        return btnNewUser;
    }

}
