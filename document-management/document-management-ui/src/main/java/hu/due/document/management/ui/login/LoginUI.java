package hu.due.document.management.ui.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.UI;

import hu.due.document.management.ui.login.presenter.LoginPresenter;

@SpringUI(path = LoginUI.PATH)
@Widgetset("hu.due.document.management.widgetset.DocumentManagementWidgetSet")
@Theme("documentManagement")
public class LoginUI extends UI {

	private static final long serialVersionUID = 1L;

	public static final String PATH = "/login";

	@Autowired
	private ApplicationContext appCtx;

	private SpringNavigator navigator;

	@Override
	protected void init(VaadinRequest request) {
		setSizeFull();
		navigator = new SpringNavigator();
		appCtx.getAutowireCapableBeanFactory().autowireBean(navigator);
		navigator.init(this, this);
		navigator.navigateTo(LoginPresenter.NAME);
	}

}
