package boj.graph;

import java.io.*;
import java.util.*;

public class BOJ11724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        List<List<Integer>> graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        for(int i = 1; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) {
            String[] nextLine = br.readLine().split(" ");
            int u = Integer.parseInt(nextLine[0]);
            int v = Integer.parseInt(nextLine[1]);
            graph.get(u).add(v);
            graph.get(v).add(u); // 방향 없는 그래프 처리
        }

        boolean[] visited = new boolean[n+1];
        int cnt = 0;
        for (int i = 1; i < visited.length; i++) {
            int start = i;
            if(visited[i]) continue;
            dfs(start, graph, visited);
            cnt++;
        }

        System.out.println(cnt);
    }

    private static void bfs(int start, List<List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Integer now = queue.poll();

            for(int next : graph.get(now)) {
                if(visited[next]) continue;
                queue.add(next);
                visited[next] = true;
            }
        }
    }

    private static void dfs(int now, List<List<Integer>> graph, boolean[] visited) {
        if(visited[now]) return;
        visited[now] = true;
        for(int next : graph.get(now)) {
            if(visited[next]) continue;
            dfs(next, graph, visited);
        }
    }
}
