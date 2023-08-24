package telran.calculator.net;

import telran.calculator.controller.CalculatorActions;
import telran.view.ConsoleInputOutput;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientCalculatorAppl {
    private static final String HOST = "localhost";
    private static final int PORT = 5000;


    public static void main(String[] args) throws Exception {
try (Socket socket = new Socket(HOST, PORT);
     PrintStream writer = new PrintStream(socket.getOutputStream());
     BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
    InputOutput io = new ConsoleInputOutput();

    ArrayList<Item> items = CalculatorActions.getCalculatorItems(writer, reader, HOST);

    items.add(Item.ofExit());
    Menu menu = new Menu("Calculator Net Client Application" , items);

    menu.perform(io);
}

    }
}
