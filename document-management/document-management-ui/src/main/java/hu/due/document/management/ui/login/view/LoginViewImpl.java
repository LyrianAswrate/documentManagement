package hu.due.document.management.ui.login.view;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginViewImpl extends CustomComponent implements LoginView {

    private static final long serialVersionUID = 1L;

    private TextField username;
    private PasswordField password;
    private Button loginButton;

    @Override
    public void buildUI() {
        setSizeFull();

        username = new TextField("Felhasználó név");
        username.setWidth("300px");
        username.setRequiredIndicatorVisible(true);

        password = new PasswordField("Jelszó");
        password.setWidth("300px");
        password.setRequiredIndicatorVisible(true);

        loginButton = new Button("Bejelentkezés");

        VerticalLayout fields = new VerticalLayout(username, password, loginButton);
        fields.setCaptionAsHtml(true);
        fields.setCaption("<h1>Bejelentkezés</h1>");
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        setCompositionRoot(viewLayout);
    }

    @Override
    public TextField getUsername() {
        return username;
    }

    @Override
    public PasswordField getPassword() {
        return password;
    }

    @Override
    public Button getLoginButton() {
        return loginButton;
    }

}
