package hu.due.document.management.ui.main.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import hu.due.document.management.dto.DocumentDTO;

public class DocumentListViewImpl extends CustomComponent implements DocumentListView {

    private static final long serialVersionUID = 1L;

    @Override
    public void buildUI() {
        // TODO Auto-generated method stub

        setCompositionRoot(new Label("DocumentListViewImpl"));
    }

    @Override
    public Grid<DocumentDTO> getDocumentGrid() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public VerticalLayout getRoot() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Button getBtnDeleteDocument() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Button getBtnEditDocument() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Button getBtnNewDocument() {
        // TODO Auto-generated method stub
        return null;
    }

}
