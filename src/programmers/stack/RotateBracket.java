package programmers.stack;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/76502
public class RotateBracket {

    public static void main(String[] args) {
        RotateBracket rotateBracket = new RotateBracket();
        String input = "[](){}";
        System.out.println(rotateBracket.solution2(input));
    }

    private static StringBuilder getRotateString(String s, int x) {
        StringBuilder stringBuilder = new StringBuilder();
        String startSubString = s.substring(x); // x ~ s.length()-1
        String endSubString = s.substring(0, x);// 0 ~ x-1
        stringBuilder.append(startSubString).append(endSubString);
        return stringBuilder;
    }

    private static boolean isCorrectString(Queue<Character> queue, Stack<Character> stack, Map<Character, Character> map) {
        boolean isCorrect = true;
        for (char c : queue.toArray(new Character[0])) {
            // 여는 괄호는 스택에 넣기
            if (c == '[' || c == '(' || c == '{') {
                stack.add(c);
                continue;
            }

            // 닫는 괄호인데 스택이 비어있으면 잘못된 문자열 ex) {{{
            if (stack.isEmpty()) {
                isCorrect = false;
                break;
            }

            // 쌍이 맞다면 스택에서 꺼내기
            // 닫는 괄호는 쌍이 맞는지 확인
            if (stack.peek() == map.get(c)) {
                stack.pop();
                continue;
            }

            // 나머지 경우는 실패
            isCorrect = false;
            break;
        }
        // 순회를 완료했는데 스택이 비어있지 않으면 잘못된 문자열 ex) }}}
        if (!stack.isEmpty()) {
            isCorrect = false;
            while (!stack.isEmpty()) stack.pop();
        }
        return isCorrect;
    }

    /**
     * 1. 올바른 문자열인지 검증하는 함수를 만든다.
     * 2. 주어진 문자열을 x번 (0 <= x < s.length()) 회전시킨다.
     * 3. 1에서 구현한 함수에 각 회전시킨 문자열을 넣어서 올바른 문자열인지 검증한다.
     * 4. 3의 결과 중 올바른 문자열의 갯수를 리턴한다.
     * <p>
     * 예시) [](){} => answer = 3
     * x = 1 : [](){} => ](){}[ NO
     * x = 2 : ](){}[ => (){}[] OK
     * x = 3 : (){}[] => ){}[( NO
     * x = 4 : ){}[( => {}[]() OK
     * x = 5 : {}[]() => }[](){ NO
     * x = 6 : }[](){ => [](){}) OK
     */
    public int solution(String s) {
        int answer = 0;
        for (int x = 1; x <= s.length(); x++) {
            StringBuilder stringBuilder = getRotateString(s, x);
            if (isCorrectBracket(stringBuilder.toString())) {
                answer++;
            }
        }
        return answer;
    }

    private boolean isCorrectBracket(String s) {
        Stack<Character> stack = new Stack<>();
        for (char bracket : s.toCharArray()) {
            if (bracket == '(' || bracket == '{' || bracket == '[') {
                stack.push(bracket);
                continue;
            }
            if (bracket == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
            if (bracket == '}') {
                if (!stack.isEmpty() && stack.peek() == '{') {
                    stack.pop();
                } else {
                    return false;
                }
            }
            if (bracket == ']') {
                if (!stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public int solution2(String s) {
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new ArrayDeque<>();
        Map<Character, Character> map = Map.of(
                ')', '(',
                ']', '[',
                '}', '{'
        );
        int answer = 0;

        // 회전을 위해 큐에 모든 원소 적재
        for (char c : s.toCharArray())
            queue.add(c);

        // 회전하면서 올바른 문자열인지 검증
        for (int i = 0; i < s.length(); i++) {
            boolean isCorrect = isCorrectString(queue, stack, map);
            if (isCorrect) answer++;
            queue.add(queue.poll());
        }

        return answer;
    }


}
