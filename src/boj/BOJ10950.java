package boj;

import java.util.Scanner;

public class BOJ10950 {
    public static void main(String[] args) {
        int A, B, T;
        Scanner scanner = new Scanner(System.in);
        T = scanner.nextInt();
        while (T > 0) {
            A = scanner.nextInt();
            B = scanner.nextInt();
            System.out.println(A + B);
            T--;
        }
    }
}
