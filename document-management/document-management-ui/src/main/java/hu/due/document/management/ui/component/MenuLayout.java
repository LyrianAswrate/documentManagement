package hu.due.document.management.ui.component;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MenuLayout extends HorizontalLayout {
    private static final long serialVersionUID = -2446665509919193243L;

    CssLayout contentArea = new CssLayout();

    CssLayout menuArea = new CssLayout();

    public MenuLayout() {
        setSizeFull();

        menuArea.setPrimaryStyleName(ValoTheme.MENU_ROOT);

        contentArea.setPrimaryStyleName("valo-content");
        contentArea.addStyleName("v-scrollable");
        contentArea.setSizeFull();

        addComponents(menuArea, contentArea);
        setExpandRatio(contentArea, 1);
    }

    public void addMenu(Component menu) {
        menu.addStyleName(ValoTheme.MENU_PART);
        menuArea.addComponent(menu);
    }

    public void displayMenu() {
        menuArea.setVisible(true);
    }

    public ComponentContainer getContentContainer() {
        return contentArea;
    }

    public void hideMenu() {
        menuArea.setVisible(false);
    }

}
