package org.prgrms.kdt.exception;

public class WrongRangeInputException extends RuntimeException {
    public WrongRangeInputException() {
    }

    public WrongRangeInputException(String message) {
        super(message);
    }
}