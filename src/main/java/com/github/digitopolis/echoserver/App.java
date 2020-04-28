package com.github.digitopolis.echoserver;

import com.github.digitopolis.echoserver.cli.CLI;
import com.github.digitopolis.echoserver.validator.Validator;

public class App 
{
    private static final CLI cli = new CLI();
    private static final Validator validator = new Validator();
    private EchoServer echoServer;

    public static void main( String[] args ) {
        startServer();
    }

    public static void startServer() {
        cli.printMessage("Enter a port number to start an echo server:");
        String port = cli.getInput();
        EchoServer echoServer = new EchoServer(validator.validatePort(port));
        echoServer.start();
    }
}
