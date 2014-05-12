package org.nomoretea.mediaService.persistence.dao;

import org.nomoretea.mediaService.persistence.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ITagDao extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {
}
