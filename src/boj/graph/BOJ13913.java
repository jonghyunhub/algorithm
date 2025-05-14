package boj.graph;

import java.io.*;
import java.util.*;

public class BOJ13913 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int k = Integer.parseInt(split[1]);

        int[] visited = new int[100_001];
        Arrays.fill(visited, -1);
        final Queue<Integer> queue = new ArrayDeque<>();
        queue.add(n);
        visited[n] = n;
        Integer now = n;
        while (!queue.isEmpty()) {
            now = queue.poll();
            if(now == k) break;

            if(now -1 >= 0){
                int next = now - 1;
                if(visited[next] == -1){
                    visited[next] = now;
                    queue.add(next);
                }
            }

            if(now + 1 < visited.length){
                int next = now + 1;
                if(visited[next] == -1){
                    visited[next] = now;
                    queue.add(next);
                }

            }

            if(now * 2 < visited.length){
                int next = now * 2;
                if(visited[next] == -1){
                    visited[next] = now;
                    queue.add(next);
                }
            }
        }

        Stack<Integer> stack = new Stack<>();
        while (now != n){
            int next = visited[now];
            stack.push(next);
            now = next;
        }
        System.out.println(stack.size());
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
            sb.append(" ");
        }
        sb.append(k);
        System.out.println(sb);
    }
}
