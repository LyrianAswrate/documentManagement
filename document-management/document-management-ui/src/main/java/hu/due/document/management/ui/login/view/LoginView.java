package hu.due.document.management.ui.login.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public interface LoginView extends HasComponents {

	public void buildUI();

	public TextField getUsername();

	public PasswordField getPassword();

	public Button getLoginButton();

}
