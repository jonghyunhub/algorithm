package boj.backtracking;

import java.util.Scanner;

// https://www.acmicpc.net/problem/14888

/**
 * 백트래킹 기반의 완전 탐색으로 풀어야하는 이유?
 * 1. dp가 잘 동작하는 경우 =>  같은 상태에 도달하는 여러 경로가 있고, 어떤 경로로 왔는지는 중요하지 않다
 * 예: 배낭 문제
 * - 상태: dp[i][무게] = 최대 가치
 * - 아이템 1,2를 선택하든 2,1을 선택하든 "무게 5에 도달"하면 동일
 * - → 경로 무관, 상태만 중요 → 메모이제이션 효과적
 *
 * 2. 이 문제는 순열 탐색이 필요함 => 어떤 순서로 선택했는지가 결과에 영향을 준다
 * 예: 연산자 끼워넣기
 * - 숫자: [3, 5, 2]
 * - 연산자: [+, ×] 하나씩
 * 경로 1: 3 + 5 × 2 = 8 × 2 = 16
 * 경로 2: 3 × 5 + 2 = 15 + 2 = 17
 * → 같은 연산자를 썼지만 순서에 따라 결과가 다름
 */
public class BOJ14888 {

    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] operand = new int[n];
        for (int i = 0; i < n; i++) {
            operand[i] = sc.nextInt();
        }
        // +, - , *, /
        int[] operator = new int[4];
        for (int i = 0; i < 4; i++) {
            operator[i] = sc.nextInt();
        }

        backTracking(operand, operator, operand[0], 1);


        System.out.println(max);
        System.out.println(min);
    }

    static public void backTracking(int[] operand,
                             int[] operator,
                             int value,
                             int idx) {
        if (idx >= operand.length) {
            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] <= 0) continue;
            operator[i]--;
            int nextValue = operate(value, operand[idx], i);
            backTracking(operand, operator, nextValue, idx + 1);
            operator[i]++;
        }

    }

    static public int operate(int value,
                       int nextOperand,
                       int operatorIdx) {
        if (operatorIdx == 0) {
            return value + nextOperand;
        }
        if (operatorIdx == 1) {
            return value - nextOperand;
        }
        if (operatorIdx == 2) {
            return value * nextOperand;
        }
        // 나눗셈
        if (value < 0) {
            value = -value;
            return -(value / nextOperand);
        }
        return value / nextOperand;

    }
}
