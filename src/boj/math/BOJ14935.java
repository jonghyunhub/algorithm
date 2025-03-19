package boj.math;

import java.util.*;

/**
 * 매우 큰 수를 입력받으므로 문자열로 받아서 처리
 */
public class BOJ14935 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        for (int i = 0; i < 100; i++) {
            String next = makeFa(input);
            if(input.equals(next)) {
                System.out.println("FA");
                return;
            }
            input = next;
        }
        System.out.println("NFA");
    }

    public static String makeFa(String input) {
        int firstNum = input.charAt(0) - '0';
        long length = input.length();
        return String.valueOf(firstNum * length);
    }
}
