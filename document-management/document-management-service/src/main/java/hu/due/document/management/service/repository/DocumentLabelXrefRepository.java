package hu.due.document.management.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hu.due.document.management.service.entity.DocumentLabelXref;

@Repository
public interface DocumentLabelXrefRepository extends JpaRepository<DocumentLabelXref, Long> {

    @Query("SELECT dlx FROM DocumentLabelXref dlx LEFT JOIN FETCH dlx.label WHERE dlx.document.id = ?1")
    public List<DocumentLabelXref> findAllByDocumentId(Long documentId);

}
