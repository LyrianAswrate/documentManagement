package hu.due.document.management.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.due.document.management.dto.DocumentLabelDTO;
import hu.due.document.management.service.entity.Document;
import hu.due.document.management.service.entity.DocumentLabel;

@Repository
public interface DocumentLabelRepository extends JpaRepository<DocumentLabel, Long> {

	public void deleteAllByDocument(Document document);

	public List<DocumentLabelDTO> findAllByDocument(Document document);

}
