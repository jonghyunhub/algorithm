package boj.heap;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ11286 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
            int absComparison = Math.abs(a) - Math.abs(b);  // 절댓값 기준 오름차순
            if (absComparison != 0) {
                return absComparison;  // 절댓값이 다르면 절댓값으로 정렬
            } else {
                return a - b;  // 절댓값이 같으면 작은 수가 우선 (오름차순)
            }
        });
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            int next = sc.nextInt();
            if(next == 0){
                if(pq.isEmpty()) {
                    sb.append(0);
                    sb.append("\n");
                    continue;
                }
                sb.append(pq.poll());
                sb.append("\n");
                continue;
            }
            pq.add(next);
        }
        System.out.println(sb);
        sc.close();
    }
}
