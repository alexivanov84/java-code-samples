package org.nomoretea.mediaService.persistence.dao;

import org.nomoretea.mediaService.persistence.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IPostDao extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
	//
}
