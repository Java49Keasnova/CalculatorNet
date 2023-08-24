package telran.calculator.net;


import telran.calculator.service.CalculatorService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCalculatorAppl {
    private static final int PORT = 5000;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Servet is listening to port " + PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            clientRun(socket);
        }
    }

    private static void clientRun(Socket socket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintStream writer = new PrintStream(socket.getOutputStream())) {
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    System.out.println("client closed normally connection");
                    break;
                }
                String response = getResponse(line);
                writer.println(response);
            }
        } catch (Exception e) {
            System.out.println("Client closed connection abnormally");
        }
    }

    private static String getResponse(String line) {
        String response = "Wrong request structure";
        response = CalculatorService.getResponse(line);
        return response;
    }
}
