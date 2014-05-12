package org.nomoretea.mediaService.persistence.service.impl;

import org.nomoretea.mediaService.persistence.IOperations;
import org.nomoretea.mediaService.persistence.dao.ITagDao;
import org.nomoretea.mediaService.persistence.model.Tag;
import org.nomoretea.mediaService.persistence.service.common.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TagService extends AbstractService<Tag> implements IOperations<Tag>{

	@Autowired
	private ITagDao tagDao;

	@Override
	protected PagingAndSortingRepository<Tag, Long> getDao() {
		return tagDao;
	}
}
