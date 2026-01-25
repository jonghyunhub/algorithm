package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.acmicpc.net/problem/1167
public class BOJ1167 {
    static int answer = 0;
    static int lastNode = 0;

    /**
     * 아이디어 : dfs 한번으로 끝점 탐색후, 그 끝점에서 dfs 한번 더 탐색해서 나온 최대 거리가 트리의 지름
     * why?
     * 1. 임의의 정점에서 가장 먼 정점 A를 찾는다
     * 2. A에서 가장 먼 정점 B를 찾는다
     * 3. A-B 거리가 트리의 지름이다
     * => 이 성질이 성립하는 이유는, 트리에서 임의의 점에서 가장 먼 점은 반드시 지름의 한 끝점이기 때문
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        Map<Integer, List<Node>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int from = Integer.parseInt(split[0]);
            for (int j = 1; j < split.length - 1; j += 2) {
                int to = Integer.parseInt(split[j]);
                int dist = Integer.parseInt(split[j + 1]);
                List<Node> nodes = graph.computeIfAbsent(from, k -> new ArrayList<>());
                nodes.add(new Node(to, dist));
            }
        }
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        dfs(graph, 1, 0, visited);

        visited = new boolean[n + 1];
        visited[lastNode] = true;
        dfs(graph, lastNode, 0, visited);
        System.out.println(answer);
    }

    public static void dfs(Map<Integer, List<Node>> graph,
                          int now,
                          int dists,
                          boolean[] visited) {
        List<Node> nodes = graph.getOrDefault(now, new ArrayList<>());
        for (Node node : nodes) {
            if(visited[node.num]) continue;
            visited[node.num] = true;
            int nextDist = dists + node.dist;
            if (nextDist > answer) {  // 최대일 때만 갱신
                answer = nextDist;
                lastNode = node.num;
            }
            dfs(graph, node.num, nextDist ,visited);
            visited[node.num] = false;
        }
    }

    static class Node {
        int num;
        int dist;

        public Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }
}
