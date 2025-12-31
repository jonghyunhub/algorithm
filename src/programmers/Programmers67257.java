package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//https://school.programmers.co.kr/learn/courses/30/lessons/67257 - 수식 최대화
public class Programmers67257 {
    public static void main(String[] args) {
        Programmers67257 programmers67257 = new Programmers67257();
        String expr1 = "100-200*300-500+20";
        String expr2 = "50*6-3*2";
        long solution = programmers67257.solution(expr2);
        System.out.println(solution);
    }

    public long solution(String expression) {
        long answer = 0;
        Set<String> operators = new HashSet<>();
        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '-' || c == '*') {
                operators.add(String.valueOf(c));
            }
        }
        List<List<String>> combinations = new ArrayList<>();
        boolean[] visited = new boolean[operators.size()];
        makeCombinations(combinations, new ArrayList<>(operators), new ArrayList<>(), visited);
        List<String> exprTokens = makeExprTokens(expression);
        for (List<String> comb : combinations) {
            List<String> exprTokensCopy = new ArrayList<>(exprTokens);
            calculate(exprTokensCopy, comb);
            long cal = Long.parseLong(exprTokensCopy.get(0));
            answer = Math.max(answer, Math.abs(cal));
        }

        return answer;
    }

    private static List<String> makeExprTokens(String expression) {
        List<String> exprTokens = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                exprTokens.add(String.valueOf(c));
                continue;
            }
            int j = i + 1;
            for (; j < expression.length(); j++) {
                char cj = expression.charAt(j);
                if (cj == '+' || cj == '-' || cj == '*') {
                    String substring = expression.substring(i, j);
                    exprTokens.add(substring);
                    break;
                }
                if (j == expression.length() - 1) {
                    String substring = expression.substring(i, j + 1);
                    exprTokens.add(substring);
                }
            }
            // 마지막 토큰 예외처리
            if(i == expression.length() - 1 && Set.of('+', '-', '*').contains(expression.charAt(i - 1))) {
                exprTokens.add(expression.substring(i));
            }
            i = j - 1;
        }
        return exprTokens;
    }

    public void makeCombinations(
            List<List<String>> results,
            List<String> origin,
            List<String> tmp,
            boolean[] visited
    ) {
        if (tmp.size() >= origin.size()) {
            results.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < origin.size(); i++) {
            if (visited[i]) continue;
            tmp.add(origin.get(i));
            visited[i] = true;
            makeCombinations(results, origin, tmp, visited);
            visited[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * 1. 연산자 우선순위에 맞는 연산자 찾는다.(제일 우선순위 높은 연산자, 제일 왼쪽에 있는 연산자)
     * 2. 1에서 찾은 연산자를 먼저 계산 => 100-200*300-500+20 에서 * > + > -  이면 => 100-(200*300)-500+20 이거먼저
     * 3. 1,2 를 계속 반복하면서 계산
     */
    public void calculate(
            List<String> exprTokens,
            List<String> operators) {
        // 1. 연산자 우선순위가 제일 높은 연산자 찾기
        int operatorIdx = findOperatorIdx(exprTokens, operators);

        // 찾은 연산자 우선순위가 없으면
        if (operatorIdx == -1) {
            return;
        }

        // 2. 찾은 연산자를 기준으로 계산
        long left = Long.parseLong(exprTokens.get(operatorIdx - 1));
        long right = Long.parseLong(exprTokens.get(Math.min(operatorIdx + 1, exprTokens.size()-1)));
        long cal = 0L;
        String now = exprTokens.get(operatorIdx);
        if (now.equals("+")) {
            cal = left + right;
        }

        if (now.equals("-")) {
            cal = left - right;
        }

        if (now.equals("*")) {
            cal = left * right;
        }
        exprTokens.remove(operatorIdx + 1);
        exprTokens.remove(operatorIdx);
        exprTokens.remove(operatorIdx - 1);
        exprTokens.add(operatorIdx - 1, String.valueOf(cal));

        calculate(exprTokens, operators);
    }

    private static int findOperatorIdx(List<String> exprTokens, List<String> operators) {
        int operatorIdx = -1;
        for (String operator : operators) {
            for (int i = 0; i < exprTokens.size(); i++) {
                String token = exprTokens.get(i);
                if (token.equals(operator)) {
                    operatorIdx = i;
                    return operatorIdx;
                }
            }
        }
        return operatorIdx;
    }
}
