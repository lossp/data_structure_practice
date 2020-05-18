package chapter3;

import java.util.Stack;

public class isStringDrome {
    public boolean isDrome(int number) {
        String numberString = String.valueOf(number);
        Stack stack = new Stack();
        for (int i = 0; i < numberString.length(); i++) {
            stack.push(numberString.charAt(i));
        }
        StringBuilder expectedString = new StringBuilder();
        while (!stack.isEmpty()) {
            expectedString.append(stack.pop());
        }
        System.out.println("expected string " + expectedString.toString());
        System.out.println("number string " + numberString);
        return expectedString.toString().equals(numberString);
    }

    public static void main(String[] args) {
        isStringDrome testOne =  new isStringDrome();
        System.out.println(testOne.isDrome(123321));
    }
}
