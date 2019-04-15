package hu.due.document.management.ui.main;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import hu.due.document.management.enums.AppRole;
import hu.due.document.management.ui.component.MenuLayout;
import hu.due.document.management.ui.main.presenter.DocumentListPresenter;
import hu.due.document.management.ui.main.presenter.UserAdministrationPresenter;
import hu.due.document.management.ui.main.presenter.UserProfilePresenter;

@SpringUI(path = MainUI.PATH)
@Widgetset("hu.due.document.management.widgetset.DocumentManagementWidgetSet")
@Theme("documentManagement")
public class MainUI extends UI {

    private static final long serialVersionUID = 1L;

    public static final String PATH = "/main";
    public static final String LOGOUT_PATH = "/logout";

    @Autowired
    private ApplicationContext appCtx;

    private SpringNavigator navigator;

    private CssLayout menu;
    private MenuLayout root;
    private CssLayout menuItemsLayout;
    private ComponentContainer viewDisplay;
    private LinkedHashMap<String, String> menuItems;

    @Override
    protected void init(VaadinRequest request) {
        checkSessionEnd();

        menu = new CssLayout();
        root = new MenuLayout();
        menuItemsLayout = new CssLayout();
        viewDisplay = root.getContentContainer();
        menuItems = new LinkedHashMap<>();

        if (getPage().getWebBrowser().isIE() && (getPage().getWebBrowser().getBrowserMajorVersion() == 9)) {
            menu.setWidth("320px");
        }

        root.addMenu(buildMenu());
        root.setWidth("100%");

        setContent(root);
        addStyleName(ValoTheme.UI_WITH_MENU);

        setSizeFull();

        navigator = new SpringNavigator();
        appCtx.getAutowireCapableBeanFactory().autowireBean(navigator);
        navigator.init(this, viewDisplay);
        navigator.navigateTo(DocumentListPresenter.NAME);

        addClickListener(new MouseEvents.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
                // Letiltja a jobbclickes context menüt.
            }
        });
    }

    private boolean userHasRole(AppRole role) {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(pre -> pre.getAuthority().equals(role.name()));
    }

    private void checkSessionEnd() {
        SecurityContext context = SecurityContextHolder.getContext();
        if ((context != null) && (context.getAuthentication() != null) && !context.getAuthentication().getAuthorities().isEmpty()) {
            if (!userHasRole(AppRole.ADMIN) && !userHasRole(AppRole.USER)) {
                logout();
            }
        } else {
            logout();
        }
    }

    private Component buildMenu() {
        menuItems.put(DocumentListPresenter.NAME, "Dokumentumok");
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(pre -> pre.getAuthority().equals(AppRole.ADMIN.name()))) {
            menuItems.put(UserAdministrationPresenter.NAME, "Felhasználók adminisztrálása");
        }
        menuItems.put(UserProfilePresenter.NAME, "Felhasználó");

        HorizontalLayout hlContainer = new HorizontalLayout();
        Label appTitleLabel = new Label("DOKUMENTUM KEZELŐ ÉS IKTATÓ RENDSZER", ContentMode.HTML);
        appTitleLabel.setWidth("100%");
        appTitleLabel.addStyleName("appTitleLabel");
        hlContainer.addComponent(appTitleLabel);
        hlContainer.setComponentAlignment(appTitleLabel, Alignment.MIDDLE_CENTER);
        hlContainer.setMargin(true);
        menu.addComponent(hlContainer);

        menuItemsLayout.setPrimaryStyleName("valo-menuitems");
        menu.addComponent(menuItemsLayout);
        for (Entry<String, String> item : menuItems.entrySet()) {
            Button btnMenu = new Button(item.getValue(), e -> MainUI.this.getUI().getNavigator().navigateTo(item.getKey()));
            btnMenu.setCaptionAsHtml(true);
            btnMenu.setPrimaryStyleName(ValoTheme.MENU_ITEM);
            menuItemsLayout.addComponent(btnMenu);
        }

        Button btnLogoutMenu = new Button("Kijelentkezés", e -> ConfirmDialog.show(UI.getCurrent(), "Kijelentkezés",
                "Biztos hogy ki szeretne jelentkezni?", "Igen", "Mégsem", () -> logout()));
        btnLogoutMenu.setCaptionAsHtml(true);
        btnLogoutMenu.setPrimaryStyleName(ValoTheme.MENU_ITEM);
        menuItemsLayout.addComponent(btnLogoutMenu);

        return menu;
    }

    private void logout() {
        getUI().getSession().close();
        getUI().getPage().setLocation(MainUI.LOGOUT_PATH);
    }

}
