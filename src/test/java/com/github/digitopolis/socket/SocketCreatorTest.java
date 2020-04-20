package com.github.digitopolis.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.github.digitopolis.echoserver.socket.SocketCreator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SocketCreatorTest {
    @Test
    public void testCreatesServerSocket() throws IOException {
        ServerSocket testSocket = SocketCreator.createServerSocket(8080);
        assertNotNull(testSocket);
        testSocket.close();
    }

    @Test
    public void testCreatesSocketAtGivenPort() throws IOException {
        ServerSocket testSocket = SocketCreator.createServerSocket(5555);
        assertEquals(testSocket.getLocalPort(), 5555);
        testSocket.close();
    }
}
