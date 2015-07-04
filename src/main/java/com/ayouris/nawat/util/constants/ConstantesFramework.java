package com.ayouris.nawat.util.constants;

/**
 * Interface de d�claration des constantes du framework.
 * 
 * @author Khalid CHAMAKH (elBissat)
 * @version 1.0
 */
public interface ConstantesFramework {

	/************************************************************
	 * Activation de la base de DEMO *
	 ************************************************************/
	boolean DEMO_VERSION = false;
	int ELEVE_COUNT_LIMIT_DEMO = 50;
	int ELEVE_COUNT_LIMIT_NOT_LICENSED = 15;
	boolean SHOW_LICENSE = false;

	/************************************************************
	 * Clef salt de cryptage md5 *
	 ************************************************************/
	String SALT_PWD = "N@WAT_@EXP54G@_FRP5MA5_@";
	String SALT_LIC = "N@PE25@KDD_EE888_GT15K_@";

	/**
	 * Fichier de config o� les valeurs sont lues.
	 */
	String CONFIG_FILE = "ConstantesFramework";

}