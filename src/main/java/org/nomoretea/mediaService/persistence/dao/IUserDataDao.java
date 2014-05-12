package org.nomoretea.mediaService.persistence.dao;

import org.nomoretea.mediaService.persistence.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserDataDao extends JpaRepository<UserData, Long>, JpaSpecificationExecutor<UserData> {
	//
}
