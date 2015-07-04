package com.ayouris.nawat.util.converter;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.util.StringUtils;

/**
 * Utilitaires de conversion de formats.
 * 
 * @author Khalid CHAMAKH (elBissat)
 * @version 1.0
 * 
 */
public final class Converter {

	private static SimpleDateFormat databaseDateFormat;
	private static SimpleDateFormat databaseCompleteDateFormat;
	private static SimpleDateFormat databaseCompleteDateFormat2;
	private static SimpleDateFormat databaseCompleteDateFormatSansSeconde;

	private static SimpleDateFormat dateFormatNoSlashCourt;
	private static SimpleDateFormat dateFormatNoSlashLong;
	private static SimpleDateFormat dateFormatSlashCourt;
	private static SimpleDateFormat dateFormatSlashLong;
	private static SimpleDateFormat dateFormatSlashLongHeure;

	private static SimpleDateFormat dateFormatJourMois;

	private static SimpleDateFormat dateFormatAnneeMoisJourHeureMinutes;

	private static SimpleDateFormat hourFormat;
	private static DecimalFormatSymbols decimalFormat;
	private static NumberFormat numberFormat;
	private static NumberFormat numberFormat3;

	private static final String INDICE_HEURE = "h";
	private static final String INDICE_MINUTE = "m";

	static {

		dateFormatNoSlashLong = new SimpleDateFormat("ddMMyyyy");
		dateFormatNoSlashLong.setLenient(false);

		dateFormatNoSlashCourt = new SimpleDateFormat("ddMMyy");
		dateFormatNoSlashCourt.setLenient(false);

		dateFormatSlashCourt = new SimpleDateFormat("dd/MM/yy");
		dateFormatSlashCourt.setLenient(false);

		dateFormatSlashLong = new SimpleDateFormat("dd/MM/yyyy");
		dateFormatSlashLong.setLenient(false);

		dateFormatSlashLongHeure = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		dateFormatSlashLong.setLenient(false);

		dateFormatJourMois = new SimpleDateFormat("dd/MM");

		dateFormatAnneeMoisJourHeureMinutes = new SimpleDateFormat("yyyyMMddHHmm");

		hourFormat = new SimpleDateFormat("HH:mm");
		decimalFormat = new DecimalFormatSymbols();
		decimalFormat.setDecimalSeparator('.');
		numberFormat = new DecimalFormat("0.00", decimalFormat);
		numberFormat3 = new DecimalFormat("0.000", decimalFormat);
	}

	/**
	 * Constructeur priv�.
	 */
	private Converter() {
		// On ne fait rien
	}

	/**
	 * Renvoi un tableau de String en ayant fait un trim sur chaque chaine
	 * 
	 * @param arg
	 *            tableau de string � tronquer
	 * @return null si arg est null ou de longueur nulle apr�s application de trim(), le tableau de cha�nes tronqu�es sinon
	 */
	public static String[] trimString(String[] arg) {
		if (arg == null) {
			return null;
		}

		int taille = arg.length;
		List<String> listeResultats = new ArrayList<String>(taille);

		for (int i = 0; i < taille; i++) {
			arg[i] = trimString(arg[i]);
			if (arg[i] != null) {
				listeResultats.add(arg[i]);
			}
		}

		String[] resultats = listeResultats.toArray(new String[listeResultats.size()]);

		if (resultats != null && resultats.length == 0) {
			return null;
		}

		return resultats;
	}

	/**
	 * Version prot�g�e contre les NPE de java.lang.String::trim()
	 * 
	 * @param arg
	 *            cha�ne � tronquer
	 * @return null si arg est null ou de longueur nulle apr�s application de trim(), la cha�ne tronqu�e sinon
	 */
	public static String trimString(String arg) {
		if (arg == null) {
			return null;
		}

		String retour = arg.trim();
		if (retour.length() == 0) {
			return null;
		}
		return retour;
	}

	/**
	 * Convertit une date en une cha�ne de caract�res.
	 * 
	 * @param arg
	 *            la date
	 * @return cha�ne de caract�res au format jj/mm/aaaa
	 */
	public static String dateToString(Date arg) {
		if (arg == null) {
			return null;
		}
		String res = null;
		res = dateFormatSlashLong.format(arg);

		return res;
	}

	/**
	 * Convertit une date en une cha�ne de caract�res.
	 * 
	 * @param arg
	 *            la date
	 * @return cha�ne de caract�res au format hh:mm
	 */
	public static String dateToHeureString(Date arg) {

		if (arg == null) {
			return null;
		}
		String res = null;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		res = sdf.format(arg);

		return res;
	}

	/**
	 * Convertit une date en une cha�ne de caract�res souhaitable pour utilisation dans une requ�te SQL.
	 * 
	 * @param date
	 *            la Date � convertir.
	 * @return date format�e pour SQL92.
	 */
	public static String dateToStringDatabase(Date date) {
		if (date == null) {
			return null;
		}
		return (databaseDateFormat.format(date));
	}

	/**
	 * Convertit une date en une cha�ne de caract�res souhaitable pour utilisation dans une requ�te SQL avec l'heure.
	 * 
	 * @param date
	 *            la date � convertir.
	 * @return date format�e pour SQL92 avec l'heure.
	 */
	public static String dateToStringDatabaseComplete(Date date) {
		if (date == null) {
			return null;
		}
		return (databaseCompleteDateFormat.format(date));
	}

	/**
	 * Convertit une date en une cha�ne de caract�res souhaitable pour utilisation dans une requ�te SQL avec l'heure.
	 * 
	 * @param date
	 *            la date � convertir.
	 * @return date format�e pour SQL92 avec l'heure.
	 */
	public static String dateToStringDatabaseComplete2(Date date) {
		if (date == null) {
			return null;
		}
		return (databaseCompleteDateFormat2.format(date));
	}

	/**
	 * Convertit une date en une cha�ne de caract�res souhaitable pour utilisation dans une requ�te SQL avec l'heure sans secondes.
	 * 
	 * @param date
	 *            la date � convertir.
	 * @return date format�e pour SQL92 avec l'heure sans secondes.
	 */
	public static String dateToStringDatabaseCompleteSansSecondes(Date date) {
		if (date == null) {
			return null;
		}
		return (databaseCompleteDateFormatSansSeconde.format(date));
	}

	/**
	 * Convertit une date en une cha�ne de caract�res avec l'heure.
	 * 
	 * @param date
	 *            la date � convertir.
	 * @return date format�e avec l'heure.
	 */
	public static String dateToStringCompleteColle(Date date) {
		if (date == null) {
			return null;
		}
		return (dateFormatAnneeMoisJourHeureMinutes.format(date));
	}

	/**
	 * Convertit une date en une cha�ne de caract�res souhaitable pour affichage.
	 * 
	 * @param date
	 *            la Date � convertir
	 * @param locale
	 *            le locale de l'utilisateur.
	 * @return date format�e pour affichage. Exemple "Lundi 23 Novembre 2003".
	 */
	public static String dateToStringAffichage(Date date, Locale locale) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat dateFormatAffichage = new SimpleDateFormat("EEEE dd MMMM yyyy", locale);
		return (dateFormatAffichage.format(date));
	}

	/**
	 * Convertit une date en une cha�ne de caract�res souhaitable pour affichage.
	 * 
	 * @param date
	 *            la Date � convertir
	 * @param locale
	 *            le locale de l'utilisateur.
	 * @return date format�e pour affichage. Exemple "23 Novembre 2003".
	 */
	public static String dateToStringAffichage2(Date date, Locale locale) {
		if (date == null) {
			return null;
		}

		SimpleDateFormat dateFormatAffichage = new SimpleDateFormat("dd MMMM yyyy", locale);
		return (dateFormatAffichage.format(date));
	}

	/**
	 * Convertit une date en une cha�ne de caract�res souhaitable pour affichage.
	 * 
	 * @param date
	 *            la Date � convertir
	 * @return date format�e pour affichage du jour et du mois. Exemple "23/12".
	 */
	public static String dateToStringJourMois(Date date) {
		if (date == null) {
			return null;
		}

		return (dateFormatJourMois.format(date));
	}

	/**
	 * Convertit un temps en une cha�ne de caract�res souhaitable pour un affichage utilisateur.
	 * 
	 * @param d
	 *            la Date � convertir
	 * @return heure/minute au format hh:mm
	 */
	public static String dateHourToString(Date d) {
		if (d == null) {
			return null;
		}

		return (hourFormat.format(d));
	}

	/**
	 * Convertit une cha�ne de caract�re en une date.
	 * 
	 * @param arg
	 *            date format�e en jj/mm/aaaa ou jj/mm/aa ou jjmmaaaa ou jjmmaa ou jj/mm/aaaa hh:mm.
	 * @return la Date correspondante ou null si aucune interpr�tation n'est possible
	 */
	public synchronized static Date stringToDate(String arg) {
		if (arg == null || trimString(arg) == null) {
			return null;
		}

		Date date = null;
		String strDate = trimString(arg);
		int taille = strDate.length();

		switch (taille) {

		case 6:
			try {
				date = dateFormatNoSlashCourt.parse(strDate);
			} catch (ParseException pe) {
				// Pas une date au format JJMMAA.
			}
			break;

		case 8:
			int contientSlash = strDate.indexOf("/");
			if (contientSlash == -1) {
				try {
					date = dateFormatNoSlashLong.parse(strDate);
				} catch (ParseException pe) {
					// Pas une date au format JJMMAAAA.
				}
			} else {
				try {
					date = dateFormatSlashCourt.parse(strDate);
				} catch (ParseException pe) {
					// Pas une date au format JJ/MM/AA.
				}
			}
			break;

		case 10:
			try {
				date = dateFormatSlashLong.parse(strDate);
			} catch (ParseException pe) {
				// Pas une date au format JJ/MM/AAAA.
			}
			break;

		case 16:
			try {
				date = dateFormatSlashLongHeure.parse(strDate);
			} catch (ParseException pe) {
				// Pas une date au format JJ/MM/AAAA.
			}
			break;

		default:
			// Pas une longueure de date valide.
			break;

		}

		return date;
	}

	/**
	 * Convertit une cha�ne de caract�re en une date.
	 * 
	 * @param arg
	 *            date format�e en aaaa-mm-jj
	 * @return la Date correspondante ou null si aucune interpr�tation n'est possible
	 */
	public synchronized static Date stringDbToDate(String arg) {
		if (arg == null) {
			return null;
		}
		Date date = null;
		try {
			date = databaseDateFormat.parse(arg);
		} catch (ParseException e) {
			// NOTHING TO DO
		}
		return date;
	}

	/**
	 * Convertit un nombre r�el en une cha�ne de caract�res souhaitable pour un affichage utilisateur.
	 * 
	 * @param arg
	 *            le nombre � convertir
	 * @return nombre au format xxxxxx,x
	 */
	public static String doubleToString(double arg) {
		return numberFormat.format(arg);
	}

	/**
	 * Convertit un nombre r�el en une cha�ne de caract�res souhaitable pour un affichage utilisateur.
	 * 
	 * @param arg
	 *            le nombre � convertir
	 * @return null si arg est null, nombre au format xxxxxx,x sinon
	 */
	public static String doubleToString(Double arg) {
		if (arg == null) {
			return null;
		}
		return numberFormat.format(arg.doubleValue());
	}

	/**
	 * Convertit un nombre r�el en une cha�ne de caract�res souhaitable pour un affichage utilisateur en precisant nbre des chiffes apr�s la
	 * virgule
	 * 
	 * @param arg
	 *            le nombre � convertir
	 * @return null si arg est null, nombre au format xxxxxx,x sinon
	 */
	public static String doubleToString(Double arg, int minDigits) {
		if (arg == null) {
			return null;
		}
		numberFormat.setMinimumFractionDigits(minDigits);
		numberFormat.setMaximumFractionDigits(minDigits);
		return numberFormat.format(arg.doubleValue());
	}

	/**
	 * Convertit un nombre r�el en une cha�ne de caract�res souhaitable pour un affichage utilisateur.
	 * 
	 * @param arg
	 *            le nombre � convertir
	 * @return nombre au format xxxxxx,x
	 */
	public static String floatToString(float arg) {
		return numberFormat.format(arg);
	}

	/**
	 * Convertit un nombre r�el en une cha�ne de caract�res souhaitable pour un affichage utilisateur.
	 * 
	 * @param arg
	 *            le nombre � convertir
	 * @return nombre au format xxxxxx,x
	 */
	public static String floatVirguleToString(float arg) {
		String result = floatToString(arg);
		if (result.indexOf(".") != -1) {
			result = StringUtils.replace(result, ".", ",");
		}
		return result;
	}

	/**
	 * Convertit un nombre r�el en une cha�ne de caract�res souhaitable pour un affichage utilisateur.
	 * 
	 * @param arg
	 *            le nombre � convertir
	 * @return null si arg est null, nombre au format xxxxxx,x sinon
	 */
	public static String floatToString(Float arg) {
		if (arg == null) {
			return null;
		}
		return numberFormat.format(arg.doubleValue());
	}

	/**
	 * Convertit un nombre r�el en une cha�ne de caract�res souhaitable pour un affichage utilisateur en precisant nbre des chiffes apr�s la
	 * virgule
	 * 
	 * @param arg
	 *            le nombre � convertir
	 * @return null si arg est null, nombre au format xxxxxx,x sinon
	 */
	public static String floatToString(Float arg, int minDigits) {
		if (arg == null) {
			return null;
		}
		numberFormat.setMinimumFractionDigits(minDigits);
		numberFormat.setMaximumFractionDigits(minDigits);
		return numberFormat.format(arg.floatValue());
	}

	/**
	 * Convertit un nombre r�el en une cha�ne de caract�res souhaitable pour un affichage utilisateur en precisant nbre des chiffes apr�s la
	 * virgule
	 * 
	 * @param arg
	 *            le nombre � convertir
	 */
	public static String floatToString(float arg, int minDigits) {
		numberFormat.setMinimumFractionDigits(minDigits);
		numberFormat.setMaximumFractionDigits(minDigits);
		return numberFormat.format(arg);
	}

	/**
	 * Convertit un nombre r�el en une cha�ne de caract�res(trois chiffres apr�s la virgule) souhaitable pour un affichage utilisateur.
	 * 
	 * @param arg
	 *            le nombre � convertir
	 * @return null si arg est null, nombre au format xxxxxx,xxx sinon
	 */
	public static String floatToString3(Float arg) {
		if (arg == null) {
			return null;
		}
		return numberFormat3.format(arg.doubleValue());
	}

	/**
	 * Convertit un nombre r�el en une cha�ne de caract�res(trois chiffres apr�s la virgule) souhaitable pour un affichage utilisateur.
	 * 
	 * @param arg
	 *            le nombre � convertir
	 * @return null si arg est null, nombre au format xxxxxx,xxx sinon
	 */
	public static String floatToString3(float arg) {
		return numberFormat3.format(arg);
	}

	/**
	 * Convertit un long en une cha�ne de caract�res.
	 * 
	 * @param arg
	 *            le nombre
	 * @return cha�ne de caract�res
	 */
	public static String longToString(long arg) {
		return (String.valueOf(arg));
	}

	/**
	 * Convertit un long en une cha�ne de caract�res.
	 * 
	 * @param arg
	 *            le nombre
	 * @return null si arg est null, cha�ne de caract�res sinon
	 */
	public static String longToString(Long arg) {
		if (arg == null) {
			return null;
		}
		return (arg.toString());
	}

	/**
	 * Convertit un long en une cha�ne de caract�res.
	 * 
	 * @param arg
	 *            le nombre
	 * @return cha�ne de caract�res
	 */
	public static String intToString(int arg) {
		return (String.valueOf(arg));
	}

	/**
	 * Convertit un int en une cha�ne de caract�res.
	 * 
	 * @param arg
	 *            le nombre
	 * @return null si arg est null, cha�ne de caract�res sinon
	 */
	public static String integerToString(Integer arg) {
		if (arg == null) {
			return null;
		}
		return (arg.toString());
	}

	/**
	 * Convertit une String en un Long.
	 * 
	 * @param arg
	 *            le nombre
	 * @return null si arg est null, ou est de longueur nulle, le Long correspondant sinon.
	 */
	public static Long stringToLong(String arg) {
		if (arg == null || arg.trim().length() == 0) {
			return null;
		}
		// return (new Long(Long.parseLong(arg)));
		// return new Long(arg);
		try {
			Long l = new Long(arg);
			return l;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Convertit une String en un Short.
	 * 
	 * @param arg
	 *            le nombre
	 * @return null si arg est null, ou est de longueur nulle, le Short correspondant sinon.
	 */
	public static Short stringToBigShort(String arg) {
		if (arg == null || arg.trim().length() == 0) {
			return null;
		}

		try {
			Short s = new Short(arg);
			return s;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Convertit une String en un short.
	 * 
	 * @param arg
	 *            le nombre
	 * @return 0 si arg est null, ou est de longueur nulle, le short correspondant sinon.
	 */
	public static short stringToShort(String arg) {
		if (arg == null || arg.trim().length() == 0) {
			return 0;
		}

		return Short.parseShort(arg);
	}

	/**
	 * Convertit une String en un double.
	 * 
	 * @param arg
	 *            le nombre au format xxxxxxxxxxxxx,xxx
	 * @return 0.0 si arg est null, ou est de longueur nulle, le double correspondant sinon.
	 */
	public static double stringToDouble(String arg) {
		if (arg == null) {
			return 0.00;
		}

		arg = arg.trim();

		if (arg.length() == 0) {
			return 0.00;
		}

		ParsePosition p = new ParsePosition(0);
		Number number = numberFormat.parse(arg, p);

		int indexArret = p.getIndex();
		if (indexArret != arg.length()) {
			return 0.00;
		}

		return number.doubleValue();
	}

	/**
	 * Convertit une String en un Double.
	 * 
	 * @param arg
	 *            le nombre au format xxxxxxxxxxxxx,xxx
	 * @return null si arg est null, ou est de longueur nulle, ou arg est non interpr�table le Double correspondant sinon.
	 */
	public static Double stringToBigDouble(String arg) {
		if (arg == null) {
			return null;
		}

		arg = arg.trim();

		if (arg.length() == 0) {
			return null;
		}

		ParsePosition p = new ParsePosition(0);
		Number number = numberFormat.parse(arg, p);

		int indexArret = p.getIndex();
		if (indexArret != arg.length()) {
			return null;
		}

		double result = number.doubleValue();

		return (new Double(result));
	}

	/**
	 * Convertit une String en un Integer.
	 * 
	 * @param arg
	 *            le nombre
	 * @return null si arg est null, ou est de longueur nulle, l'Integer correspondant sinon.
	 */
	public static Integer stringToInteger(String arg) {
		if (arg == null || arg.trim().length() == 0) {
			return null;
		}

		try {
			Integer i = new Integer(arg);
			return i;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Convertit une String en un int, attention au NumberFormatException.
	 * 
	 * @param arg
	 *            le nombre
	 * @return 0 si arg est null, ou est de longueur nulle, l'Integer Long correspondant sinon.
	 * @throws NumberFormatException
	 *             Attention, les appels n'ont pas � catcher l'exception dans leur d�claration.
	 */
	public static int stringToInt(String arg) throws NumberFormatException {
		if (arg == null || arg.trim().length() == 0) {
			return 0;
		}
		return (Integer.parseInt(arg));
	}

	/**
	 * Convertit une String en un int.
	 * 
	 * @param arg
	 *            le nombre
	 * @return 0 si arg est null, ou est de longueur nulle, -1 si le nombre n'est pas un entier, l'Integer Long correspondant sinon.
	 */
	public static int stringToInt2(String arg) {
		if (arg == null || arg.trim().length() == 0) {
			return 0;
		}
		try {
			return (Integer.parseInt(arg));

		} catch (NumberFormatException e) {
			return -1;
		}
	}

	/**
	 * Convertit une String en un Float.
	 * 
	 * @param arg
	 *            le nombre
	 * @return null si arg est null, ou est de longueur nulle, le Float correspondant sinon.
	 */
	public static Float stringToFloat(String arg) {
		if (arg == null || arg.trim().length() == 0) {
			return null;
		}

		try {
			Float f = new Float(arg);
			return f;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Convertit une String en un float, attention au NumberFormatException.
	 * 
	 * @param arg
	 *            le nombre
	 * @return 0 si arg est null, ou est de longueur nulle, l'Integer Long correspondant sinon.
	 * @throws NumberFormatException
	 *             Attention, les appels n'ont pas � catcher l'exception dans leur d�claration.
	 */
	public static float stringTofloat(String arg) throws NumberFormatException {
		if (arg == null || arg.trim().length() == 0) {
			return 0;
		}
		return (Float.parseFloat(arg));
	}

	/**
	 * Convertit une String en un float, attention au NumberFormatException.
	 * 
	 * @param arg
	 *            le nombre
	 * @return 0 si arg est null, ou est de longueur nulle, l'Integer Long correspondant sinon.
	 * @throws NumberFormatException
	 *             Attention, les appels n'ont pas � catcher l'exception dans leur d�claration.
	 */
	public static float stringTofloatVirgule(String arg) throws NumberFormatException {
		if (arg == null || arg.trim().length() == 0) {
			return 0;
		} else {
			arg = StringUtils.replace(arg, ",", ".");
		}

		return (Float.parseFloat(arg));
	}

	/**
	 * Convertit un tableau de byte en cha�ne de caract�re. ATTENTION : l'encoding n'est pas sp�cifi�.
	 * 
	 * @param arg
	 *            le tableau de byte.
	 * @return la cha�ne de caract�res correspondant.
	 */
	public static String byteArrayToString(byte[] arg) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(arg, 0, arg.length);
		return baos.toString();
	}

	/**
	 * Convertit un tableau de byte en cha�ne de caract�re en fonction de l'encoding demand�.
	 * 
	 * @param arg
	 *            le tableau de byte.
	 * @param encoding
	 *            l'encoding � utiliser
	 * @return la cha�ne de caract�res correspondant.
	 * @throws UnsupportedEncodingException
	 *             si l'encodage demand� n'est pas disponible.
	 */
	public static String byteArrayToString(byte[] arg, String encoding) throws UnsupportedEncodingException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(arg, 0, arg.length);
		return baos.toString(encoding);
	}

	/**
	 * Tronque une cha�ne de caract�re avec protection contre les exceptions.
	 * 
	 * @param chaine
	 *            la chaine � tronquer
	 * @param lng
	 *            longueur de la cha�ne r�sultante
	 * @return null si chaine est null, une cha�ne de longueur lng si chaine �tait plus grand, ou la chaine initiale si elle est de longueur
	 *         inf�rieure � lng
	 */
	public static String truncate(String chaine, int lng) {
		if (chaine == null || chaine.length() <= lng) {
			return chaine;
		}

		return chaine.substring(0, lng);
	}

	/**
	 * Renvoie une cha�ne de la longueur souhait�e, par troncature ou extension.
	 * 
	 * @param chaine
	 *            la cha�ne de caract�res de base
	 * @param lg
	 *            longueur finale d�sir�e
	 * @param c
	 *            caract�re servissant au remplissage � droite si chaine est trop courte
	 * @return une cha�ne de longueur lg identique � chaine sur les min(lg, chaine.trim().length()) premiers caract�res
	 */
	public static String fitChaine(String chaine, int lg, char c) {
		String result = null;
		if (chaine == null) {
			chaine = "";
		} else {
			chaine = chaine.trim();
		}
		int lgChaine = chaine.length();

		if (lgChaine == lg) {
			result = chaine;
		} else if (lgChaine > lg) {
			result = chaine.substring(0, lg);
		} else {
			char[] chars = new char[lg];
			chaine.getChars(0, lgChaine, chars, 0);
			Arrays.fill(chars, lgChaine, lg, c);
			result = new String(chars);
		}

		return result;
	}

	/**
	 * Renvoie une cha�ne de la longueur souhait�e, par troncature ou extension.
	 * 
	 * @param chaine
	 *            la cha�ne de caract�res de base
	 * @param lg
	 *            longueur finale d�sir�e
	 * @param c
	 *            caract�re servissant au remplissage � gauche si chaine est trop courte
	 * @return une cha�ne de longueur lg identique � chaine sur les min(lg, chaine.trim().length()) derniers caract�res
	 */
	public static String fitChaineGauche(String chaine, int lg, char c) {
		String result = null;
		if (chaine == null) {
			chaine = "";
		} else {
			chaine = chaine.trim();
		}
		int lgChaine = chaine.length();

		if (lgChaine == lg) {
			result = chaine;
		} else if (lgChaine > lg) {
			result = chaine.substring(lgChaine - lg);
		} else {
			char[] chars = new char[lg];
			chaine.getChars(0, lgChaine, chars, lg - lgChaine);
			Arrays.fill(chars, 0, lg - lgChaine, c);
			result = new String(chars);
		}

		return result;
	}

	/**
	 * Convertit une String (ex: 7h30mn)en un Float (ex:7,5).
	 * 
	 * @param arg
	 *            heures minutes
	 * @return 0 si arg est null, ou n'est pas au format XXhYYmn, le Float correspondant sinon.
	 */
	public static Float heuresMinutesToCentiemes(String arg) {
		if (arg == null || !isHeuresMinutes(arg)) {
			return new Float(0);
		}
		int h = arg.indexOf(INDICE_HEURE);
		int mn = arg.indexOf(INDICE_MINUTE);

		float heures = stringTofloat(arg.substring(0, h));
		float minutes = stringTofloat(arg.substring(h + 1, mn));
		Float f = new Float(heures + minutes / 60);
		return f;
	}

	/**
	 * verifie si l'argument est au format heures minutes.
	 * 
	 * @param arg
	 *            heures en String
	 * @return null si arg est null, ou le String correspondant sinon.
	 */
	public static boolean isHeuresMinutes(String arg) {
		int h = arg.indexOf(INDICE_HEURE);
		int mn = arg.indexOf(INDICE_MINUTE);
		if (arg == null || h == -1 || mn == -1) {
			return false;
		}
		return true;
	}

	/**
	 * verifie si l'argument est format heures minutes.
	 * 
	 * @param arg
	 *            heures en Float
	 * @return null si arg est null, ou le String correspondant sinon.
	 */
	public static String centiemesToHeuresMinutes(Float arg) {
		if (null == arg)
			return null;
		String heures = intToString(arg.intValue());
		if (heures.length() == 1)
			heures = "0" + heures;
		String minutes = intToString(new Float((arg.floatValue() - arg.intValue()) * 60).intValue());
		if (minutes.length() == 1)
			minutes = "0" + minutes;
		return heures + INDICE_HEURE + " " + minutes + INDICE_MINUTE;
	}

	public static float Round(float number, int digit) {
		float p = (float) Math.pow(10, digit);
		number = number * p;
		float tmp = Math.round(number);
		return tmp / p;
	}

	public static Calendar convertToGmt(Calendar cal) {

		Date date = cal.getTime();
		TimeZone tz = cal.getTimeZone();
		long msFromEpochGmt = date.getTime();
		int offsetFromUTC = tz.getOffset(msFromEpochGmt);
		Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		gmtCal.setTime(date);
		gmtCal.add(Calendar.MILLISECOND, offsetFromUTC);

		return gmtCal;
	}

	public static String escapeUnicode(String input) {
		StringBuilder b = new StringBuilder(input.length());
		Formatter f = new Formatter(b);
		for (char c : input.toCharArray()) {
			if (c < 128) {
				b.append(c);
			} else {
				f.format("\\u%04x", (int) c);
			}
		}
		f.close();
		return b.toString();
	}

}