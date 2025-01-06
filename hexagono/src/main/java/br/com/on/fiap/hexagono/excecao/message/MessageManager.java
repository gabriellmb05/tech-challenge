package br.com.on.fiap.hexagono.excecao.message;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;

public class MessageManager {

	private static final ResourceBundle resourceMessage = ResourceBundle.getBundle("message");

	public static String getMessage(String key, Object... args) {
		try {
			return Objects.nonNull(args) && args.length > 0
					? MessageFormat.format(resourceMessage.getString(key), args)
					: key;
		} catch (MissingResourceException ex) {
			return key;
		}
	}
}
