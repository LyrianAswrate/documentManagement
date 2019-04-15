package hu.due.document.management.ui.main.presenter;

import javax.annotation.PostConstruct;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;

import hu.due.document.management.ui.main.MainUI;
import hu.due.document.management.ui.main.view.DocumentListView;
import hu.due.document.management.ui.main.view.DocumentListViewImpl;

@SpringView(name = DocumentListPresenter.NAME, ui = MainUI.class)
public class DocumentListPresenter implements View {
    private static final long serialVersionUID = 1L;

    public static final String NAME = "";

    private DocumentListView view;

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
        view = new DocumentListViewImpl();
        view.buildUI();
    }

}
