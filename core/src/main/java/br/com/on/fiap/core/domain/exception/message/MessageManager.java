package br.com.on.fiap.core.domain.exception.message;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

public class MessageManager {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("message");

    private MessageManager() {}

    public static String getMessage(String key, Object... args) {
        return getFormattedMessage(key)
                .map(message -> formatMessage(message, args))
                .orElse(key);
    }

    private static Optional<String> getFormattedMessage(String key) {
        try {
            return Optional.of(RESOURCE_BUNDLE.getString(key));
        } catch (MissingResourceException ex) {
            return Optional.empty();
        }
    }

    private static String formatMessage(String message, Object... args) {
        return (args != null && args.length > 0) ? MessageFormat.format(message, args) : message;
    }
}
