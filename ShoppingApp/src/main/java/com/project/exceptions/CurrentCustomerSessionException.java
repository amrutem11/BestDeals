package com.project.exceptions;

/**
 * @author tejas
 *
 */
public class CurrentCustomerSessionException extends Exception {

    /**
     *
     */
    public CurrentCustomerSessionException() {

    }

    /**
     * @param message
     */
    public CurrentCustomerSessionException(String message) {
        super(message);

    }

    /**
     * @param cause
     */
    public CurrentCustomerSessionException(Throwable cause) {
        super(cause);

    }

    /**
     * @param message
     * @param cause
     */
    public CurrentCustomerSessionException(String message, Throwable cause) {
        super(message, cause);

    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public CurrentCustomerSessionException(String message, Throwable cause, boolean enableSuppression,
                                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

    }

}