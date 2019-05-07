package hu.due.document.management.ui.main.view;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import hu.due.document.management.dto.DocumentDTO;

public class DocumentListViewImpl extends CustomComponent implements DocumentListView {

    private static final long serialVersionUID = 1L;

    private VerticalLayout root;
    private TextField serachField;
    private Grid<DocumentDTO> documentGrid;

    private Button btnSearch;
    private Button btnNewDocument;
    private Button btnEditDocument;
    private Button btnDeleteDocument;

    @Override
    public void buildUI() {
        Label headerLabel = new Label("<h2>Dokumentumok</h2>", ContentMode.HTML);

        documentGrid = buildGrid();
        HorizontalLayout hlButtonContainer = buildButtonContainer();
        HorizontalLayout hlSearchContainer = buildSearchContainer();

        root = createContent(headerLabel, hlSearchContainer, hlButtonContainer, documentGrid);
        root.setExpandRatio(documentGrid, 1f);
        setCompositionRoot(root);
    }

    private HorizontalLayout buildSearchContainer() {
        HorizontalLayout container = new HorizontalLayout();

        serachField = new TextField();
        serachField.setWidth("400px");

        btnSearch = new Button(VaadinIcons.SEARCH);
        btnSearch.addStyleName(ValoTheme.BUTTON_PRIMARY);

        container.addComponent(serachField);
        container.addComponent(btnSearch);

        container.setComponentAlignment(serachField, Alignment.MIDDLE_LEFT);
        container.setComponentAlignment(btnSearch, Alignment.MIDDLE_LEFT);
        return container;
    }

    private HorizontalLayout buildButtonContainer() {
        HorizontalLayout container = new HorizontalLayout();

        btnNewDocument = new Button("Új");
        btnEditDocument = new Button("Szerkesztés");
        btnDeleteDocument = new Button("Törlés");

        btnNewDocument.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnEditDocument.addStyleName(ValoTheme.BUTTON_PRIMARY);
        btnDeleteDocument.addStyleName(ValoTheme.BUTTON_DANGER);

        btnEditDocument.setEnabled(false);
        btnDeleteDocument.setEnabled(false);

        container.addComponent(btnNewDocument);
        container.addComponent(btnEditDocument);
        container.addComponent(btnDeleteDocument);

        container.setComponentAlignment(btnNewDocument, Alignment.MIDDLE_LEFT);
        container.setComponentAlignment(btnEditDocument, Alignment.MIDDLE_LEFT);
        container.setComponentAlignment(btnDeleteDocument, Alignment.MIDDLE_LEFT);
        return container;
    }

    private Grid<DocumentDTO> buildGrid() {
        Grid<DocumentDTO> grid = new Grid<>();
        grid.setSizeFull();
        grid.addColumn(DocumentDTO::getRegnumber).setCaption("Iktatószám").setSortable(false).setResizable(false);
        grid.addColumn(DocumentDTO::getFilename).setCaption("Fájl neve").setSortable(false).setResizable(false);
        grid.addColumn(DocumentDTO::getDescription).setCaption("Leírás").setSortable(false).setResizable(false);
        grid.addColumn(DocumentDTO::getContentSize).setCaption("Méret").setSortable(false).setResizable(false);
        grid.addColumn(DocumentDTO::getCreateUser).setCaption("Feltöltő").setSortable(false).setResizable(false);
        grid.addColumn(DocumentDTO::getCreateDate).setCaption("Feltöltve").setSortable(false).setResizable(false);
        grid.addColumn(DocumentDTO::getModifyUser).setCaption("Módosító").setSortable(false).setResizable(false);
        grid.addColumn(DocumentDTO::getModifyDate).setCaption("Módosítva").setSortable(false).setResizable(false);
        return grid;
    }

    private VerticalLayout createContent(Component... component) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setSpacing(true);
        layout.addComponents(component);

        return layout;
    }

    @Override
    public Grid<DocumentDTO> getDocumentGrid() {
        return documentGrid;
    }

    @Override
    public VerticalLayout getRoot() {
        return root;
    }

    @Override
    public Button getBtnDeleteDocument() {
        return btnDeleteDocument;
    }

    @Override
    public Button getBtnEditDocument() {
        return btnEditDocument;
    }

    @Override
    public Button getBtnNewDocument() {
        return btnNewDocument;
    }

    @Override
    public Button getBtnSearch() {
        return btnSearch;
    }

    @Override
    public TextField getSerachField() {
        return serachField;
    }

}
