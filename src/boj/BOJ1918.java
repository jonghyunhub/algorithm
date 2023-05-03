package boj;

import java.io.*;
import java.util.Stack;

public class BOJ1918 {

    //1. 피연산자는 그대로 출력합니다.
    //2. 연산자는 스택이 비어있으면 자신을 바로 추가합니다.
    //3. stack의 top이 자신보다 우선순위가 낮은 연산자를 만날 때까지 pop 하고 자신을 담습니다.
    //4. 단, 여는 괄호는 닫는 괄호가 아니면 pop하지 않습니다.
    //4. 닫는 괄호가 나오면 여는 괄호가 나올 때까지 꺼내서 출력합니다.
    //5. 마지막에 도착하면 스택에서 차례로 꺼내서 출력합니다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String expression = br.readLine();
        Stack<Character> operationStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {

            switch (expression.charAt(i)) {
                case '(':
                    operationStack.push(expression.charAt(i));
                    break;
                case ')':
                    while (!operationStack.isEmpty() && operationStack.peek() != '(') {
                        bw.write(operationStack.pop());
                    }
                    operationStack.pop();
                    break;
                case '+':
                case '-':
                case '*':
                case '/' :
                    if (operationStack.isEmpty()) {
                        operationStack.push(expression.charAt(i));
                        break;
                    }
                    while (!operationStack.isEmpty() && precedence(operationStack.peek()) >= precedence(expression.charAt(i))) {
                        bw.write(operationStack.pop());
                    }
                    operationStack.push(expression.charAt(i));
                    break;
                default:
                    bw.write(expression.charAt(i));
            }

        }

        while (!operationStack.isEmpty()) {
            bw.write(operationStack.pop());
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static int precedence(char ch) {
        if(ch == '(') return 0;
        if(ch == '+' || ch == '-') return 1;
        return 2;
    }

}
