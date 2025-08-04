package boj.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ2075 {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> b-a);
        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().split(" ");
            for (String s : split) {
                queue.offer(Integer.parseInt(s));
            }
        }

        for(int i=0; i<n; i++)
            answer = queue.poll();

        System.out.println(answer);

        br.close();
    }
}
