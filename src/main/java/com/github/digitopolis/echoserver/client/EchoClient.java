package com.github.digitopolis.echoserver.client;

import com.github.digitopolis.echoserver.socket.SocketCreator;
import com.github.digitopolis.echoserver.cli.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    private PrintWriter out;
    private BufferedReader in;
    private static CLI cli = new CLI();

    public static void main(String[] args) {
        cli.printMessage("Please enter a port number:");
        String port = cli.getInput();
        connect("127.0.0.1", Integer.parseInt(port));
    }

    public static void connect(String address, int port) {
        try (
                Socket clientSocket = SocketCreator.createClientSocket(address, port);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            cli.printMessage("Client connected at " + address + " on port " + port);
        } catch (IOException e) {
            cli.printMessage("Unable to connect at port " + port + ": " + e.getMessage());
        }
    }

    public static void chat() throws IOException {
        cli.printMessage("Send a message to the server:");
        String message = cli.getInput();
        String response = sendMessage(message);
        cli.printMessage(response);
    }

    public static String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine();
    }
}
