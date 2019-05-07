package hu.due.document.management.ui.main.presenter;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.addon.pagination.Pagination;
import com.vaadin.addon.pagination.PaginationResource;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.UI;

import hu.due.document.management.dto.CustomePageDTO;
import hu.due.document.management.dto.DocumentDTO;
import hu.due.document.management.enums.AppRole;
import hu.due.document.management.service.document.DocumentService;
import hu.due.document.management.service.security.ApplicationUserDetails;
import hu.due.document.management.ui.main.MainUI;
import hu.due.document.management.ui.main.view.DocumentListView;
import hu.due.document.management.ui.main.view.DocumentListViewImpl;

@SpringView(name = DocumentListPresenter.NAME, ui = MainUI.class)
public class DocumentListPresenter implements View {

    private static final long serialVersionUID = 1L;

    public static final String NAME = "";

    private String filter;
    private DocumentListView view;

    @Autowired
    private DocumentService documentService;

    private Pagination pagination;

    @Override
    public void enter(ViewChangeEvent event) {
        filter = "";
        loadGridPage(0, 10);
    }

    @Override
    public Component getViewComponent() {
        return view;
    }

    @PostConstruct
    public void postConstruct() {
        view = new DocumentListViewImpl();
        view.buildUI();

        pagination = createPagination(findAll("", 0, 10).getTotalElements(), 1, 10);
        pagination.setItemsPerPageVisible(false);
        pagination.setItemsPerPageEnabled(false);

        pagination.addPageChangeListener(event -> loadGridPage(event.pageIndex(), event.limit()));

        view.getRoot().addComponent(pagination);
        view.getDocumentGrid().setSelectionMode(SelectionMode.SINGLE);
        view.getDocumentGrid().addSelectionListener(event -> {
            if (event.getFirstSelectedItem().isPresent()
                    && (((ApplicationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId()
                            .longValue() != event.getFirstSelectedItem().get().getId().longValue())) {
                view.getBtnEditDocument().setEnabled(true);
                view.getBtnDeleteDocument().setEnabled(true);
            } else {
                view.getBtnEditDocument().setEnabled(false);
                view.getBtnDeleteDocument().setEnabled(false);
            }
        });

        ShortcutListener listener = new ShortcutListener("", KeyCode.ENTER, null) {
            private static final long serialVersionUID = 1L;

            @Override
            public void handleAction(Object sender, Object target) {
                onSearchClicked();
            }
        };

        view.getBtnSearch().addShortcutListener(listener);
        view.getSerachField().addShortcutListener(listener);

        view.getBtnSearch().addClickListener(event -> onSearchClicked());
        view.getBtnNewDocument().addClickListener(event -> onNewDocumentClicked());
        view.getBtnEditDocument().addClickListener(event -> onEditDocumentClicked());
        view.getBtnDeleteDocument().addClickListener(event -> onDeleteDocumentClicked());
    }

    private void onSearchClicked() {
        filter = view.getSerachField().getValue();
        loadGridPage(0, 10);
    }

    private void onDeleteDocumentClicked() {
        ConfirmDialog.show(UI.getCurrent(), "Felhasználó törlése", "Biztos hogy szeretné töröli a felhasználót?", "Igen", "Mégsem",
                () -> onDeleteConfirmedClicked());
    }

    private void onDeleteConfirmedClicked() {
        documentService.deleteDocumentById(view.getDocumentGrid().getSelectionModel().getFirstSelectedItem().get().getId());
        loadGridPage(0, 10);
    }

    private void onEditDocumentClicked() {
        UI.getCurrent().getSession().setAttribute("documentId",

                view.getDocumentGrid().getSelectionModel().getFirstSelectedItem().get().getId());
        UI.getCurrent().getNavigator().navigateTo(DocumentEditorPresenter.NAME);
    }

    private void onNewDocumentClicked() {
        UI.getCurrent().getSession().setAttribute("documentId", null);
        UI.getCurrent().getNavigator().navigateTo(DocumentEditorPresenter.NAME);
    }

    private void loadGridPage(int page, int size) {
        CustomePageDTO<DocumentDTO> users = findAll(filter, page, size);
        pagination.setTotalCount(users.getTotalElements());
        view.getDocumentGrid().setItems(users.getContent());
        view.getDocumentGrid().scrollToStart();
    }

    private CustomePageDTO<DocumentDTO> findAll(String filter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        CustomePageDTO<DocumentDTO> documents = null;
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(pre -> pre.getAuthority().equals(AppRole.ADMIN.name()))) {
            documents = documentService.search(filter, pageable);
        } else {
            ApplicationUserDetails applicationUserDetails = (ApplicationUserDetails) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            documents = documentService.search(filter, applicationUserDetails.getUser().getId(), pageable);
        }
        return documents;
    }

    private Pagination createPagination(long total, int page, int limit) {
        PaginationResource paginationResource = PaginationResource.newBuilder().setTotal(total).setPage(page).setLimit(limit).build();
        Pagination pagination = new Pagination(paginationResource);
        return pagination;
    }
}
