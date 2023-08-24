package telran.calculator.controller;

import telran.view.InputOutput;
import telran.view.Item;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CalculatorActions {

    private static final Set<String> SET_OPERATIONS = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
    static BufferedReader reader;
    static PrintStream writer;
    static String host;

    public static ArrayList<Item> getCalculatorItems(PrintStream writer, BufferedReader reader, String host) {
        CalculatorActions.writer = writer;
        CalculatorActions.reader = reader;
        CalculatorActions.host = host;
        ArrayList<Item> items = new ArrayList<>();
        items.add(Item.of("Send request", io -> getRequest(io, writer, reader, host)));
        return items;
    }

    private static void getRequest(InputOutput io, PrintStream writer, BufferedReader reader, String host) {
        String operation = io.readString("Enter operation" + SET_OPERATIONS, host, SET_OPERATIONS);
        double operand1 = io.readDouble("enter first number", "Wrong number");
        double operand2 = io.readDouble("enter second number", "Wrong number");
        writer.println(String.format("%s#%s#%s", operation, operand1, operand2));
        try {
            String response = reader.readLine();
            io.writeLine(response);
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }
}
