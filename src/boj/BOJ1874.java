package boj;

import java.io.*;
import java.util.Stack;

public class BOJ1874 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(bf.readLine());
        int nowStack = 0; //  nowStack은 현재 stack에 넣어야 하는 숫자
        String answer = "";
        Stack<Integer> stack = new Stack<>();
        while (n-- > 0) {
            //nextValue는 입력받아 출력해야 하는 값
            int nextValue = Integer.parseInt(bf.readLine());
            if (nextValue > nowStack) {
                //스택에 넣어야하는 숫자보다 출력해야 하는 숫자가 크므로 같아질때까지 계속 넣어줌
                while (nextValue > nowStack) {
                    stack.push(++nowStack);
                    answer += "+";
                }
                stack.pop();
                answer += "-";
            } else {
                //스택에 넣어야하는 숫자(nowStack) 보다 출력해야하는 숫자(nextValue)가 작은경우
                // => 스택이 비어있거나
                // => 스택에 값이 있는 경우에는 스택의 맨 위에 있는 숫자가 출력해야하는 숫자(nextValue)랑 같은경우만 성립함
                if (!stack.empty()) {
                    if (nextValue == stack.pop()) {
                        answer += "-";
                    } else {
                        System.out.println("NO");
                        return;
                    }
                }
            }
        }
        for (int i = 0; i < answer.length(); i++) {
            System.out.println(answer.charAt(i));
        }
    }
}
