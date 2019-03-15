package hu.due.document.management.ui.login.presenter;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;

import hu.due.document.management.ui.login.LoginUI;
import hu.due.document.management.ui.login.view.LoginView;
import hu.due.document.management.ui.login.view.LoginViewImpl;

@SpringView(name = LoginPresenter.NAME, ui = LoginUI.class)
public class LoginPresenter implements View {

	private static final long serialVersionUID = 1L;

	public static final String NAME = "";

	private LoginView view;

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO
	}

	@Override
	public Component getViewComponent() {
		return view;
	}

	@PostConstruct
	public void postConstruct() {
		view = new LoginViewImpl();
		view.buildUI();

		view.getLoginButton().addClickListener(this::onLoginAction);
	}

	private void onLoginAction(ClickEvent event) {
		// TODO Auto-generated method stub
	}

}
