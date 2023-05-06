package boj;

import java.util.Scanner;
import java.util.Stack;

public class BOJ10799 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        String input = scanner.nextLine();
        int result = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if(c == ')'){
                //레이저인 경우 잘린 쇠막대기 갯수 세기
                if (stack.peek() == i - 1) {
                    stack.pop();
                    result += stack.size();
                } else { // 쇠막대기인경우 1개 추가
                    stack.pop();
                    result++;
                }
            }
        }

        System.out.println(result);

        scanner.close();
    }
}
