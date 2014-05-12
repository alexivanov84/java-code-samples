package org.nomoretea.mediaService.persistence.service.impl;

import org.nomoretea.mediaService.persistence.dao.IPostDao;
import org.nomoretea.mediaService.persistence.model.Post;
import org.nomoretea.mediaService.persistence.service.IPostService;
import org.nomoretea.mediaService.persistence.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PostService extends AbstractService<Post> implements IPostService {

	@Autowired
	private IPostDao dao;

	@Override
	protected PagingAndSortingRepository<Post, Long> getDao() {
		return dao;
	}
}
