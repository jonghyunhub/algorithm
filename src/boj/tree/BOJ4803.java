package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.acmicpc.net/problem/4803
public class BOJ4803 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int caseCount = 1;
        while (true) {
            int treeCount = 0;
            String readLine = bufferedReader.readLine();
            if ("0 0".equals(readLine)) break;
            String[] split = readLine.split(" ");
            int n = Integer.parseInt(split[0]); // 전체 정점 갯수
            int m = Integer.parseInt(split[1]); // 전체 간선 갯수
            Map<Integer, List<Integer>> graph = new HashMap<>();
            for (int i = 0; i < m; i++) {
                split = bufferedReader.readLine().split(" ");
                int v1 = Integer.parseInt(split[0]);
                int v2 = Integer.parseInt(split[1]);
                graph.computeIfAbsent(v1, k -> new ArrayList<>()).add(v2);
                graph.computeIfAbsent(v2, k -> new ArrayList<>()).add(v1);
            }
            /**
             * [트리 갯수 세기]
             * 각 연결 요소마다 DFS/BFS로 탐색하면서 사이클 여부 확인
             * 사이클 판별 시 "직전에 온 정점"은 제외하고, 그 외에 이미 방문한 정점을 만나면 사이클
             */
            boolean[] visited = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (visited[i]) continue;
                if (dfs(graph, visited, i, -1)) treeCount++;
            }
            System.out.print("Case " + caseCount + ": ");
            if (treeCount == 0) {
                System.out.println("No trees.");
                caseCount++;
                continue;
            }
            if (treeCount == 1) {
                System.out.println("There is one tree.");
                caseCount++;
                continue;
            }
            System.out.println("A forest of " + treeCount + " trees.");
            caseCount++;
        }
    }

    public static boolean dfs(Map<Integer, List<Integer>> graph,
                              boolean[] visited,
                              int now,
                              int parent) {
        for (int next : graph.getOrDefault(now, new ArrayList<>())) {
            if(next == parent) continue; // 직전에 온 곳은 무시

            if (visited[next]) {
                // 이전에 지나온 노드가 아닌데 이미 방문한 정점을 다시 지날수 있으면 => 사이클 존재
                return false;
            }

            visited[next] = true;
            if (!dfs(graph, visited, next, now)) {
                return false; // 하위 탐색에서 사이클 발견되면 위로 전파
            }
        }

        return true;  // 사이클 없음
    }
}
