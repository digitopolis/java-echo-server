package com.github.digitopolis.echoserver;

import com.github.digitopolis.echoserver.cli.CLI;
import com.github.digitopolis.echoserver.socket.SocketCreator;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer {
    private ServerSocket serverSocket;
    private final int port;
    private final CLI cli = new CLI();

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            serverSocket = SocketCreator.createServerSocket(port);
            cli.printMessage("Socket successfully created at port " + port);
            serverSocket.close();
        } catch (IOException e) {
            cli.printMessage(e.getMessage());
        }
    }
}
