function showPopup(url) {
	// if(url){
	// a=window.open("about:blank", "v_notmodalpopup", winSettings);
	// a.close();
	// var winSettings = 'location=no, menubar=no, toolbars=no, status=yes,
	// directories=no, copyHistory=no, resizable=no, scrollbars=yes,
	// alwaysRaised=yes';
	// var wOpen = window.open(url,"v_notmodalpopup",winSettings);
	// wOpen.focus();
	// wOpen.moveTo( 0, 0 );
	// wOpen.resizeTo( screen.availWidth, screen.availHeight );
	// }
}

/**
 * Permet de controler et formater la saisie des heures
 * 
 * @param vHeureName
 * @param vHeureValue
 * @param e
 * @returns {Boolean}
 */
function formatHour(vHeureName, vHeureValue, e) {
	var strSeperator = ":";
	var whichCode = (window.Event) ? e.which : e.keyCode;

	// Eliminate all the ASCII codes that are not valid
	var alphaCheck = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ/-";
	if ((alphaCheck.indexOf(vHeureValue) >= 1 && whichCode != 16)
			|| (vHeureValue.length > 5)) {
		vHeureName.value = vHeureName.value.substr(0, (vHeureValue.length - 1));
		return false;
	}

	// Ignore the Netscape value for backspace. IE has no value
	if (whichCode == 8) {
		return false;
	} else {
		// Create numeric string values for 0123456789/
		// The codes provided include both keyboard and keypad values
		var strCheck = '16,37,38,39,40,46,47,48,49,50,51,52,53,54,55,56,57,58,59,95,96,97,98,99,100,101,102,103,104,105';
		if (strCheck.indexOf(whichCode) != -1) {
			if (vHeureValue.length == 2) {
				vHeureName.value = vHeureName.value + strSeperator;
			}
			return true;
		} else {
			// If the value is not in the string return the string minus the
			// last
			// key entered.
			vHeureName.value = vHeureName.value.substr(0,
					(vHeureValue.length - 1));
			return false;

		}
	}
}

/**
 * Permet de controler et formater la saisie des chiffre entiers
 * 
 * @param event
 */
function formatEntier(event) {
	// Compatibilit\u00E9 IE / Firefox
	if (!event && window.event) {
		event = window.event;
	}
	// IE
	if (event.keyCode < 48 || event.keyCode > 57) {
		event.returnValue = false;
		event.cancelBubble = true;
	}
	// DOM
	if (event.which < 48 || event.which > 57) {
		event.preventDefault();
		event.stopPropagation();
	}
}

/**
 * Permet de controler et formater la saisie des chiffre decimales
 * 
 * @param value
 * @param event
 */
function formatDecimale(value, event) {
	// Compatibilit\u00E9 IE / Firefox
	if (!event && window.event) {
		event = window.event;
	}

	// alert(event.keyCode);

	if (event.keyCode == 44) {
		event.keyCode = 46;
	}

	if (event.which == 44) {
		event.which = 46;
	}

	// Pour ne pas saisir le point deux fois
	// IE
	if (event.keyCode == 46) {
		if (value.indexOf(".", 0) != -1 || value.length == 0) {
			event.returnValue = false;
			event.cancelBubble = true;
		}
	} else {

		if ((event.keyCode < 48 || event.keyCode > 57)) {
			event.returnValue = false;
			event.cancelBubble = true;
		}
	}

	// DOM
	if (event.which == 46) {
		if (value.indexOf(".", 0) != -1 || value.length == 0) {
			event.preventDefault();
			event.stopPropagation();
		}
	} else {
		if ((event.which < 48 || event.which > 57)) {
			event.preventDefault();
			event.stopPropagation();
		}
	}
}

/**
 * Permet de soustraire deux valeurs et mettre le resultat dans le champ
 * resultat
 * 
 * @param champ1
 * @param champ2
 * @param champResultat
 */
function soustraire(champ1, champ2, champResultat, decimale) {

	var verif = /^\d*\.?\d*$/;
	var resultat = 0;

	if (verif.exec(champ1.value) == null) {
		champ1.value = "";
	}
	if (verif.exec(champ2.value) == null) {
		champ2.value = "";
	}
	if (champ1.value != "" && champ2.value != "") {
		resultat = parseFloat(champ1.value) - parseFloat(champ2.value);
	} else {
		champResultat.value = "";
	}

	if (parseFloat(resultat) > 0) {
		champResultat.value = resultat.toFixed(decimale);
	} else {
		champResultat.value = "";
	}
}

/**
 * Permet d'additionner deux valeurs et mettre le resultat dans le champ
 * resultat
 * 
 * @param champ1
 * @param champ2
 * @param champResultat
 */
function additionner(champ1, champ2, champResultat, decimale) {

	var verif = /^\d*\.?\d*$/;
	var resultat = 0;

	if (verif.exec(champ1.value) == null) {
		champ1.value = "";
	}
	if (verif.exec(champ2.value) == null) {
		champ2.value = "";
	}
	if (champ1.value != "" && champ2.value != "") {
		resultat = parseFloat(champ1.value) + parseFloat(champ2.value);
	} else {
		champResultat.value = "";
	}

	if (parseFloat(resultat) > 0) {
		champResultat.value = resultat.toFixed(decimale);
	} else {
		champResultat.value = "";
	}
}

/**
 * Permet d'additionner deux valeurs et mettre le resultat dans le champ
 * resultat
 * 
 * @param champ1
 * @param champ2
 * @param champResultat
 */
function multiplier(champ1, champ2, champResultat, decimale) {

	var verif = /^\d*\.?\d*$/;
	var resultat = 0;

	if (verif.exec(champ1.value) == null) {
		champ1.value = "";
	}
	if (verif.exec(champ2.value) == null) {
		champ2.value = "";
	}
	if (champ1.value != "" && champ2.value != "") {
		resultat = parseFloat(champ1.value) * parseFloat(champ2.value);
	} else {
		champResultat.value = "";
	}

	if (parseFloat(resultat) > 0) {
		champResultat.value = resultat.toFixed(decimale);
	} else {
		champResultat.value = "";
	}
}

function hideOnSuccess(args, dialog) {
    if (args.validationFailed) {
        return false;
    }
    if (args.result != null) {
        if (args.result.failed) {
            return false;
        }
    }
    dialog.hide();
    return true;
}