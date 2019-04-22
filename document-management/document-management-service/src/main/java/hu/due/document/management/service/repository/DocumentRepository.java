package hu.due.document.management.service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hu.due.document.management.service.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("SELECT d FROM Document d LEFT JOIN FETCH d.createUser cu LEFT JOIN FETCH d.modifyUser mu WHERE d.id = ?1")
    public Document findByIdWithFetch(Long documentId);

    @Query(value = "SELECT d FROM Document d LEFT JOIN FETCH d.createUser cu LEFT JOIN FETCH d.modifyUser mu WHERE cu.id = ?1", //
            countQuery = "SELECT count(d) FROM Document d WHERE d.createUser.id = ?1")
    public Page<Document> findAllByCreateUserWithFetch(Long userId, Pageable pageable);

    @Query(value = "SELECT d FROM Document d LEFT JOIN FETCH d.createUser cu LEFT JOIN FETCH d.modifyUser mu", //
            countQuery = "SELECT count(d) FROM Document d")
    public Page<Document> findAllWithFetch(Pageable pageable);

    @Query(value = "SELECT d FROM Document d LEFT JOIN FETCH d.createUser cu LEFT JOIN FETCH d.modifyUser mu WHERE d.regnumber like %:filter% OR d.filename like %:filter% OR d.description like %:filter% OR "
            + "d.documentLabelXrefs IN ((SELECT dlx FROM DocumentLabelXref dlx WHERE dlx.document.id = d.id AND dlx.label.label like %:filter%))", //
            countQuery = "SELECT d FROM Document d WHERE d.regnumber like %:filter% OR d.filename like %:filter% OR d.description like %:filter% OR "
                    + "d.documentLabelXrefs IN ((SELECT dlx FROM DocumentLabelXref dlx WHERE dlx.document.id = d.id AND dlx.label.label like %:filter%))")
    public Page<Document> findAllByFilterWithFetch(@Param("filter") String filter, Pageable pageable);

    @Query(value = "SELECT d FROM Document d LEFT JOIN FETCH d.createUser cu LEFT JOIN FETCH d.modifyUser mu WHERE cu.id = :userId AND (d.regnumber like %:filter% OR d.filename like %:filter% OR d.description like %:filter% OR "
            + "d.documentLabelXrefs IN ((SELECT dlx FROM DocumentLabelXref dlx WHERE dlx.document.id = d.id AND dlx.label.label like %:filter%)))", //
            countQuery = "SELECT d FROM Document d WHERE d.createUser.id = :userId AND (d.regnumber like %:filter% OR d.filename like %:filter% OR d.description like %:filter% OR "
                    + "d.documentLabelXrefs IN ((SELECT dlx FROM DocumentLabelXref dlx WHERE dlx.document.id = d.id AND dlx.label.label like %:filter%)))")
    public Page<Document> findAllByCreateUserAndFilterWithFetch(@Param("filter") String filter, @Param("userId") Long userId,
            Pageable pageable);

}
