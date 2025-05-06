package boj.heap;

import java.util.*;

public class BOJ11279 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder()); // 내림차순(MAX_HEAP)

        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int next = sc.nextInt();
            if(next == 0){
                if(pq.isEmpty()){
                    System.out.println("0");
                    continue;
                }
                Integer poll = pq.poll();
                System.out.println(poll);
                continue;
            }
            pq.add(next);
        }

        sc.close();
    }
}
