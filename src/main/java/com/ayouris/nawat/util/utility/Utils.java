package com.ayouris.nawat.util.utility;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.omnifaces.util.Faces;

public final class Utils {
	private static final ResourceBundle labelBundle = ResourceBundle.getBundle("i18n.labels", Faces.getLocale());
	private static final ResourceBundle messageBundle = ResourceBundle.getBundle("i18n.messages", Faces.getLocale());
	private static final ResourceBundle constantBundle = ResourceBundle.getBundle("i18n.constants", Faces.getLocale());

	public static String getBundleByKey(String message, Object... params) {

		if (labelBundle.containsKey(message)) {
			message = labelBundle.getString(message);
		}
		if (messageBundle.containsKey(message)) {
			message = messageBundle.getString(message);
		}
		if (constantBundle.containsKey(message)) {
			message = constantBundle.getString(message);
		}
		message = message.replace("'", "''");
		return MessageFormat.format(message, params);
	}

}
