package hu.due.document.management.service.document;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.due.document.management.dto.LabelDTO;
import hu.due.document.management.service.entity.Label;
import hu.due.document.management.service.repository.LabelRepository;

@Transactional(readOnly = true)
@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<LabelDTO> getAllLabel() {
        List<LabelDTO> documentLabels = new ArrayList<>();
        repository.findAll().stream().forEach(entity -> documentLabels.add(modelMapper.map(entity, LabelDTO.class)));
        return documentLabels;
    }

    @Transactional
    @Override
    public void save(LabelDTO label) {
        Label entity = new Label();

        entity.setLabel(label.getLabel());

        repository.save(entity);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
