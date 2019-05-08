package hu.due.document.management.ui.main.view;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

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
        grid.addColumn(DocumentDTO::getRegnumber).setCaption("Iktatószám").setSortable(false).setMinimumWidth(150.0d);
        grid.addColumn(DocumentDTO::getFilename).setCaption("Fájl neve").setSortable(false).setMinimumWidth(250.0d).setMaximumWidth(250.0d);
        grid.addColumn(this::getContentSizeAsString).setCaption("Méret").setSortable(false).setMinimumWidth(250.0d);
        grid.addColumn(this::getCreateUserName).setCaption("Feltöltő").setSortable(false).setMinimumWidth(150.0d);
        grid.addColumn(this::getCreateDateAsString).setCaption("Feltöltve").setSortable(false).setMinimumWidth(150.0d);
        grid.addColumn(this::getModifyUserName).setCaption("Módosító").setSortable(false).setMinimumWidth(150.0d);
        grid.addColumn(this::getModifyDateAsString).setCaption("Módosítva").setSortable(false).setMinimumWidth(150.0d);
        return grid;
    }

    private String getModifyUserName(DocumentDTO document) {
        return document.getModifyUser() != null ? document.getModifyUser().getFullname() : "";
    }

    private String getCreateUserName(DocumentDTO document) {
        return document.getCreateUser().getFullname();
    }

    private String getCreateDateAsString(DocumentDTO document) {
        return new SimpleDateFormat("yyyy.MM.dd.").format(document.getCreateDate());
    }

    private String getModifyDateAsString(DocumentDTO document) {
        return document.getModifyDate() != null ? new SimpleDateFormat("yyyy.MM.dd.").format(document.getModifyDate()) : "";
    }

    private String getContentSizeAsString(DocumentDTO document) {
        return formatFileSize(document.getContentSize());
    }

    public static String formatFileSize(long size) {
        String hrSize = null;

        double b = size;
        double k = size / 1024.0;
        double m = ((size / 1024.0) / 1024.0);
        double g = (((size / 1024.0) / 1024.0) / 1024.0);
        double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");

        if (t > 1) {
            hrSize = dec.format(t).concat(" TB");
        } else if (g > 1) {
            hrSize = dec.format(g).concat(" GB");
        } else if (m > 1) {
            hrSize = dec.format(m).concat(" MB");
        } else if (k > 1) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }

        return hrSize;
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
