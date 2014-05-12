package org.nomoretea.mediaService.persistence.service.common;


import com.google.common.collect.Lists;
import org.nomoretea.mediaService.persistence.IOperations;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

	@Override
	@Transactional(readOnly = true)
	public T findOne(final Long entityId) {
		return getDao().findOne(entityId);
	}

	@Override
	public List<T> findAll() {
		return Lists.newArrayList(getDao().findAll());
	}

	@Override
	public T create(T entity) {
		return getDao().save(entity);
	}

	@Override
	public T update(T entity) {
		return getDao().save(entity);
	}

	@Override
	public void delete(T entity) {
		getDao().delete(entity);
	}

	@Override
	public void deleteById(Long entityId) {
		getDao().delete(entityId);
	}

	protected abstract PagingAndSortingRepository<T, Long> getDao();

}
