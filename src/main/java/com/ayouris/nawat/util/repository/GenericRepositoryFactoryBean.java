/**
 * 
 */
package com.ayouris.nawat.util.repository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.ayouris.nawat.model.base.BaseEntity;
import com.ayouris.nawat.repository.generic.GenericRepositoryImpl;

@NoRepositoryBean
public class GenericRepositoryFactoryBean<R extends JpaRepository<T, I>, T extends BaseEntity, I extends Serializable> extends
		JpaRepositoryFactoryBean<R, T, I> {

	static Logger logger = LoggerFactory.getLogger(GenericRepositoryFactoryBean.class);

	@SuppressWarnings("rawtypes")
	@Override
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new GenericRepositoryFactory(entityManager);
	}

	protected static class GenericRepositoryFactory<T extends BaseEntity, I extends Serializable> extends JpaRepositoryFactory {

		private final EntityManager entityManager;

		public GenericRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected Object getTargetRepository(RepositoryMetadata metadata) {
			return new GenericRepositoryImpl<T, I>((Class<T>) metadata.getDomainType(), entityManager);
		}

		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			// The RepositoryMetadata can be safely ignored, it is used by the JpaRepositoryFactory
			// to check for QueryDslJpaRepository's which is out of scope.
			return GenericRepositoryImpl.class;
		}
	}
}