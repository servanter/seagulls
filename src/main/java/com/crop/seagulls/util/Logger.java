package com.crop.seagulls.util;

import java.text.MessageFormat;

import com.hp.hpl.sparta.xpath.ThisNodeTest;

public class Logger {

    private Class clazz;

    private org.apache.log4j.Logger log4j;

    public Logger(Class clazz) {
        this.clazz = clazz;
        this.log4j = org.apache.log4j.Logger.getLogger(clazz);
    }

    public static Logger getLogger(Class clazz) {
        return new Logger(clazz);
    }

    public void info(Object message, Object... params) {
        if (hasParams(params)) {
            log4j.info(MessageFormat.format(message.toString(), params));
        } else {
            log4j.info(message);
        }
    }

    public void error(Object message, Object... params) {
        if (hasParams(params)) {
            log4j.error(MessageFormat.format(message.toString(), params));
        } else {
            log4j.error(message);
        }
    }

    public void error(Object message, Throwable t, Object... params) {
        if (hasParams(params)) {
            log4j.error(MessageFormat.format(message.toString(), params), t);
        } else {
            log4j.error(message, t);
        }
    }
    
    public void debug(Object message, Object... params) {
        if (hasParams(params)) {
            log4j.debug(MessageFormat.format(message.toString(), params));
        } else {
            log4j.debug(message);
        }
    }

    public void debug(Object message, Throwable t, Object... params) {
        if (hasParams(params)) {
            log4j.debug(MessageFormat.format(message.toString(), params), t);
        } else {
            log4j.debug(message, t);
        }
    }


    private boolean hasParams(Object... params) {
        return params != null && params.length > 0;
    }
}
