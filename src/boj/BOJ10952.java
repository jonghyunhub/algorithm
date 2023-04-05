package boj;

import java.util.Scanner;

public class BOJ10952 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int a,b;
            a = scanner.nextInt();
            b = scanner.nextInt();
            if(a == 0 && b == 0) return;
            System.out.println(a + b);
        }
    }
}
