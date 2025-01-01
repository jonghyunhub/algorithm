package programmers.stack;

import java.util.Stack;

public class ConverterDecimalToBinaryNumber {

    public static void main(String[] args) {
        final ConverterDecimalToBinaryNumber converterDecimalToBinaryNumber = new ConverterDecimalToBinaryNumber();
        int input = 10;
        String solution = converterDecimalToBinaryNumber.solution2(input);
        System.out.println("solution = " + solution);
    }

    public String solution(int num) {
        final Stack<String> stack = new Stack<>();
        while (num > 0) {
            stack.push(Integer.toString(num % 2));
            num /= 2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!stack.isEmpty()) {
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.toString();
    }

    public String solution2(int decimal) {
        Stack<Integer> stack = new Stack<>();
        while (decimal > 0) {
            Integer remain = decimal % 2;
            stack.add(remain);
            decimal = decimal / 2;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
