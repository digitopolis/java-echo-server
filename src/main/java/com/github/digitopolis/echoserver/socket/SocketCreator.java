package com.github.digitopolis.echoserver.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketCreator {
    public static ServerSocket createServerSocket(int port) throws IOException {
        return new ServerSocket(port);
    }
}
