package com.leot.exception;

@SuppressWarnings("all")
public class ExitException extends RuntimeException {

    public ExitException() {

    }

    public ExitException(String msg) {
        super(msg);
    }

}
