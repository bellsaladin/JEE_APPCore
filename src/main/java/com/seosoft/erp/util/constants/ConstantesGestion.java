package com.seosoft.erp.util.constants;

/**
 * Interface de déclaration des constantes gestion.
 */
public interface ConstantesGestion {

	/************************************************************
	 * Emplacement des fichiers de configurations *
	 ************************************************************/

	/** Nom du fichier de constantes metier */
	String CONFIG_FILE = "ConstantesGestion";

	/** id par defaut a aficher en action nouvelle */
	String AUTO_ID = "---Auto---";

	/** Code mois scolaire effectif */
	// String MOIS_SCOLAIRE_FICTIF_CODE = "00";

	/** Abreviation du locale Arabe */
	String ABREV_LOCALE_ARABIC = "ar";

	/** Clé de l'impression pdf dans la session */
	String PDF_IMPRESSION_SESSION_KEY = "pdfSessionKey";

	/************************************************************
	 * les constantes etat *
	 ************************************************************/

	String ETAT_FICHE_ELEVE = "fiche_eleve";
	String ETAT_LISTE_ELELVE_NIVEAU_CLASSE = "liste_eleves_niveau_classe";
	String ETAT_CERTIFICAT_SCOLAIRE = "certificat_scolaire";
	String ETAT_CERTIFICAT_SCOLAIRE_LNG2 = "certificat_scolaire_lng2";
	String ETAT_CERTIFICAT_SCOLAIRE_DELEGATION = "certificat_scolaire_delegation";
	String ETAT_CERTIFICAT_SCOLAIRE_DELEGATION_LNG2 = "certificat_scolaire_delegation_lng2";
	String ETAT_CERTIFICAT_SCOLAIRE_CESSATION = "certificat_cessation_delegation";
	String ETAT_CERTIFICAT_SCOLAIRE_CESSATION_LNG2 = "certificat_cessation_delegation_lng2";
	String ETAT_LISTE_IMPAYE = "liste_impayes";
	String ETAT_LISTE_PAIEMENT = "liste_paiements";
	String ETAT_LISTE_REGLEMENT = "liste_reglements";
	String ETAT_RECU_PAIEMENT = "recu_paiement";
	String ETAT_ACCUSE_INSCRIPTION = "accuse_inscription";
	String ETAT_FICHE_RENSEIGNEMENT_ELEVE = "fiche_renseignement";
	String ETAT_FICHE_NOTES_VIDE = "fiche_notes_vide";
	String ETAT_LISTE_BULLETINS = "bulletin";
	String ETAT_LISTE_BULLETINS_NORMALISE = "bulletin_normalise";
	String ETAT_LISTE_BULLETINS_FIN = "bulletin_fin_annee";
	String ETAT_LISTE_PERIODE_FERIE = "liste_periode_ferie";
	String ETAT_LISTE_MOYENNE_CLASSE = "liste_moyenne_classe";
	String ETAT_LISTE_NOTE_MATIERE_CLASSE = "liste_notes_matiere_classe";
	String ETAT_LISTE_ELEVE_CLASSE_SEXE = "liste_eleve_classe_sexe";
	String ETAT_LISTE_CHEQUES = "liste_cheques";
	String ETAT_LISTE_PERSONNELS = "liste_personnel_administration";
	String ETAT_LISTE_PERSONNELS_ENSEIGNANT = "liste_personnel_enseignant";
	String ETAT_LISTE_ENSEIGNANT_NIVEAU_CLASSE = "liste_enseignant_niveau_classe";
	String ETAT_LISTE_REDUCTION_NIVEAU_CLASSE = "liste_reduction_niveau_classe";
	String ETAT_LISTE_FAMILLE_ELEVE = "liste_famille_eleve";
	String ETAT_LISTE_ELEVE_CLASSE_SERVICE = "liste_eleves_service";
	String ETAT_LISTE_CONTACT_ELEVE = "liste_contacts_test";
	String ETAT_LISTE_INSCRIPTION_CLASSE = "liste_inscription_classe";
	String ETAT_LISTE_ELEVES_REGISTRE = "liste_eleves_registre";
	String ETAT_LISTE_ELELVE_NON_INSCRITS = "liste_eleves_non_inscrits";
	String LETTRE_RELANCE_IMAPYES = "relance_impaye";
	String ETAT_RECETTE_CAISSE = "recette_caisse";
	String ETAT_FICHE_ABSENCE_VIDE = "fiche_absence_vide";
	String ETAT_PIECES_INSCRIPTION_MANQUANT = "liste_pieces_inscription_manquant";
	String ETAT_FICHE_RENSEIGNEMENT_PERSONNEL = "fiche_renseignement_personnel";
	String ETAT_NOTE_CONTROLE_CONTINUE = "fiche_note_controle_continue";
	String ETAT_FICHE_RENSEIGNEMENT_ELEVE_VIDE = "fiche_eleve_vide";
	String ETAT_NOTE_CONTROLE_CONTINUE_PRIMAIRE = "fiche_note_controle_continue_primaire";

}