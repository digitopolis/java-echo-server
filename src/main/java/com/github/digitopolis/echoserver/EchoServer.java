package com.github.digitopolis.echoserver;

import com.github.digitopolis.echoserver.cli.CLI;
import com.github.digitopolis.echoserver.socket.SocketCreator;

import java.net.ServerSocket;

public class EchoServer {
    private final int port;
    private final CLI cli = new CLI();
    Boolean listening = true;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() {
        try (
                ServerSocket serverSocket = SocketCreator.createServerSocket(port)
                ){
                cli.printMessage("Socket successfully created at port " + port);
                while (listening) {
                    new EchoThread(SocketCreator.createServerClientSocket(serverSocket)).start();
                }
        } catch (Exception e) {
            cli.printMessage(e.getMessage());
        }
    }
}
