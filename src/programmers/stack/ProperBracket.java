package programmers.stack;

import java.util.Stack;

public class ProperBracket {

    boolean solution(String s) {
        boolean answer = true;

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
                continue;
            }
            if(c == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
                continue;
            }
            answer = false;
            return answer;
        }

        if(!stack.isEmpty())
            answer = false;

        return answer;
    }
}
