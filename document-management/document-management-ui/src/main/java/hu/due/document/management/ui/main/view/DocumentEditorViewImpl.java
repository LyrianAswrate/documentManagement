package hu.due.document.management.ui.main.view;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class DocumentEditorViewImpl extends CustomComponent implements DocumentEditorView {

    private static final long serialVersionUID = 1L;

    private final Receiver uplodaReceiver;

    private VerticalLayout root;

    private TextField tfRegnumber;
    private TextArea taDescription;
    private HorizontalLayout uploadComponent;
    private TextField tfFileName;
    private Upload upload;
    private TextField tfContentSize;
    private TextField tfCreateUser;
    private DateField dfCreateDate;
    private TextField tfModifyUser;
    private DateField dfModifyDate;

    private Button btnSave;
    private Button btnCancel;

    public DocumentEditorViewImpl(Receiver uplodaReceiver) {
        this.uplodaReceiver = uplodaReceiver;
    }

    @Override
    public void buildUI() {
        Label headerLabel = new Label("<h2>Dokumentum szerkesztése</h2>", ContentMode.HTML);

        tfRegnumber = new TextField("Iktatószám");
        tfRegnumber.setWidth("300px");
        taDescription = new TextArea("Leírás");
        taDescription.setWidth("300px");

        tfContentSize = new TextField("Fájl méret");
        tfContentSize.setVisible(false);
        tfContentSize.setWidth("300px");

        tfCreateUser = new TextField("Feltöltő");
        tfCreateUser.setVisible(false);
        tfCreateUser.setWidth("300px");

        dfCreateDate = new DateField("Feltöltve");
        dfCreateDate.setVisible(false);

        tfModifyUser = new TextField("Módosító");
        tfModifyUser.setVisible(false);
        tfModifyUser.setWidth("300px");

        dfModifyDate = new DateField("Módosítva");
        dfModifyDate.setVisible(false);

        tfFileName = new TextField();
        tfFileName.setReadOnly(true);
        tfFileName.setWidth("300px");

        upload = new Upload(null, uplodaReceiver);

        uploadComponent = new HorizontalLayout();

        uploadComponent.addComponent(tfFileName);
        uploadComponent.addComponent(upload);

        uploadComponent.setComponentAlignment(tfFileName, Alignment.MIDDLE_LEFT);
        uploadComponent.setComponentAlignment(upload, Alignment.MIDDLE_LEFT);

        HorizontalLayout buttonComponent = new HorizontalLayout();
        buttonComponent.setMargin(true);

        btnSave = new Button("Mentés");
        btnSave.addStyleName(ValoTheme.BUTTON_PRIMARY);

        btnCancel = new Button("Mégsem");

        buttonComponent.addComponent(btnSave);
        buttonComponent.addComponent(btnCancel);

        buttonComponent.setComponentAlignment(btnSave, Alignment.MIDDLE_LEFT);
        buttonComponent.setComponentAlignment(btnCancel, Alignment.MIDDLE_LEFT);

        root = new VerticalLayout();
        root.setMargin(true);
        root.addComponent(headerLabel);
        root.addComponent(tfRegnumber);
        root.addComponent(taDescription);
        root.addComponent(uploadComponent);
        root.addComponent(tfContentSize);
        root.addComponent(tfCreateUser);
        root.addComponent(dfCreateDate);
        root.addComponent(tfModifyUser);
        root.addComponent(dfModifyDate);
        root.addComponent(buttonComponent);

        root.setComponentAlignment(headerLabel, Alignment.TOP_LEFT);
        root.setComponentAlignment(tfRegnumber, Alignment.TOP_LEFT);
        root.setComponentAlignment(taDescription, Alignment.TOP_LEFT);
        root.setComponentAlignment(uploadComponent, Alignment.TOP_LEFT);
        root.setComponentAlignment(tfContentSize, Alignment.TOP_LEFT);
        root.setComponentAlignment(tfCreateUser, Alignment.TOP_LEFT);
        root.setComponentAlignment(dfCreateDate, Alignment.TOP_LEFT);
        root.setComponentAlignment(tfModifyUser, Alignment.TOP_LEFT);
        root.setComponentAlignment(dfModifyDate, Alignment.TOP_LEFT);
        root.setComponentAlignment(buttonComponent, Alignment.TOP_LEFT);
        setCompositionRoot(root);
    }

    @Override
    public Upload getUpload() {
        return upload;
    }

    @Override
    public TextField getTfFileName() {
        return tfFileName;
    }

    @Override
    public HorizontalLayout getUploadComponent() {
        return uploadComponent;
    }

    @Override
    public TextField getTfRegnumber() {
        return tfRegnumber;
    }

    @Override
    public TextArea getTaDescription() {
        return taDescription;
    }

    @Override
    public TextField getTfCreateUser() {
        return tfCreateUser;
    }

    @Override
    public DateField getDfCreateDate() {
        return dfCreateDate;
    }

    @Override
    public TextField getTfModifyUser() {
        return tfModifyUser;
    }

    @Override
    public DateField getDfModifyDate() {
        return dfModifyDate;
    }

    @Override
    public Button getBtnSave() {
        return btnSave;
    }

    @Override
    public Button getBtnCancel() {
        return btnCancel;
    }

}
