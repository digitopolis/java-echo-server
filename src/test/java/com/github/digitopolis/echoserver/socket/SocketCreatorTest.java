package com.github.digitopolis.echoserver.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SocketCreatorTest {
    @Test
    public void testCreatesServerSocket() throws IOException {
        ServerSocket testSocket = SocketCreator.createServerSocket(8080);
        assertNotNull(testSocket);
        testSocket.close();
    }

    @Test
    public void testThrowsErrorWhenUnableToCreateSocket() {
        assertThrows(Exception.class, () -> SocketCreator.createServerSocket(999999));
    }

    @Test
    public void testCreatesSocketAtGivenPort() throws IOException {
        ServerSocket testSocket = SocketCreator.createServerSocket(5555);
        assertEquals(testSocket.getLocalPort(), 5555);
        testSocket.close();
    }

    @Test
    public void testCreatesServerClientSocket() throws IOException {
        MockServerSocket mockServerSocket = new MockServerSocket();
        Socket clientSocket = SocketCreator.createServerClientSocket(mockServerSocket);
        assertNotNull(clientSocket);
    }

    @Test
    public void testCreatesNewClientSocket() throws IOException {
        ServerSocket testSocket = SocketCreator.createServerSocket(8080);
        Socket clientSocket = SocketCreator.createClientSocket("127.0.0.1", 8080);
        assertEquals(clientSocket.getInetAddress().getHostAddress(), "127.0.0.1");
        testSocket.close();
        clientSocket.close();
    }
}
