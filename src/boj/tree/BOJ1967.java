package boj.tree;

import java.io.*;
import java.util.*;

// https://www.acmicpc.net/problem/1967
public class BOJ1967 {
    static int answer = 0;
    static int lastNodeNum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        Map<Integer, List<Node>> graph = new HashMap<>();

        for (int i = 0; i < n-1; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int v1 = Integer.parseInt(split[0]);
            int v2 = Integer.parseInt(split[1]);
            int dist = Integer.parseInt(split[2]);

            List<Node> v1Nodes = graph.computeIfAbsent(v1, k -> new ArrayList<>());
            v1Nodes.add(new Node(v2, dist));

            List<Node> v2Nodes = graph.computeIfAbsent(v2, k -> new ArrayList<>());
            v2Nodes.add(new Node(v1, dist));
        }

        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        dfs(1, 0, graph, visited);
        visited[1] = false;

        visited[lastNodeNum] = true;
        dfs(lastNodeNum, 0, graph, visited);

        System.out.println(answer);
    }

    public static void dfs(int now,
                           int dist,
                           Map<Integer, List<Node>> graph,
                           boolean[] visited) {
        List<Node> nodes = graph.getOrDefault(now, new ArrayList<>());
        for (Node next : nodes) {
            if(visited[next.num]) continue;
            visited[next.num] = true;
            int nextDist = dist + next.dist;
            if (nextDist > answer) {
                answer = nextDist;
                lastNodeNum = next.num;
            }
            dfs(next.num, nextDist, graph, visited);
            visited[next.num] = false;
        }
    }

    public static class Node{
        int num;
        int dist;

        public Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }
}
