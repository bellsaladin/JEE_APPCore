package com.ayouris.nawat.util.listener;

public class EntityListener {

	// @PrePersist
	// public void prePersist(BaseEntity superclass) throws Exception {
	// Ecole currentEcole = (Ecole) Component.getInstance("currentEcole");
	// if (ConstantesFramework.DEMO_VERSION || !currentEcole.isLicensed()) {
	// if (superclass instanceof Eleve || superclass instanceof Inscription || superclass instanceof Payement
	// || superclass instanceof Reglement) {
	//
	// int countLimit = ConstantesFramework.ELEVE_COUNT_LIMIT_DEMO;
	// if (!currentEcole.isLicensed()) {
	// countLimit = ConstantesFramework.ELEVE_COUNT_LIMIT_NOT_LICENSED;
	// }
	//
	// EntityManager entityManager = (EntityManager) Component.getInstance("entityManager");
	// Query query = entityManager.createQuery("select count(*) from Eleve");
	// long count = (Long) query.getSingleResult();
	//
	// if (superclass instanceof Eleve && count >= countLimit) {
	// throw new DemoException();
	// } else if ((superclass instanceof Inscription || superclass instanceof Payement || superclass instanceof Reglement)
	// && count > countLimit) {
	// throw new DemoException();
	// }
	// }
	// }
	// }
	//
	// @PostRemove
	// public void postRemove(BaseEntity superclass) throws Exception {
	//
	// EntityManager entityManager = (EntityManager) Component.getInstance("entityManager");
	//
	// RemoveJournal removeJournal = new RemoveJournal();
	// removeJournal.setTableName(superclass.getClass().getSimpleName());
	// removeJournal.setRowId(superclass.getId());
	// removeJournal.setEcole((Ecole) Component.getInstance("currentEcole"));
	// removeJournal.setRemoveDate(getCurrentDateTime());
	//
	// entityManager.persist(removeJournal);
	//
	// }
	//
	// public Date getCurrentDateTime() {
	// Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
	// return calendar.getTime();
	// }
}
