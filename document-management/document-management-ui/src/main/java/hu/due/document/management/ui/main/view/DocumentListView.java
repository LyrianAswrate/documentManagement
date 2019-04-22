package hu.due.document.management.ui.main.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import hu.due.document.management.dto.DocumentDTO;
import hu.due.document.management.ui.component.BaseView;

public interface DocumentListView extends BaseView {

    public Grid<DocumentDTO> getDocumentGrid();

    public VerticalLayout getRoot();

    public Button getBtnDeleteDocument();

    public Button getBtnEditDocument();

    public Button getBtnNewDocument();

}
