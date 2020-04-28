package com.github.digitopolis.echoserver.validator;

import com.github.digitopolis.echoserver.cli.CLI;

public class Validator {
    private static final CLI cli = new CLI();

    public int validatePort(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            cli.printMessage("Invalid port: " + e.getMessage() + "\nUsing default port(5000)");
            return 5000;
        }
    }
}
