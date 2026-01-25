package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/11725
public class BOJ11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] parent = new int[n+1];
        boolean visited[] = new boolean[n+1];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            int node1 = Integer.parseInt(split[0]);
            int node2 = Integer.parseInt(split[1]);
            List<Integer> node1List = graph.computeIfAbsent(node1, k -> new ArrayList<>());
            node1List.add(node2);
            List<Integer> node2List = graph.computeIfAbsent(node2, k -> new ArrayList<>());
            node2List.add(node1);
        }

//        dfsWithStack(graph, parent, visited);

        visited[1] = true;
        dfsWithRecursive(graph, 1, parent, visited);

        for (int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }
    }

    public static void dfsWithStack(Map<Integer, List<Integer>> graph, int[] parent, boolean[] visited) {
        int root = 1;
        Stack<Integer> stack = new Stack<>();
        stack.add(root);
        visited[root] = true;

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            visited[pop] = true;
            List<Integer> children = graph.getOrDefault(pop, new ArrayList<>());
            for (int child : children) {
                if(visited[child]) continue;
                parent[child] = pop;
                stack.add(child);
            }
            visited[pop] = true;
        }
    }

    public static void dfsWithRecursive(Map<Integer, List<Integer>> graph,
                                        int now,
                                        int[] parent,
                                        boolean[] visited) {
        List<Integer> children = graph.getOrDefault(now, new ArrayList<>());
        for (int child : children) {
            if(visited[child]) continue;
            parent[child] = now;
            visited[child] = true;
            dfsWithRecursive(graph, child, parent, visited);
            visited[child] = false;
        }
    }
}
