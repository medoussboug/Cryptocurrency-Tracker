package com.example.cryptocurrencytracker.domain.exceptions;

import java.util.Objects;

public class GeneralException extends RuntimeException {
    /**
     * code : failed Method name
     * message : message to show
     */
    private final String code;
    private final String message;


    public GeneralException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public GeneralException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public GeneralException(String code, Exception exception) {
        super(exception.getMessage(), exception.getCause());
        this.code = code;
        this.message = exception.getMessage();
    }

    public GeneralException(Exception exception) {
        super(exception.getMessage(), exception.getCause());
        this.code = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralException that = (GeneralException) o;
        return code.equals(that.code) && message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }
}
