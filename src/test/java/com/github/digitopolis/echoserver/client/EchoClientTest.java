package com.github.digitopolis.echoserver.client;

import com.github.digitopolis.echoserver.socket.SocketCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoClientTest {
    @Test
    public void connectsToServerOnGivenPort() throws IOException {
        ServerSocket testServer = SocketCreator.createServerSocket(8080);
        final EchoClient echoClient = new EchoClient();
        assertDoesNotThrow(() -> EchoClient.connect("127.0.0.1", 8080));
        testServer.close();
    }
}
