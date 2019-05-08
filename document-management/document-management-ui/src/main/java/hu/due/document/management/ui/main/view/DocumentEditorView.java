package hu.due.document.management.ui.main.view;

import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;

import hu.due.document.management.ui.component.BaseView;

public interface DocumentEditorView extends BaseView {

    public Upload getUpload();

    public TextField getTfFileName();

    public TextField getTfRegnumber();

    public TextArea getTaDescription();

    public TextField getTfCreateUser();

    public DateField getDfCreateDate();

    public TextField getTfModifyUser();

    public DateField getDfModifyDate();

    public Button getBtnSave();

    public Button getBtnCancel();

    public HorizontalLayout getUploadComponent();

}
