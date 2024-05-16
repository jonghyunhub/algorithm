package programmers.stack;

import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/12973
public class PairOff {
    // abccba -> abba -> aa
    public int solution(String s) {
        String input = s;
        while (!input.isEmpty()) {
            String nextString = proceedPairOff(s);
            if (input.equals(nextString)) // 짝지어 제거하기 했으나 변화가 없으면 실패했으므로 0 리턴
                return 0;
            input = nextString;
        }

        return 1;
    }

    private String proceedPairOff(String input) {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().equals(c)) {
                stack.pop();
                continue;
            }
            stack.push(c);
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
