package com.github.digitopolis.echoserver;

import com.github.digitopolis.echoserver.cli.CLI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoThread extends Thread {
    private Socket socket;
    private CLI cli = new CLI();

    public EchoThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
                ){
                cli.printMessage("Client connected!");
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    out.println(inputLine);
                    if (inputLine.equals("Goodbye")) {
                        break;
                    }
                }
                socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
