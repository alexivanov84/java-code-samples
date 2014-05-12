package org.nomoretea.mediaService.persistence.service.impl;

import org.nomoretea.mediaService.persistence.dao.IUserDao;
import org.nomoretea.mediaService.persistence.model.User;
import org.nomoretea.mediaService.persistence.service.IUserService;
import org.nomoretea.mediaService.persistence.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService extends AbstractService<User> implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	protected PagingAndSortingRepository<User, Long> getDao() {
		return userDao;
	}

}