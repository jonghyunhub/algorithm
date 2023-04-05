package boj;

import java.util.Scanner;
public class BOJ10951 {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int a,b;
            a = scanner.nextInt();
            b = scanner.nextInt();
            System.out.println(a+b);
        }
    }
}
