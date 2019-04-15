package hu.due.document.management.ui.main.presenter;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;

import hu.due.document.management.ui.main.MainUI;
import hu.due.document.management.ui.main.view.UserAdministrationView;
import hu.due.document.management.ui.main.view.UserAdministrationViewImpl;

@SpringView(name = UserAdministrationPresenter.NAME, ui = MainUI.class)
public class UserAdministrationPresenter implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "userAdministration";

    private UserAdministrationView view;

    @Override
    public void enter(ViewChangeEvent event) {
        // nope
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    @PostConstruct
    public void postConstruct() {
        view = new UserAdministrationViewImpl();
        view.buildUI();
    }

}