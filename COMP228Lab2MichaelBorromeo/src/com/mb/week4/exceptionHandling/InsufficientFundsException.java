package com.mb.week4.exceptionHandling;

/**
 * @author michael_borromeo	
 * @date 2026-10-01
 */

public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(String message) {
        super(message);
    }
}