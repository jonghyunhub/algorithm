package boj.graph;

import java.util.*;
import java.util.stream.Collectors;

public class BOJ24481 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int R = sc.nextInt();
        final List<List<Integer>> graph = new ArrayList<>(N + 1);
        final boolean[] visited = new boolean[N + 1];
        Arrays.fill(visited, false);
        final int[] depths = new int[N + 1];
        Arrays.fill(depths, -1);
        final Stack<Node> stack = new Stack<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            final int u = sc.nextInt();
            final int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u); // 무방향 그래프
        }
        for (int i = 1; i <= N; i++) {
            final List<Integer> sorted = graph.get(i)
                    .stream()
                    .sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
            graph.set(i, sorted);
        }
        // 노드의 방문순서가 아니고, 트리 기준 depth를 출력해야함
        stack.push(new Node(R, 0));
        depths[R] = 0;
        while (!stack.isEmpty()) {
            final Node pop = stack.pop();
            if (visited[pop.idx]) continue; // 방문 노드 스킵
            visited[pop.idx] = true; // 방문처리
            List<Integer> list = graph.get(pop.idx);
            for (int item : list) {
                if (visited[item]) continue;
                int nextDepth = pop.depth + 1;
                stack.push(new Node(item, nextDepth));
                depths[item] = nextDepth;
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(depths[i]);
        }
        sc.close();
    }

    static class Node {
        int idx;
        int depth;

        public Node(int idx, int depth) {
            this.idx = idx;
            this.depth = depth;
        }
    }
}
