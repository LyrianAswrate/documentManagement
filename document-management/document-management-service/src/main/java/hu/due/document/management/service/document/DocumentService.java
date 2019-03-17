package hu.due.document.management.service.document;

import java.util.List;

import hu.due.document.management.dto.DocumentDTO;

public interface DocumentService {

	public List<DocumentDTO> getAllDocument();

	public DocumentDTO getDocumentById(Long documentId);

	public void deleteDocumentById(Long documentId);

	public void save(DocumentDTO document);

	public List<DocumentDTO> search();

}
