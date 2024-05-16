package programmers.stack;

import java.util.Stack;

public class ConverterDecimalToBinaryNumber {

    public static void main(String[] args) {
        final ConverterDecimalToBinaryNumber converterDecimalToBinaryNumber = new ConverterDecimalToBinaryNumber();
        int input = 12345;
        String solution = converterDecimalToBinaryNumber.solution(input);
        System.out.println("solution = " + solution);
    }

    public String solution(int num){
        final Stack<String> stack = new Stack<>();
        while(num >0){
            stack.push(Integer.toString(num % 2));
            num /= 2;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while(!stack.isEmpty()){
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.toString();
    }
}
