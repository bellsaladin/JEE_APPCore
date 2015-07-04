package com.ayouris.nawat.service.business.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.nawat.model.entity.Eleve;
import com.ayouris.nawat.repository.business.EleveRepository;
import com.ayouris.nawat.repository.generic.GenericRepository;
import com.ayouris.nawat.service.business.EleveService;
import com.ayouris.nawat.service.generic.impl.GenericServiceImpl;

@Service
@Transactional(readOnly = true)
public class EleveServiceImpl extends GenericServiceImpl<Eleve, String> implements EleveService {

	@Autowired
	EleveRepository eleveRepository;

	@Override
	public GenericRepository<Eleve, String> getRepository() {
		return eleveRepository;
	}

	@Override
	public Page<Eleve> findByNomStartingWithAndPrenomStartingWith(String nom, String prenom, Pageable pageable) {
		return null;
		//return eleveRepository.findByNomStartingWithAndPrenomStartingWith(nom, prenom, pageable);
	}

	public static Specification<Eleve> statusSetEqual(final String param) {
		return new Specification<Eleve>() {
			@Override
			public Predicate toPredicate(Root<Eleve> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.not(root.get("status").in((Object) param));
			}
		};

	}

}
