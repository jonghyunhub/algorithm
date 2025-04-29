package boj.graph;

import java.util.*;
import java.util.stream.Collectors;

public class BOJ24482 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();
        for (int i = 1; i <= m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            List<Integer> uList = graph.getOrDefault(u, new ArrayList<>());
            uList.add(v);
            graph.put(u, uList);
            List<Integer> vList = graph.getOrDefault(v, new ArrayList<>());
            vList.add(u);
            graph.put(v, vList);
        }
        for (int key : graph.keySet()) {
            List<Integer> integers = graph.get(key);
            List<Integer> sorted = integers.stream().sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
            graph.put(key, sorted);
        }
        final int[] visited = new int[n + 1];
        Arrays.fill(visited, -1);
        int cnt = 0;
        dfs(r, cnt, graph, visited);

        for (int i = 1; i <= n; i++) {
            System.out.println(visited[i]);
        }
        sc.close();
    }

    public static void dfs(int now,
                           int cnt,
                           Map<Integer, List<Integer>> graph,
                           int[] visited) {
        if (visited[now] != -1) return;
        visited[now] = cnt;
        for (int next : graph.getOrDefault(now, new ArrayList<>())) {
            if(visited[next] != -1) continue;
            dfs(next, cnt + 1, graph, visited);
        }
    }

}
