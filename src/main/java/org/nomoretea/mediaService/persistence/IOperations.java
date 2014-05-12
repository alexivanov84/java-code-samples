package org.nomoretea.mediaService.persistence;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public interface IOperations<T extends Serializable> {

	T findOne(final Long entityId);

	List<T> findAll();

	T create(final T entity);

	T update(final T entity);

	void delete(final T entity);

	void deleteById(final Long entityId);

}
