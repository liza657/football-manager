package com.example.football_manager.exception;

public class LackOfFundsException extends RuntimeException {

    private static final String LACK_OF_FUNDS = "Lack of founds";

    public LackOfFundsException(String message) {
        super(message.isEmpty() ? LACK_OF_FUNDS : message);
    }

    public LackOfFundsException() {
        super(LACK_OF_FUNDS);
    }
}
