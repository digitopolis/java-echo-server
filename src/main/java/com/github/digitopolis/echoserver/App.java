package com.github.digitopolis.echoserver;

import com.github.digitopolis.echoserver.cli.CLI;

import java.io.IOException;

public class App 
{
    private static CLI cli = new CLI();
    private EchoServer echoServer;

    public static void main( String[] args ) {
        startServer();
    }

    public static void startServer() {
        cli.printMessage("Enter a port number to start an echo server:");
        String port = cli.getInput();
        EchoServer echoServer = new EchoServer(Integer.parseInt(port));
        echoServer.start();
    }
}
