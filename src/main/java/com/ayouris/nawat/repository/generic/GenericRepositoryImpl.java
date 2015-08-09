/**
 * 
 */
package com.ayouris.nawat.repository.generic;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ayouris.nawat.model.base.BaseEcole;
import com.ayouris.nawat.model.base.BaseEntity;
import com.ayouris.nawat.model.entity.Ecole;
import com.ayouris.nawat.model.entity.UserNawat;
import com.ayouris.nawat.util.repository.DefaultPersistenceProvider;

@NoRepositoryBean
public class GenericRepositoryImpl<T extends BaseEntity, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements
		GenericRepository<T, ID>, Serializable {

	private static final long serialVersionUID = 2192554972658072711L;
	static Logger logger = Logger.getLogger(GenericRepositoryImpl.class);
	private static String FIRST_COMPTEUR_ID = "000000001";

	final JpaEntityInformation<T, ?> entityInformation;
	final EntityManager entityManager;
	final DefaultPersistenceProvider provider;

	Ecole currentEcole;
	UserNawat currentUser;

	private Class<?> springDataRepositoryInterface;

	public Class<?> getSpringDataRepositoryInterface() {
		return springDataRepositoryInterface;
	}

	public void setSpringDataRepositoryInterface(Class<?> springDataRepositoryInterface) {
		this.springDataRepositoryInterface = springDataRepositoryInterface;
	}

	/**
	 * Creates a new {@link SimpleJpaRepository} to manage objects of the given {@link JpaEntityInformation}.
	 * 
	 * @param entityInformation
	 * @param entityManager
	 */
	public GenericRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager,
			Class<?> springDataRepositoryInterface) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.entityManager = entityManager;
		this.provider = DefaultPersistenceProvider.fromEntityManager(entityManager);
		this.springDataRepositoryInterface = springDataRepositoryInterface;
	}

	/**
	 * Creates a new {@link SimpleJpaRepository} to manage objects of the given domain type.
	 * 
	 * @param domainClass
	 * @param em
	 */
	public GenericRepositoryImpl(Class<T> domainClass, EntityManager em) {
		this(JpaEntityInformationSupport.getMetadata(domainClass, em), em, null);
	}

	private void setDefaultFilter() {
		getdataSession();
		Session session = entityManager.unwrap(Session.class);
		Filter filter = session.enableFilter("ecole_filter");
		filter.setParameter("idEcole", currentEcole.getId());
		filter.validate();
	}

	@Override
	public void deleteAll() {
		setDefaultFilter();
		super.deleteAll();
	}

	@Override
	public void deleteAllInBatch() {
		setDefaultFilter();
		super.deleteAllInBatch();
	}

	@Override
	public List<T> findAll() {
		setDefaultFilter();
		return super.findAll();
	}

	@Override
	public List<T> findAll(Iterable<ID> ids) {
		return super.findAll(ids);
	}

	@Override
	public List<T> findAll(Sort sort) {
		setDefaultFilter();
		return super.findAll(sort);
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		setDefaultFilter();
		return super.findAll(pageable);
	}

	@Override
	public T findOne(Specification<T> spec) {
		setDefaultFilter();
		return super.findOne(spec);
	}
	
	@Override
	public List<T> findAll(Specification<T> spec) {
		setDefaultFilter();
		return super.findAll(spec);
	}

	@Override
	public Page<T> findAll(Specification<T> spec, Pageable pageable) {
		setDefaultFilter();
		return super.findAll(spec, pageable);
	}

	@Override
	public List<T> findAll(Specification<T> spec, Sort sort) {
		setDefaultFilter();
		return super.findAll(spec, sort);
	}

	@Override
	public long count() {
		setDefaultFilter();
		return super.count();
	}

	@Override
	public long count(Specification<T> spec) {
		setDefaultFilter();
		return super.count(spec);
	}

	@Override
	@Transactional
	public <S extends T> S save(S entity) {

		if (entityInformation.isNew(entity)) {
			prepareForSave(entity);

			entityManager.persist(entity);
			return entity;
		} else {
			return entityManager.merge(entity);
		}
	}

	private void prepareForSave(BaseEntity baseEntity) {
		baseEntity.setId(generateId(baseEntity));

		getdataSession();

		if (baseEntity instanceof BaseEcole) {
			BaseEcole baseEcole = (BaseEcole) baseEntity;
			baseEcole.setEcoleId(currentEcole.getId());
			baseEcole.setUserId(currentUser.getId());
		}
	}

	private String generateId(BaseEntity baseEntity) {
		StringBuffer sb = new StringBuffer();

		getdataSession();

		sb.append(" select max(id) from " + baseEntity.getClass().getName());
		sb.append(" where id like :boId");

		Query query = entityManager.createQuery(sb.toString());
		query.setParameter("boId", currentEcole.getId().concat("%"));

		String maxId = (String) query.getSingleResult();
		if (maxId != null) {
			int compteur = (Integer.parseInt(maxId.substring(6, 13)) + 1);
			return currentEcole.getId() + StringUtils.leftPad("" + compteur, 9, '0');

		} else {
			return currentEcole.getId() + FIRST_COMPTEUR_ID;
		}
	}

	private void getdataSession() {
		if (currentEcole == null || currentUser == null) {
			WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();

			this.currentEcole = (Ecole) context.getBean("currentEcole");
			this.currentUser  = (UserNawat) context.getBean("currentUser");
		}
	}

}
