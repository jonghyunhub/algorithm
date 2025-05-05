package boj.dp;

import java.util.Scanner;

public class BOJ12015 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // tails[i]는 길이가 i+1인 LIS의 마지막 값
        int[] tails = new int[n];
        int len = 0;

        for (int x : arr) {
            // 이진 탐색으로 x가 들어갈 위치를 찾음
            int lo = 0, hi = len;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tails[mid] < x) {
                    lo = mid + 1;
                    continue;
                }
                hi = mid;
            }

            tails[lo] = x;
            if (lo == len) len++;
        }

        System.out.println(len);
        sc.close();
    }
}
