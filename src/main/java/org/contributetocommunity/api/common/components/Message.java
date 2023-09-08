package org.contributetocommunity.api.common.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class Message {

    private final MessageSource messageSource;

    @Autowired
    public Message(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String message) {
        return messageSource.getMessage(message, null, Locale.getDefault());
    }

    public String getMessage(String message, Object[] params) {
        return messageSource.getMessage(message, params, Locale.getDefault());
    }

    public String getMessage(String message, Object[] params, Locale locale) {
        return messageSource.getMessage(message, params, locale);
    }

}
