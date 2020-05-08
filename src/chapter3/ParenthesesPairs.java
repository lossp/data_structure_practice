package chapter3;

import java.util.Stack;

/**
 * 用下列语言编写检测平衡符号的程序:
 * b. Java(/ * * /, (), [], {})
 *
 * 由于题干中没有明确指明如何检测平衡，因此本算法就仅仅打印成对的符号
 */
public class ParenthesesPairs {
    private Stack<String> stack = new Stack<>();
    private Stack<String> result = new Stack<>();

    public void checkPairs(String string) {
        if (string == null) throw new IllegalArgumentException();
        for (int i = 0; i < string.length(); i++) {
            String character = Character.toString(string.charAt(i));
            if (character.equals("(")) {
                stack.push("(");
            } else if (character.equals("[")) {
                stack.push("[");
            } else if (character.equals("{")) {
                stack.push("{");
            } else if (character.equals(")")) {
                String poppedCharacter = stack.pop();
                if (poppedCharacter.equals("(")) {
                    result.push(")");
                    result.push("(");
                }
            } else if (character.equals("]")) {
                String poppedCharacter = stack.pop();
                if (poppedCharacter.equals("[")) {
                    result.push("]");
                    result.push("[");
                }

            } else if (character.equals("}")) {
                String poppedCharacter = stack.pop();
                if (poppedCharacter.equals("}")) {
                    result.push("}");
                    result.push("{");
                }

            }
        }
    }
    public void printResult() {
        StringBuilder res = new StringBuilder();
        while (!result.isEmpty()) {
            res.append(result.pop());
        }
        String resultString = res.toString();
        System.out.println("结果为: " + resultString);
    }

    public static void main(String[] args) {
        ParenthesesPairs parenthesesPairs = new ParenthesesPairs();
        parenthesesPairs.checkPairs("{(()]{(([[))}");
        parenthesesPairs.printResult();
    }
}