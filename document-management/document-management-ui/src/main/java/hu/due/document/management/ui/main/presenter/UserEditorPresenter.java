package hu.due.document.management.ui.main.presenter;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;

import hu.due.document.management.dto.UserDTO;
import hu.due.document.management.enums.AppRole;
import hu.due.document.management.service.user.UserService;
import hu.due.document.management.ui.main.MainUI;
import hu.due.document.management.ui.main.view.UserProfileView;
import hu.due.document.management.ui.main.view.UserProfileViewImpl;

@SpringView(name = UserEditorPresenter.NAME, ui = MainUI.class)
public class UserEditorPresenter implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "userEditor";

    private UserProfileView view;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    private UserDTO user;

    @Override
    public void enter(ViewChangeEvent event) {
        Object userId = UI.getCurrent().getSession().getAttribute("userId");
        if (userId instanceof Long) {
            user = userService.getUserById((Long) userId);
            UI.getCurrent().getSession().setAttribute("userId", null);
        } else {
            user = new UserDTO();
        }
        loadUserData();
    }

    private void loadUserData() {
        view.getTfUsername().setReadOnly(false);
        view.getCbRole().setReadOnly(false);

        view.getTfUsername().setValue("");
        view.getTfFullname().setValue("");
        view.getTfEmail().setValue("");
        view.getPfPassword().setValue("");
        view.getPfRePassword().setValue("");
        view.getCbRole().clear();
        view.getChbEnabled().setValue(false);

        if (user.getId() != null) {
            view.getTfUsername().setValue(user.getUsername());
            view.getTfFullname().setValue(user.getFullname());
            view.getTfEmail().setValue(user.getEmail());
            view.getCbRole().setValue(user.getRole());
            view.getChbEnabled().setValue(user.getEnabled());

            view.getTfUsername().setReadOnly(true);
            view.getCbRole().setReadOnly(true);
        }
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    @PostConstruct
    public void postConstruct() {
        view = new UserProfileViewImpl("Felhasználó szerkesztése", true, true);
        view.buildUI();

        view.getCbRole().setItems(AppRole.values());

        view.getBtnSave().addClickListener(event -> onSaveClicked());
        view.getBtnCancel().addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(UserAdministrationPresenter.NAME));
    }

    private void onSaveClicked() {
        boolean valid = true;
        StringBuilder sb = new StringBuilder();

        if (StringUtils.trimToNull(view.getTfUsername().getValue()) != null) {
            user.setUsername(view.getTfUsername().getValue());
        } else {
            valid = false;
            sb.append("A felhasználói név nem lehet üres! \n");
        }

        if (StringUtils.trimToNull(view.getTfFullname().getValue()) != null) {
            user.setFullname(view.getTfFullname().getValue());
        } else {
            valid = false;
            sb.append("A felhasználó neve nem lehet üres! \n");
        }

        if (view.getCbRole() != null) {
            user.setRole(view.getCbRole().getValue());
        } else {
            valid = false;
            sb.append("A felhasználó jogosúltsága nem lehet üres! \n");
        }

        // lehet erre is kötni formai validációt de nem érdekkel most :P
        user.setEmail(view.getTfEmail().getValue());

        String password = view.getPfPassword().getValue();
        String rePassword = view.getPfRePassword().getValue();

        if (((user.getPassword() == null) && (StringUtils.trimToNull(password) == null)) || (StringUtils.trimToNull(rePassword) == null)) {
            valid = false;
            sb.append("A jelszó megadássa kötelező!");
        }

        if ((StringUtils.trimToNull(password) != null) && (StringUtils.trimToNull(rePassword) != null)) {
            if (StringUtils.equals(password, rePassword)) {
                user.setPassword(encoder.encode(view.getPfPassword().getValue()));
            } else {
                valid = false;
                sb.append("A kétt jelszó nem egyezik egymással!");
            }
        }

        if (view.getChbEnabled().getValue() == null) {
            user.setEnabled(false);
        } else {
            user.setEnabled(view.getChbEnabled().getValue());
        }

        if (valid) {
            userService.save(user);
            UI.getCurrent().getNavigator().navigateTo(UserAdministrationPresenter.NAME);
            Notification.show("Sikeres mentés!", Type.HUMANIZED_MESSAGE);
        } else {
            Notification.show("Validációs hiba", sb.toString(), Type.ERROR_MESSAGE);
        }
    }

}
