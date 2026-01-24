package boj.backtracking;

import java.util.Scanner;

// https://www.acmicpc.net/problem/14888
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
