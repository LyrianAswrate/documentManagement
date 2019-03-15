package hu.due.document.management.service.document;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.due.document.management.dto.DocumentLabelDTO;
import hu.due.document.management.service.entity.DocumentLabel;
import hu.due.document.management.service.repository.DocumentLabelRepository;
import hu.due.document.management.service.repository.DocumentRepository;

@Transactional(readOnly = true)
@Service
public class DocumentLabelServiceImpl implements DocumentLabelService {

	@Autowired
	private DocumentLabelRepository repository;

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<DocumentLabelDTO> getAllDocumentLabelByDocumentId(Long documentId) {
		List<DocumentLabelDTO> documentLabels = new ArrayList<>();
		repository.findAllByDocument(documentRepository.getOne(documentId)).stream()
				.forEach(entity -> documentLabels.add(modelMapper.map(entity, DocumentLabelDTO.class)));
		return documentLabels;
	}

	@Override
	public List<DocumentLabelDTO> getAllDocumentLabel() {
		List<DocumentLabelDTO> documentLabels = new ArrayList<>();
		repository.findAll().stream()
				.forEach(entity -> documentLabels.add(modelMapper.map(entity, DocumentLabelDTO.class)));
		return documentLabels;
	}

	@Transactional
	@Override
	public void save(DocumentLabelDTO documentLabel) {
		DocumentLabel entity = new DocumentLabel();

		entity.setLabel(documentLabel.getLabel());
		entity.setDocument(documentRepository.findById(documentLabel.getDocument().getId()).orElse(null));

		repository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteByDocumentId(Long documentId) {
		repository.deleteAllByDocument(documentRepository.getOne(documentId));
	}

}
