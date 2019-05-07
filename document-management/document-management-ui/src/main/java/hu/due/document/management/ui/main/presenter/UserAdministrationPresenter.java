package hu.due.document.management.ui.main.presenter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.addon.pagination.Pagination;
import com.vaadin.addon.pagination.PaginationResource;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.UI;

import hu.due.document.management.dto.CustomePageDTO;
import hu.due.document.management.dto.UserDTO;
import hu.due.document.management.service.security.ApplicationUserDetails;
import hu.due.document.management.service.user.UserService;
import hu.due.document.management.ui.main.MainUI;
import hu.due.document.management.ui.main.view.UserAdministrationView;
import hu.due.document.management.ui.main.view.UserAdministrationViewImpl;

@SpringView(name = UserAdministrationPresenter.NAME, ui = MainUI.class)
public class UserAdministrationPresenter implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "userAdministration";

    private UserAdministrationView view;

    @Autowired
    private UserService userService;

    private Pagination pagination;

    @Override
    public void enter(ViewChangeEvent event) {
        loadGridPage(0, 10);
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    @PostConstruct
    public void postConstruct() {
        view = new UserAdministrationViewImpl();
        view.buildUI();

        pagination = createPagination(findAll(0, 10).getTotalElements(), 1, 10);
        pagination.setItemsPerPageVisible(false);
        pagination.setItemsPerPageEnabled(false);

        pagination.addPageChangeListener(event -> loadGridPage(event.pageIndex(), event.limit()));

        view.getRoot().addComponent(pagination);
        view.getUserGrid().setSelectionMode(SelectionMode.SINGLE);
        view.getUserGrid().addSelectionListener(event -> {
            if (event.getFirstSelectedItem().isPresent()
                    && (((ApplicationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId()
                            .longValue() != event.getFirstSelectedItem().get().getId().longValue())) {
                view.getBtnEditUser().setEnabled(true);
                view.getBtnDeleteUser().setEnabled(true);
            } else {
                view.getBtnEditUser().setEnabled(false);
                view.getBtnDeleteUser().setEnabled(false);
            }
        });

        view.getBtnNewUser().addClickListener(event -> onNewUserClicked());
        view.getBtnEditUser().addClickListener(event -> onEditUserClicked());
        view.getBtnDeleteUser().addClickListener(event -> onDeleteUserClicked());
    }

    private void onDeleteUserClicked() {
        ConfirmDialog.show(UI.getCurrent(), "Felhasználó törlése", "Biztos hogy szeretné töröli a felhasználót?", "Igen", "Mégsem",
                () -> onDeleteConfirmedClicked());
    }

    private void onDeleteConfirmedClicked() {
        userService.deleteUser(view.getUserGrid().getSelectionModel().getFirstSelectedItem().get().getId());
        loadGridPage(0, 10);
    }

    private void loadGridPage(int page, int size) {
        CustomePageDTO<UserDTO> users = findAll(page, size);
        pagination.setTotalCount(users.getTotalElements());
        view.getUserGrid().setItems(users.getContent());
        view.getUserGrid().scrollToStart();
    }

    private void onEditUserClicked() {
        UI.getCurrent().getSession().setAttribute("userId", view.getUserGrid().getSelectionModel().getFirstSelectedItem().get().getId());
        UI.getCurrent().getNavigator().navigateTo(UserEditorPresenter.NAME);
    }

    private void onNewUserClicked() {
        UI.getCurrent().getSession().setAttribute("userId", null);
        UI.getCurrent().getNavigator().navigateTo(UserEditorPresenter.NAME);
    }

    private CustomePageDTO<UserDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        CustomePageDTO<UserDTO> users = userService.findAll(pageable);
        return users;
    }

    private Pagination createPagination(long total, int page, int limit) {
        PaginationResource paginationResource = PaginationResource.newBuilder().setTotal(total).setPage(page).setLimit(limit).build();
        Pagination pagination = new Pagination(paginationResource);
        return pagination;
    }

}
