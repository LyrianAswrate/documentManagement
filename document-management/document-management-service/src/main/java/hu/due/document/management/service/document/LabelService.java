package hu.due.document.management.service.document;

import java.util.List;

import hu.due.document.management.dto.LabelDTO;

public interface LabelService {

    public List<LabelDTO> getAllLabel();

    public void save(LabelDTO label);

    public void deleteById(Long id);

}
