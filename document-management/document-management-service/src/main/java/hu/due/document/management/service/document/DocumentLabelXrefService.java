package hu.due.document.management.service.document;

import java.util.List;

import hu.due.document.management.dto.LabelDTO;

public interface DocumentLabelXrefService {

    public List<LabelDTO> getLabelsByDocumentId(Long documentId);

}
