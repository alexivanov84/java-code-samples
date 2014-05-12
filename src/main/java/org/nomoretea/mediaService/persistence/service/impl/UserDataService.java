package org.nomoretea.mediaService.persistence.service.impl;

import org.nomoretea.mediaService.persistence.dao.IUserDataDao;
import org.nomoretea.mediaService.persistence.model.UserData;
import org.nomoretea.mediaService.persistence.service.IUserDataService;
import org.nomoretea.mediaService.persistence.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDataService extends AbstractService<UserData> implements IUserDataService{

	@Autowired
	private IUserDataDao dao;

	@Override
	protected PagingAndSortingRepository<UserData, Long> getDao() {
		return dao;
	}
}