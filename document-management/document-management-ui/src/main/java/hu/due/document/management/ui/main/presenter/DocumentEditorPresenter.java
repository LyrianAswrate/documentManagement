package hu.due.document.management.ui.main.presenter;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

import hu.due.document.management.dto.DocumentDTO;
import hu.due.document.management.service.document.DocumentService;
import hu.due.document.management.ui.main.MainUI;
import hu.due.document.management.ui.main.view.DocumentEditorView;
import hu.due.document.management.ui.main.view.DocumentEditorViewImpl;

@SpringView(name = DocumentEditorPresenter.NAME, ui = MainUI.class)
public class DocumentEditorPresenter implements View, Receiver, SucceededListener, FailedListener {
    private static final long serialVersionUID = 1L;

    public static final String NAME = "documentEditor";

    private DocumentEditorView view;

    @Autowired
    private DocumentService documentService;

    private DocumentDTO document;

    private ByteArrayOutputStream uploadedFileBaos;

    @Override
    public void enter(ViewChangeEvent event) {
        uploadedFileBaos = null;
        Object userId = UI.getCurrent().getSession().getAttribute("documentId");
        if (userId instanceof Long) {
            document = documentService.getDocumentById((Long) userId);
            UI.getCurrent().getSession().setAttribute("documentId", null);
        } else {
            document = new DocumentDTO();
        }
        loadDocumentData();
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    @PostConstruct
    public void postConstruct() {
        view = new DocumentEditorViewImpl(this);
        view.buildUI();

        view.getUpload().addSucceededListener(this);
        view.getUpload().addFailedListener(this);
        view.getUpload().setPrimaryStyleName("upload");
        view.getUpload().setButtonCaption("Talózás...");

        view.getBtnSave().addClickListener(event -> onSaveClicked());
        view.getBtnCancel().addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(DocumentListPresenter.NAME));
    }

    private void onSaveClicked() {
        boolean valid = true;
        StringBuilder sb = new StringBuilder();

        if (StringUtils.trimToNull(view.getTfRegnumber().getValue()) != null) {
            document.setRegnumber(view.getTfRegnumber().getValue());
        } else {
            valid = false;
            sb.append("Az iktatószám nem lehet üres! \n");
        }

        if ((document.getId() == null) && (uploadedFileBaos == null)) {
            valid = false;
            sb.append("A dokumentum feltőltés során hiba történt próbálja meg újra! \n");
        }

        if ((document.getId() == null) && (uploadedFileBaos != null)) {
            byte[] content = uploadedFileBaos.toByteArray();
            if (content.length <= 0) {
                valid = false;
                sb.append("A dokumentum mérete 0! \n");
            } else {
                document.setFileContent(content);
                document.setContentSize((long) content.length);
            }
        }

        document.setDescription(view.getTaDescription().getValue());
        document.setFilename(view.getTfFileName().getValue());

        // TODO labels!

        if (valid) {
            documentService.save(document);
            UI.getCurrent().getNavigator().navigateTo(DocumentListPresenter.NAME);
            Notification.show("Sikeres mentés!", Type.HUMANIZED_MESSAGE);
        } else {
            Notification.show("Validációs hiba", sb.toString(), Type.ERROR_MESSAGE);
        }
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private void loadDocumentData() {
        view.getTfRegnumber().setReadOnly(false);

        view.getTfRegnumber().setValue("");
        view.getTaDescription().setValue("");
        view.getTfCreateUser().setValue("");
        view.getDfCreateDate().setValue(null);
        view.getTfModifyUser().setValue("");
        view.getDfModifyDate().setValue(null);

        view.getUploadComponent().setVisible(true);
        view.getTfCreateUser().setVisible(false);
        view.getDfCreateDate().setVisible(false);
        view.getTfModifyUser().setVisible(false);
        view.getDfModifyDate().setVisible(false);

        if (document.getId() != null) {
            view.getUploadComponent().setVisible(false);
            view.getTfCreateUser().setVisible(true);
            view.getDfCreateDate().setVisible(true);
            view.getTfModifyUser().setVisible(true);
            view.getDfModifyDate().setVisible(true);

            view.getTfRegnumber().setValue(document.getRegnumber());
            view.getTaDescription().setValue(document.getDescription());
            view.getTfCreateUser().setValue(document.getCreateUser().getFullname());
            view.getDfCreateDate().setValue(convertToLocalDateViaInstant(document.getCreateDate()));
            view.getTfModifyUser().setValue(document.getModifyUser().getFullname());
            view.getDfModifyDate().setValue(convertToLocalDateViaInstant(document.getModifyDate()));

            view.getTfRegnumber().setReadOnly(true);
            view.getTfCreateUser().setReadOnly(true);
            view.getDfCreateDate().setReadOnly(true);
            view.getTfModifyUser().setReadOnly(true);
            view.getDfModifyDate().setReadOnly(true);

        }
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        uploadedFileBaos = new ByteArrayOutputStream();
        view.getTfFileName().setValue(filename);
        return uploadedFileBaos;
    }

    @Override
    public void uploadFailed(FailedEvent event) {
        Notification.show("Sikertelen mentés", "A dokumentum feltöltése során hiba keletkezett!", Notification.Type.ERROR_MESSAGE);
    }

    @Override
    public void uploadSucceeded(SucceededEvent event) {
        Notification.show("Sikeres fájl kiválasztás", Notification.Type.HUMANIZED_MESSAGE);
    }

}
