package programmers.stack;

import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/76502
public class RotateBracket {

    public static void main(String[] args) {
        RotateBracket rotateBracket = new RotateBracket();
        String input = "}}}";
        System.out.println(rotateBracket.solution(input));
    }

    /**
     * 1. 올바른 문자열인지 검증하는 함수를 만든다.
     * 2. 주어진 문자열을 x번 (0 <= x < s.length()) 회전시킨다.
     * 3. 1에서 구현한 함수에 각 회전시킨 문자열을 넣어서 올바른 문자열인지 검증한다.
     * 4. 3의 결과 중 올바른 문자열의 갯수를 리턴한다.
     *
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

    private static StringBuilder getRotateString(String s, int x) {
        StringBuilder stringBuilder = new StringBuilder();
        String startSubString = s.substring(x); // x ~ s.length()-1
        String endSubString = s.substring(0, x);// 0 ~ x-1
        stringBuilder.append(startSubString).append(endSubString);
        return stringBuilder;
    }


    private boolean isCorrectBracket(String s) {
        Stack<Character> stack = new Stack<>();
        for(char bracket : s.toCharArray()){
            if(bracket == '(' || bracket == '{' || bracket == '[') {
                stack.push(bracket);
                continue;
            }
            if(bracket == ')'){
                if(!stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                } else {
                    return false;
                }
            }
            if(bracket == '}'){
                if(!stack.isEmpty() && stack.peek() == '{'){
                    stack.pop();
                } else {
                    return false;
                }
            }
            if(bracket == ']'){
                if(!stack.isEmpty() && stack.peek() == '['){
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }


}
