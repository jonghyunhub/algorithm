package boj.graph;

import java.util.*;

// https://www.acmicpc.net/problem/1260
public class BOJ1260 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int M = sc.nextInt();
        final int V = sc.nextInt();
        final Map<Integer, List<Integer>> graph = new HashMap<>(N);
        final List<Integer> dfsList = new ArrayList<>();
        final List<Integer> bfsList = new ArrayList<>();

        // 그래프 초기화
        for (int i = 0; i < M; i++) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            List<Integer> v1OrDefault = graph.getOrDefault(v1, new ArrayList<>());
            v1OrDefault.add(v2);
            graph.put(v1, v1OrDefault);

            List<Integer> v2OrDefault = graph.getOrDefault(v2, new ArrayList<>());
            v2OrDefault.add(v1);
            graph.put(v2, v2OrDefault);
        }

        dfsWithRecursive(graph, V, dfsList);
        for (Integer node : dfsList)
            System.out.print(node + " ");
        System.out.println();


        bfs(graph, V, bfsList);
        for (Integer node : bfsList)
            System.out.print(node + " ");

        sc.close();
    }

    // 스택을 통해 dfs 구현
    public static void dfs(Map<Integer, List<Integer>> graph, int start, List<Integer> visited) {
        Stack<Integer> stack = new Stack<>();
        stack.add(start);
        while (!stack.isEmpty()) {
            final Integer now = stack.pop();
            if (visited.contains(now)) continue;
            visited.add(now);
            List<Integer> orDefault = graph.getOrDefault(now, new ArrayList<>());
            orDefault.sort(Comparator.reverseOrder()); // 인접 간선 내림차순 정렬
            for (int nextNode : orDefault) {
                // 이미 방문한 노드는 스킵
                if (visited.contains(nextNode)) continue;
                stack.push(nextNode);
            }
        }
    }

    // 재귀를 통해 dsf 구현
    public static void dfsWithRecursive(Map<Integer, List<Integer>> graph, int now, List<Integer> visited) {
        if (visited.contains(now)) return;
        visited.add(now);
        List<Integer> orDefault = graph.getOrDefault(now, new ArrayList<>());
        orDefault.sort(Comparator.naturalOrder()); // 인접 간선 오름차순 정렬
        for (int nextNode : orDefault) {
            dfsWithRecursive(graph, nextNode, visited);
        }
    }

    public static void bfs(Map<Integer, List<Integer>> graph, int start, List<Integer> visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            final Integer now = queue.poll();
            List<Integer> orDefault = graph.getOrDefault(now, new ArrayList<>());
            orDefault.sort(Comparator.naturalOrder()); // 인접 간선 오름차순 정렬
            for (int nextNode : orDefault) {
                // 이미 방문한 노드는 스킵
                if (visited.contains(nextNode)) continue;
                visited.add(nextNode);
                queue.add(nextNode);
            }
        }
    }
    
}
