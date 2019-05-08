package hu.due.document.management.ui.component;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.ChangeEvent;
import com.vaadin.ui.Upload.ChangeListener;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.themes.ValoTheme;

@Deprecated
public class UploadComponent extends HorizontalLayout
        implements Receiver, ChangeListener, SucceededListener, ClickListener, FailedListener {
    private static final long serialVersionUID = 1L;

    private TextField fileNameTextField;
    private Upload upload;
    private Button uploadButton;

    private ByteArrayOutputStream uploadedFileBaos;
    private final String[] mimeTypes;
    private final String caption;

    private final List<SucceededListener> succeededListeners = new ArrayList<>();

    public UploadComponent(String caption, String... mimeTypes) {
        this.mimeTypes = mimeTypes;
        this.caption = caption;
        init();
    }

    public void addSucceededListener(SucceededListener listener) {
        succeededListeners.add(listener);
        upload.addSucceededListener(listener);
    }

    @Override
    public void buttonClick(ClickEvent event) {
        upload.submitUpload();
        uploadButton.setEnabled(false);
    }

    public void clear() {
        setTextFieldValue("");
        uploadButton.setEnabled(false);
    }

    @Override
    public void filenameChanged(ChangeEvent event) {
        setTextFieldValue(event.getFilename());
        uploadButton.setEnabled(true);
    }

    private void init() {
        fileNameTextField = new TextField();

        fileNameTextField.setReadOnly(true);
        fileNameTextField.setWidth("300px");
        fileNameTextField.setHeight("30px");

        uploadButton = new Button("Feltőtlés", this);
        uploadButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        uploadButton.addStyleName(ValoTheme.BUTTON_SMALL);
        uploadButton.setEnabled(false);

        upload = new Upload(null, this);
        upload.addChangeListener(this);
        upload.addSucceededListener(this);
        upload.addFailedListener(this);
        upload.setPrimaryStyleName("upload");
        upload.setButtonCaption(null);

        if (!succeededListeners.isEmpty()) {
            succeededListeners.stream().forEach(l -> upload.addSucceededListener(l));
        }

        addComponents(fileNameTextField, upload, uploadButton);
        setCaption(caption);
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        uploadedFileBaos = new ByteArrayOutputStream();

        String contentType = mimeType;
        boolean allowed = false;
        for (String allowedContentType : mimeTypes) {
            if (contentType.equalsIgnoreCase(allowedContentType)) {
                allowed = true;
                break;
            }
        }
        if (!allowed) {
            Notification.show("Nem megfelelő kiterjesztés", "A feltöltendő fájl kiterjesztése nem megfelelő!", Type.ERROR_MESSAGE);
            uploadedFileBaos = null;
            upload.interruptUpload();
            return null;
        }

        return uploadedFileBaos;
    }

    private void reLoadComponent() {
        removeAllComponents();
        init();
        markAsDirty();
    }

    @Override
    public void setEnabled(boolean enabled) {
        upload.setEnabled(enabled);
    }

    private void setTextFieldValue(String newValue) {
        fileNameTextField.setReadOnly(false);
        fileNameTextField.setValue(newValue);
        fileNameTextField.setReadOnly(true);
    }

    @Override
    public void uploadFailed(FailedEvent event) {
        reLoadComponent();
    }

    private byte[] getUploadedContent() {
        return uploadedFileBaos.toByteArray();
    }

    @Override
    public void uploadSucceeded(SucceededEvent event) {
        Notification.show("Sikeres feltőltés", Notification.Type.HUMANIZED_MESSAGE);
        reLoadComponent();
    }

}
