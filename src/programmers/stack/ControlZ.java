package programmers.stack;

import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/120853?language=java
public class ControlZ {

    /**
     * 1. 문자열을 파싱한다.
     * 2. 스택에 파싱한 배열을 순회하면서 넣는다.
     * 3. if(넣는 문자 == "Z") -> stack.pop();
     * <p>
     * [시간 복잡도]
     * 문자열 s를 파싱한 inputs의 길이 : n 일때
     * O(n)
     */
    public int solution(String s) {
        String[] inputs = s.split(" ");
        final Stack<Integer> stack = new Stack<>();
        for (String input : inputs) {
            if (input.equals("Z")) {
                stack.pop();
                continue;
            }
            stack.push(Integer.parseInt(input));
        }

        return stack.stream().mapToInt(Integer::intValue).sum();
    }
}
