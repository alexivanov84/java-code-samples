package org.nomoretea.mediaService.persistence.dao;

import org.nomoretea.mediaService.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	//
}