package hu.due.document.management.service.document;

import java.util.List;

import hu.due.document.management.dto.DocumentLabelDTO;

public interface DocumentLabelService {

	public List<DocumentLabelDTO> getAllDocumentLabelByDocumentId(Long documentId);

	public List<DocumentLabelDTO> getAllDocumentLabel();

	public void save(DocumentLabelDTO documentLabel);

	public void deleteById(Long id);

	public void deleteByDocumentId(Long documentId);

}
