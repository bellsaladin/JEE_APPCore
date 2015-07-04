package com.ayouris.nawat.service.parametrage;

import com.ayouris.nawat.model.entity.Cycle;
import com.ayouris.nawat.service.generic.GenericService;

public interface CycleService extends GenericService<Cycle, String> {

	void testTransaction();
}
