package hu.due.document.management.ui.main.presenter;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import hu.due.document.management.dto.UserDTO;
import hu.due.document.management.enums.AppRole;
import hu.due.document.management.service.security.ApplicationUserDetails;
import hu.due.document.management.service.user.UserService;
import hu.due.document.management.ui.main.MainUI;
import hu.due.document.management.ui.main.view.UserProfileView;
import hu.due.document.management.ui.main.view.UserProfileViewImpl;

@SpringView(name = UserProfilePresenter.NAME, ui = MainUI.class)
public class UserProfilePresenter implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "userProfile";

    private UserProfileView view;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder encoder;

    private ApplicationUserDetails appUserDetails;

    @Override
    public void enter(ViewChangeEvent event) {
        loadLogedInUserData();
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    @PostConstruct
    public void postConstruct() {
        view = new UserProfileViewImpl("Felhasználói adatok", true, false);
        view.buildUI();

        view.getCbRole().setItems(AppRole.values());

        view.getBtnSave().addClickListener(event -> onSaveClicked());
    }

    private void onSaveClicked() {
        boolean valid = true;
        StringBuilder sb = new StringBuilder();

        UserDTO dto = appUserDetails.getUser();

        if (StringUtils.trimToNull(view.getTfFullname().getValue()) != null) {
            dto.setFullname(view.getTfFullname().getValue());
        } else {
            valid = false;
            sb.append("A felhasználó neve nem lehet üres! \n");
        }

        // lehet erre is kötni formai validációt de nem érdekkel most :P
        dto.setEmail(view.getTfEmail().getValue());

        String password = view.getPfPassword().getValue();
        String rePassword = view.getPfRePassword().getValue();
        if ((StringUtils.trimToNull(password) != null) && (StringUtils.trimToNull(rePassword) != null)) {
            if (StringUtils.equals(password, rePassword)) {
                dto.setPassword(encoder.encode(view.getPfPassword().getValue()));
            } else {
                valid = false;
                sb.append("A kétt jelszó nem egyezik egymással!");
            }
        }

        if (valid) {
            userService.save(dto);
            loadLogedInUserData();
            Notification.show("Sikeres mentés!", Type.HUMANIZED_MESSAGE);
        } else {
            Notification.show("Validációs hiba", sb.toString(), Type.ERROR_MESSAGE);
        }
    }

    private void loadLogedInUserData() {
        view.getTfUsername().setReadOnly(false);
        view.getCbRole().setReadOnly(false);
        view.getChbEnabled().setReadOnly(false);

        view.getTfUsername().setValue("");
        view.getTfFullname().setValue("");
        view.getTfEmail().setValue("");
        view.getPfPassword().setValue("");
        view.getPfRePassword().setValue("");
        view.getCbRole().clear();
        view.getChbEnabled().setValue(false);

        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails instanceof ApplicationUserDetails) {
            appUserDetails = (ApplicationUserDetails) userDetails;

            view.getTfUsername().setValue(appUserDetails.getUsername());
            view.getTfFullname().setValue(appUserDetails.getUser().getFullname());
            view.getTfEmail().setValue(appUserDetails.getUser().getEmail());
            view.getCbRole().setValue(appUserDetails.getUser().getRole());
            view.getChbEnabled().setValue(appUserDetails.isEnabled());

        }

        view.getTfUsername().setReadOnly(true);
        view.getCbRole().setReadOnly(true);
        view.getChbEnabled().setReadOnly(true);
    }

}
