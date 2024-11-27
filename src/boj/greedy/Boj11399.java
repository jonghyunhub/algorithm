package boj.greedy;

import java.util.*;

// https://www.acmicpc.net/problem/11399
public class Boj11399 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int[] withdrawTimes = new int[n];
        for (int i = 0; i < n; i++)
            withdrawTimes[i] = sc.nextInt();

        int solution = solution(n, withdrawTimes);
        System.out.println(solution);
    }

    public static int solution(int n, int[] withdrawTimes) {
        int[] sorted = Arrays.stream(withdrawTimes).sorted().toArray(); // 오름차순 정렬
        int[] withdrawTimePerPerson = new int[n];
        withdrawTimePerPerson[0] = sorted[0];
        for (int i=1; i<n; i++) {
            withdrawTimePerPerson[i] = sorted[i] + withdrawTimePerPerson[i-1];
        }
        return Arrays.stream(withdrawTimePerPerson).sum();
    }
}
