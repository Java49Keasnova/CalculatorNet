package telran.calculator.service;

public class CalculatorService {
    private static final String SPLIT_CONDITION = "#";
    private static short TOKENS_LENGTH = 3;
    static String line;
   public static String getResponse(String line){
       String[] tokens = line.split(SPLIT_CONDITION);
       return tokens.length == TOKENS_LENGTH ? calculate(tokens): "Wrong request format";

   }

    private static String calculate(String[] tokens){

           String operation = tokens[0];
           double num1 = Double.parseDouble(tokens[1]);
           double num2 =Double.parseDouble(tokens[2]);

           return getRes(operation, num1, num2);
   }

    private static String getRes(String operation, double num1, double num2) {

       return switch (operation){
            case "+" -> Double.toString(num1 + num2);
            case "-" -> Double.toString(num1 - num2);
            case "*" -> Double.toString(num1 * num2);
            case "/" -> Double.toString(num1 / num2);
            default -> "Wrong request";
        };

    }
}
