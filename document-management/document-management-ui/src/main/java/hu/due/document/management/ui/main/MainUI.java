package hu.due.document.management.ui.main;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.event.MouseEvents;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import hu.due.document.management.ui.component.MenuLayout;

@SpringUI(path = MainUI.PATH)
@Widgetset("hu.due.document.management.widgetset.DocumentManagementWidgetSet")
@Theme("documentManagement")
public class MainUI extends UI {

    private static final long serialVersionUID = 1L;

    public static final String PATH = "/main";

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
        navigator.navigateTo("MainPresenter");

        addClickListener(new MouseEvents.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
                // Letiltja a jobbclickes context menüt.
            }
        });
    }

    private Component buildMenu() {
        // TODO
        menuItems.put("MainPresenter", "MainPresenter_LABEL");
        menuItems.put("MainPresenter2", "MainPresenter2_LABEL");
        menuItems.put("MainPresenter3", "MainPresenter3_LABEL");

        Label labelMenu = new Label("Menu label", ContentMode.HTML);
        labelMenu.setWidth("100%");
        labelMenu.addStyleName("label-text-center");
        menu.addComponent(labelMenu);

        menuItemsLayout.setPrimaryStyleName("valo-menuitems");
        menu.addComponent(menuItemsLayout);
        for (Entry<String, String> item : menuItems.entrySet()) {
            Button btnMenu = new Button(item.getValue(), e -> MainUI.this.getUI().getNavigator().navigateTo(item.getKey()));
            btnMenu.setCaptionAsHtml(true);
            btnMenu.setPrimaryStyleName(ValoTheme.MENU_ITEM);
            menuItemsLayout.addComponent(btnMenu);
        }

        return menu;
    }

}
