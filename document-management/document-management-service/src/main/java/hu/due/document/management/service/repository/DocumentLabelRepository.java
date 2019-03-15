package hu.due.document.management.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.due.document.management.service.entity.DocumentLabel;

@Repository
public interface DocumentLabelRepository extends JpaRepository<DocumentLabel, Long> {
	//
}
