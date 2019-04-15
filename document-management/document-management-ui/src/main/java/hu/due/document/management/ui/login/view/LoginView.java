package hu.due.document.management.ui.login.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import hu.due.document.management.ui.component.BaseView;

public interface LoginView extends BaseView {

    public TextField getUsername();

    public PasswordField getPassword();

    public Button getLoginButton();

}
