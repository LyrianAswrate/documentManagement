package hu.due.document.management.ui.main.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

import hu.due.document.management.enums.AppRole;
import hu.due.document.management.ui.component.BaseView;

public interface UserProfileView extends BaseView {

    public TextField getTfUsername();

    public PasswordField getPfPassword();

    public PasswordField getPfRePassword();

    public TextField getTfFullname();

    public TextField getTfEmail();

    public ComboBox<AppRole> getCbRole();

    public CheckBox getChbEnabled();

    public Button getBtnSave();

    public Button getBtnCancel();

}
