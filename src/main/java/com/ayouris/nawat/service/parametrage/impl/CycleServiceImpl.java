package com.ayouris.nawat.service.parametrage.impl;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ayouris.nawat.model.entity.Cycle;
import com.ayouris.nawat.repository.generic.GenericRepository;
import com.ayouris.nawat.repository.parametrage.CycleRepository;
import com.ayouris.nawat.service.generic.impl.GenericServiceImpl;
import com.ayouris.nawat.service.parametrage.CycleService;

@Service
@Transactional(readOnly = true)
public class CycleServiceImpl extends GenericServiceImpl<Cycle, String> implements CycleService {

	@Autowired
	CycleRepository cycleRepository;

	@Override
	public GenericRepository<Cycle, String> getRepository() {
		return cycleRepository;
	}

	@Override
	@Transactional
	public void testTransaction() {
		// List<Cycle> cycleList = new ArrayList<Cycle>();

		Cycle cycle = new Cycle();
		cycle.setLibelle("Ayouris-1");
		cycle.setNumeroAutorisation("NHTR12548");
		cycle.setActif(true);
		cycle.setOrdre(1);
		cycle.setDateAutorisation(new LocalDate());
		// cycleList.add(cycle);
		cycleRepository.saveAndFlush(cycle);

		Cycle cycle1 = new Cycle();
		cycle1.setLibelle("Ayouris-2");
		cycle1.setNumeroAutorisation("NHTR12548");
		cycle1.setActif(true);
		cycle1.setOrdre(1);
		cycle1.setDateAutorisation(new LocalDate());
		// cycleList.add(cycle1);
		cycleRepository.saveAndFlush(cycle1);

		Cycle cycle2 = new Cycle();
		cycle2.setLibelle("Ayouris-3");
		cycle2.setNumeroAutorisation("NHTR12548");
		cycle2.setActif(true);
		cycle2.setOrdre(1);
		cycle2.setDateAutorisation(new LocalDate());
		// cycleList.add(cycle2);
		cycleRepository.saveAndFlush(cycle2);

	}

}
