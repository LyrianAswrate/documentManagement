package hu.due.document.management.service.document;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.due.document.management.dto.LabelDTO;
import hu.due.document.management.service.repository.DocumentLabelXrefRepository;

@Service
public class DocumentLabelXrefServiceImpl implements DocumentLabelXrefService {

    @Autowired
    private DocumentLabelXrefRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LabelDTO> getLabelsByDocumentId(Long documentId) {
        List<LabelDTO> labels = new ArrayList<>();
        repository.findAllByDocumentId(documentId).forEach(entity -> {
            labels.add(modelMapper.map(entity.getLabel(), LabelDTO.class));
        });
        return labels;
    }

}
