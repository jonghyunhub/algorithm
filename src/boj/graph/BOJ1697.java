package boj.graph;

import java.io.*;
import java.util.*;

public class BOJ1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);
        final Queue<Integer> queue = new ArrayDeque<>();
        final int[] visited = new int[100_001];
        Arrays.fill(visited, -1);
        queue.add(n);
        visited[n] = 0;
        while (!queue.isEmpty()) {
            Integer now = queue.poll();
            if (now == k) {
                System.out.println(visited[now]);
                return;
            }

            if (now > 0) {
                int next = now - 1;
                if(visited[next] == -1){
                    visited[next] = visited[now]+1;
                    queue.add(next);
                }
            }

            if(2 * now <= visited.length-1){
                int next = 2 * now;
                if(visited[next] == -1){
                    visited[next] = visited[now]+1;
                    queue.add(next);
                }
            }

            if(now + 1 <= visited.length-1){
                int next = now + 1;
                if(visited[next] == -1){
                    visited[next] = visited[now]+1;
                    queue.add(next);
                }
            }
        }
    }
}
