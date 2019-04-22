package hu.due.document.management.ui.main.view;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import hu.due.document.management.enums.AppRole;

public class UserProfileViewImpl extends CustomComponent implements UserProfileView {

    private static final long serialVersionUID = 1L;

    private final String headerCaption;
    private final boolean showBtnSave;
    private final boolean showBtnCancel;

    private VerticalLayout root;

    private TextField tfUsername;
    private PasswordField pfPassword;
    private PasswordField pfRePassword;
    private TextField tfFullname;
    private TextField tfEmail;
    private ComboBox<AppRole> cbRole;
    private CheckBox chbEnabled;

    private Button btnSave;
    private Button btnCancel;

    public UserProfileViewImpl(String headerCaption, boolean showBtnSave, boolean showBtnCancel) {
        this.headerCaption = headerCaption;
        this.showBtnSave = showBtnSave;
        this.showBtnCancel = showBtnCancel;
    }

    @Override
    public void buildUI() {
        Label headerLabel = new Label("<h2>" + headerCaption + "</h2>", ContentMode.HTML);

        tfUsername = new TextField("Felhasználó név");
        pfPassword = new PasswordField("Jelszó");
        pfRePassword = new PasswordField("Jelszó ismét");
        tfFullname = new TextField("Teljes név");
        tfEmail = new TextField("E-mail");
        cbRole = new ComboBox<>("Jogosúltság");
        chbEnabled = new CheckBox("Fiók aktív?");

        cbRole.setItemCaptionGenerator(AppRole::getCaption);

        HorizontalLayout buttonComponent = new HorizontalLayout();
        buttonComponent.setMargin(true);

        btnSave = new Button("Mentés");
        btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnSave.setVisible(showBtnSave);

        btnCancel = new Button("Mégsem");
        btnCancel.setVisible(showBtnCancel);

        buttonComponent.addComponent(btnSave);
        buttonComponent.addComponent(btnCancel);

        buttonComponent.setComponentAlignment(btnSave, Alignment.MIDDLE_LEFT);
        buttonComponent.setComponentAlignment(btnCancel, Alignment.MIDDLE_LEFT);

        root = new VerticalLayout();
        root.setMargin(true);
        root.addComponent(headerLabel);
        root.addComponent(tfUsername);
        root.addComponent(pfPassword);
        root.addComponent(pfRePassword);
        root.addComponent(tfFullname);
        root.addComponent(tfEmail);
        root.addComponent(cbRole);
        root.addComponent(chbEnabled);
        root.addComponent(buttonComponent);

        root.setComponentAlignment(headerLabel, Alignment.TOP_LEFT);
        root.setComponentAlignment(tfUsername, Alignment.TOP_LEFT);
        root.setComponentAlignment(pfPassword, Alignment.TOP_LEFT);
        root.setComponentAlignment(pfRePassword, Alignment.TOP_LEFT);
        root.setComponentAlignment(tfFullname, Alignment.TOP_LEFT);
        root.setComponentAlignment(tfEmail, Alignment.TOP_LEFT);
        root.setComponentAlignment(cbRole, Alignment.TOP_LEFT);
        root.setComponentAlignment(chbEnabled, Alignment.TOP_LEFT);
        root.setComponentAlignment(buttonComponent, Alignment.TOP_LEFT);
        setCompositionRoot(root);
    }

    @Override
    public TextField getTfUsername() {
        return tfUsername;
    }

    @Override
    public PasswordField getPfPassword() {
        return pfPassword;
    }

    @Override
    public PasswordField getPfRePassword() {
        return pfRePassword;
    }

    @Override
    public TextField getTfFullname() {
        return tfFullname;
    }

    @Override
    public TextField getTfEmail() {
        return tfEmail;
    }

    @Override
    public ComboBox<AppRole> getCbRole() {
        return cbRole;
    }

    @Override
    public CheckBox getChbEnabled() {
        return chbEnabled;
    }

    @Override
    public Button getBtnSave() {
        return btnSave;
    }

    @Override
    public Button getBtnCancel() {
        return btnCancel;
    }

}
