package org.prgrms.kdt.io;

import org.prgrms.kdt.exception.ErrorCode;
import org.prgrms.kdt.exception.voucher.WrongCommandException;

import java.util.stream.Stream;

public enum CommandType {
    CREAT("create"),
    LIST("list"),
    INITIALIZE("initialize"),
    DELETE("delete"),
    FIND("find"),
    UPDATE("update"),
    EXIT("exit");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public static CommandType selectType(String input) {
        return Stream.of(values())
                .filter(value -> value.command.equals(input))
                .findFirst()
                .orElseThrow(() -> new WrongCommandException(ErrorCode.WRONG_COMMAND_EXCEPTION.getMessage()));
    }
}

