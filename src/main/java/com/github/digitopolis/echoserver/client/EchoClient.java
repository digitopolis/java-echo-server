package com.github.digitopolis.echoserver.client;

import com.github.digitopolis.echoserver.socket.SocketCreator;
import com.github.digitopolis.echoserver.cli.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    private static Socket clientSocket;
    private static PrintWriter out;
    private static BufferedReader in;
    private static CLI cli = new CLI();

    public static void main(String[] args) {
        cli.printMessage("Please enter a port number:");
        String port = cli.getInput();
        try {
            connect("127.0.0.1", Integer.parseInt(port));
            chat();
        } catch (Exception e) {
            cli.printMessage("Please try again on an available port");
        }
    }

    public static void connect(String address, int port) throws IOException {
        try {
            clientSocket = SocketCreator.createClientSocket(address, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            cli.printMessage("Client connected at " + address + " on port " + port);
        } catch (Exception e) {
            cli.printMessage("Unable to connect at port " + port + ": " + e.getMessage());
            throw e;
        }
    }

    public static void chat() throws IOException {
        String message, response = "";
        cli.printMessage("Send a message to the server:");
        while (!response.equals("Goodbye")){
            message = cli.getInput();
            response = sendMessage(message);
            cli.printMessage(response);
        }
    }

    public static String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine();
    }
}
