package hu.due.document.management.ui.main.view;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class UserAdministrationViewImpl extends CustomComponent implements UserAdministrationView {

    private static final long serialVersionUID = 1L;

    @Override
    public void buildUI() {
        // TODO Auto-generated method stub
        setCompositionRoot(new Label("UserAdministrationViewImpl"));
    }

}
