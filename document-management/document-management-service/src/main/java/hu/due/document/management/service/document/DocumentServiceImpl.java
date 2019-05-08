package hu.due.document.management.service.document;

import java.util.Date;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.due.document.management.dto.CustomePageDTO;
import hu.due.document.management.dto.DocumentDTO;
import hu.due.document.management.service.entity.Document;
import hu.due.document.management.service.repository.DocumentRepository;
import hu.due.document.management.service.repository.UserRepository;
import hu.due.document.management.service.security.ApplicationUserDetails;

@Service
@Transactional(readOnly = true)
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository repository;

    @Autowired
    private UserRepository userRepository;

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

    @Transactional
    @Override
    public void deleteDocumentById(Long documentId) {
        repository.deleteByDocumentId(documentId);
    }

    @Transactional
    @Override
    public void save(DocumentDTO document) {
        Long callerUserId = ((ApplicationUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser()
                .getId();
        Document entity = null;
        if (document.getId() == null) {
            entity = new Document();
            entity.setRegnumber(document.getRegnumber());
            entity.setDescription(document.getDescription());
            entity.setContent(document.getFileContent());
            entity.setContentSize(document.getContentSize());
            entity.setCreateUser(userRepository.getOne(callerUserId));
            entity.setCreateDate(new Date());
            entity.setFilename(document.getFilename());
            repository.save(entity);
        } else {
            entity = repository.getOne(document.getId());
            entity.setRegnumber(document.getRegnumber());
            entity.setDescription(document.getDescription());
            entity.setModifyUser(userRepository.getOne(callerUserId));
            entity.setModifyDate(new Date());
        }
    }

    @Override
    public CustomePageDTO<DocumentDTO> search(String filter, Pageable pageable) {
        Page<Document> page = null;
        if ((filter != null) && !filter.trim().isEmpty()) {
            page = repository.findAllByFilterWithFetch(filter, pageable);
        } else {
            page = repository.findAllWithFetch(pageable);
        }
        CustomePageDTO<DocumentDTO> customePage = new CustomePageDTO<>();
        customePage.setContent(page.getContent().stream().map(map -> modelMapper.map(map, DocumentDTO.class)).collect(Collectors.toList()));
        customePage.setTotalElements(page.getTotalElements());
        return customePage;
    }

    @Override
    public CustomePageDTO<DocumentDTO> search(String filter, Long userId, Pageable pageable) {
        Page<Document> page = null;
        if ((filter != null) && !filter.trim().isEmpty()) {
            page = repository.findAllByCreateUserAndFilterWithFetch(filter, userId, pageable);
        } else {
            page = repository.findAllByCreateUserWithFetch(userId, pageable);
        }
        CustomePageDTO<DocumentDTO> customePage = new CustomePageDTO<>();
        customePage.setContent(page.getContent().stream().map(map -> modelMapper.map(map, DocumentDTO.class)).collect(Collectors.toList()));
        customePage.setTotalElements(page.getTotalElements());
        return customePage;
    }

    @Override
    public byte[] getDocumentContent(Long documentId) {
        return repository.getOne(documentId).getContent();
    }

}
