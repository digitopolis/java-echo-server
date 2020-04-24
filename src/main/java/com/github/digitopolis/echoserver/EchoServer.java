package com.github.digitopolis.echoserver;

import com.github.digitopolis.echoserver.cli.CLI;
import com.github.digitopolis.echoserver.socket.SocketCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private final int port;
    private PrintWriter out;
    private BufferedReader in;
    private final CLI cli = new CLI();

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() {
        try (
                ServerSocket serverSocket = SocketCreator.createServerSocket(port)
                ){
            cli.printMessage("Socket successfully created at port " + port);
                Socket clientSocket = SocketCreator.createServerClientSocket(serverSocket);
                cli.printMessage("Client connected!");
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                echo();
        } catch (Exception e) {
            cli.printMessage(e.getMessage());
        }
    }

    public void echo() throws IOException {
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            out.println(inputLine);
        }
    }
}
