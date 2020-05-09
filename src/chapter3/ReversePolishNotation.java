package chapter3;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.Stack;

public class ReversePolishNotation {
    private static Stack<String> operators = new Stack<>();
    private static Stack<String> numbers = new Stack<>();

    public static void generatePolishNotation(String string) {
        if (string == null) throw new IllegalArgumentException();
        for (int i = 0; i < string.length(); i++) {
            String character = String.valueOf(string.charAt(i));
            if (character.equals("+")) {
                process(character);
            } else if (character.equals("-")) {
                process(character);
            } else if (character.equals("*")) {
                process(character);
            } else if (character.equals("/")) {
                process(character);
            } else if (character.equals("(")) {
                operators.push("(");
            } else if (character.equals(")")) {
                while (!operators.isEmpty()) {
                    String operator = operators.pop();
                    if (operator.equals("(")) break;
                    numbers.push(operator);
                }
            } else if (character.matches("\\d+")){
                numbers.push(character);
            }
        }
        Stack<String> result = new Stack<>();
        // 处理完成之后，将operator全部操作符压入numbers中
        for(String operator:operators) {
            numbers.push(operator);
        }
        // 倒序输出至另一个Stack中
        for (String item:numbers) {
            result.push(item);
        }
        System.out.println("post fix expression = " + result);
    }

    private static void process(String character) {
        if (operators.isEmpty()) {
            operators.push(character);
        } else {
            if (prior(character, operators.peek())) {
                operators.push(character);
            } else {
                numbers.push(operators.pop());
                operators.push(character);
            }
        }
    }

    private static boolean prior(String operator, String comparedOperator) {
        if (operator != null && comparedOperator.matches("\\(")) return true;
        if (operator.matches("(\\*)|(\\/)") && comparedOperator.matches("(\\+)|(\\-)")) return true;
        return false;
    }

    public static void main(String[] args) {
        ReversePolishNotation.generatePolishNotation("5 + 60 * ( 3 - 1 ) / ( 6 - 4 ) + 3");
        ReversePolishNotation.generatePolishNotation("2 * ( 5 - 1 )");
    }
}
