package hu.due.document.management.service.document;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.due.document.management.dto.DocumentDTO;
import hu.due.document.management.service.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentRepository repository;

	@Autowired
	private DocumentLabelService documentLabelService;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<DocumentDTO> getAllDocument() {
		List<DocumentDTO> documents = new ArrayList<>();
		repository.findAll().stream().forEach(entity -> {
			DocumentDTO dto = modelMapper.map(entity, DocumentDTO.class);
			dto.setLabels(documentLabelService.getAllDocumentLabelByDocumentId(entity.getId()));
			documents.add(dto);
		});
		return documents;
	}

	@Override
	public DocumentDTO getDocumentById(Long documentId) {
		DocumentDTO document = modelMapper.map(repository.findById(documentId).orElse(null), DocumentDTO.class);
		document.setLabels(documentLabelService.getAllDocumentLabelByDocumentId(documentId));
		return document;
	}

	@Override
	public void deleteDocumentById(Long documentId) {
		repository.deleteById(documentId);
	}

	@Override
	public void save(DocumentDTO document) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DocumentDTO> search() {
		// TODO Auto-generated method stub
		return null;
	}

}
