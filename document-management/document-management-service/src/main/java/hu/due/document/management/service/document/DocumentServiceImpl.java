package hu.due.document.management.service.document;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hu.due.document.management.dto.CustomePageDTO;
import hu.due.document.management.dto.DocumentDTO;
import hu.due.document.management.service.entity.Document;
import hu.due.document.management.service.repository.DocumentRepository;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository repository;

    @Autowired
    private DocumentLabelXrefService documentLabelXrefService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomePageDTO<DocumentDTO> getAllDocument(Pageable pageable) {
        Page<Document> page = repository.findAllWithFetch(pageable);
        CustomePageDTO<DocumentDTO> customePage = new CustomePageDTO<>();
        customePage.setContent(page.getContent().stream().map(map -> modelMapper.map(map, DocumentDTO.class)).collect(Collectors.toList()));
        customePage.setTotalElements(page.getTotalElements());
        return customePage;
    }

    @Override
    public CustomePageDTO<DocumentDTO> getAllByUserId(Long userId, Pageable pageable) {
        Page<Document> page = repository.findAllByCreateUserWithFetch(userId, pageable);
        CustomePageDTO<DocumentDTO> customePage = new CustomePageDTO<>();
        customePage.setContent(page.getContent().stream().map(map -> modelMapper.map(map, DocumentDTO.class)).collect(Collectors.toList()));
        customePage.setTotalElements(page.getTotalElements());
        return customePage;
    }

    @Override
    public DocumentDTO getDocumentById(Long documentId) {
        DocumentDTO document = modelMapper.map(repository.findByIdWithFetch(documentId), DocumentDTO.class);
        document.setLabels(documentLabelXrefService.getLabelsByDocumentId(documentId));
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
    public CustomePageDTO<DocumentDTO> search(String filter, Pageable pageable) {
        Page<Document> page = repository.findAllByFilterWithFetch(filter, pageable);
        CustomePageDTO<DocumentDTO> customePage = new CustomePageDTO<>();
        customePage.setContent(page.getContent().stream().map(map -> modelMapper.map(map, DocumentDTO.class)).collect(Collectors.toList()));
        customePage.setTotalElements(page.getTotalElements());
        return customePage;
    }

    @Override
    public CustomePageDTO<DocumentDTO> search(String filter, Long userId, Pageable pageable) {
        Page<Document> page = repository.findAllByCreateUserAndFilterWithFetch(filter, userId, pageable);
        CustomePageDTO<DocumentDTO> customePage = new CustomePageDTO<>();
        customePage.setContent(page.getContent().stream().map(map -> modelMapper.map(map, DocumentDTO.class)).collect(Collectors.toList()));
        customePage.setTotalElements(page.getTotalElements());
        return customePage;
    }

}
