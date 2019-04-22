package hu.due.document.management.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.due.document.management.service.entity.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
    //
}
