package br.com.on.fiap.hexagono.excecao.message;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessageManager {

	private static final ResourceBundle resourceMessage = ResourceBundle.getBundle("message");

	private MessageManager() {
		// vazio
	}

	public static String getMessage(String key, Object... args) {
		String text;

		try {
			text = resourceMessage.getString(key);
		} catch (MissingResourceException ex) {
			text = key;
		}

		if ((args != null) && (args.length > 0)) {
			text = MessageFormat.format(text, args);
		}

		return text;
	}
}
