package hu.due.document.management.service.document;

import org.springframework.data.domain.Pageable;

import hu.due.document.management.dto.CustomePageDTO;
import hu.due.document.management.dto.DocumentDTO;

public interface DocumentService {

    public CustomePageDTO<DocumentDTO> getAllDocument(Pageable pageable);

    public CustomePageDTO<DocumentDTO> getAllByUserId(Long userId, Pageable pageable);

    public DocumentDTO getDocumentById(Long documentId);

    public void deleteDocumentById(Long documentId);

    public void save(DocumentDTO document);

    public CustomePageDTO<DocumentDTO> search(String filter, Pageable pageable);

    public CustomePageDTO<DocumentDTO> search(String filter, Long userId, Pageable pageable);

}
