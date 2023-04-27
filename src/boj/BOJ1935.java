package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String expression = br.readLine();
        //숫자 배열
        double[] operands = new double[N];
        for (int i = 0; i < N; i++) {
            operands[i] = Double.parseDouble(br.readLine());
        }
        Stack<Double> operandStack = new Stack<>();
        int index = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) >= 'A' && expression.charAt(i) <= 'Z') {
                //피연산자에 맞는 숫자 스택에 넣어줌
                // 3번째줄에는 'A'에 해당하는 값, 4번째줄에는 'B'에 해당하는 값 .... 들어옴
                // 즉, 3번째줄 입력받은 값 => operand[0] / 4번째줄 입력받은 값 => operand[1] / ...
                // expression.charAt(i) -'A' => 'A'가 입력되면 => 0 'B'가 입력되면 =>1
                operandStack.push(operands[(int)expression.charAt(i)-'A']);
            } else if (expression.charAt(i) == '+') {
                // 먼저 들어간 것이 연산의 왼쪽으로 와야함
                Double second = operandStack.pop();
                Double first = operandStack.pop();
                operandStack.push(first + second);
            }else if(expression.charAt(i) == '-'){
                Double second = operandStack.pop();
                Double first = operandStack.pop();
                operandStack.push(first - second);
            }else if (expression.charAt(i) == '*') {
                Double second = operandStack.pop();
                Double first = operandStack.pop();
                operandStack.push(first * second);
            } else if (expression.charAt(i) == '/') {
                Double second = operandStack.pop();
                Double first = operandStack.pop();
                operandStack.push(first / second);
            }
        }
        System.out.printf("%.2f", operandStack.pop());
        br.close();
    }
}
