package com.github.digitopolis.echoserver.client;

import com.github.digitopolis.echoserver.socket.SocketCreator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void throwsErrorWhenServerNotAvailable() throws IOException {
        final EchoClient echoClient = new EchoClient();
        assertThrows(IOException.class, () -> echoClient.connect("127.0.0.1", 5000));
    }

    @Test
    public void sendsMessageAndReceivesEchoResponse() throws IOException {
        EchoClient client = new EchoClient();
        client.connect("127.0.0.1", 5000);
        String response = client.sendMessage("hello");
        assertEquals("hello", response);
    }

    @Test
    public void multipleClientsConnectOnPort() throws IOException {
        EchoClient client1 = new EchoClient();
        EchoClient client2 = new EchoClient();
        EchoClient client3 = new EchoClient();
        client1.connect("127.0.0.1", 5000);
        assertDoesNotThrow(() -> client2.connect("127.0.0.1", 5000));
        assertDoesNotThrow(() -> client3.connect("127.0.0.1", 5000));
    }

    @Test
    public void test5000ClientConnections() throws IOException {
        for (int i = 0; i < 5000; i++) {
            EchoClient client = new EchoClient();
            client.connect("127.0.0.1", 5000);
        }
    }
}
