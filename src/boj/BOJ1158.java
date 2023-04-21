package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ1158 {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList();
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        for (int i = 1; i <= N; i++) {
            queue.add(i);
        }
        System.out.print("<");
        while (!queue.isEmpty()) {
            for (int i = 0; i < K-1; i++) {
                Integer poll = queue.poll();
                queue.add(poll);
            }
            Integer poll = queue.poll();
            System.out.print(poll);
            if (!queue.isEmpty()) {
                System.out.print(", ");
            }
        }
        System.out.println(">");
        scanner.close();
    }
}
