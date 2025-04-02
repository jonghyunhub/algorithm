package boj.graph;

import java.util.*;
import java.util.stream.Collectors;

public class BOJ24479 {
    public static void main(String[] args) {
        int N, M, R;
        final Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        R = sc.nextInt();
        final List<List<Integer>> graph = new ArrayList<>(N+1);
        final int[] visited = new int[N+1];
        Arrays.fill(visited, 0);
        final Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N+1; i++) {
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
                    .stream().
                    sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
            graph.set(i, sorted);
        }
        stack.push(R);
        int count = 1;
        while (!stack.isEmpty()) {
            final int pop = stack.pop();
            if(visited[pop] != 0) continue; // 방문한 노드 스킵
            visited[pop] = count++; // 방문 처리
            final List<Integer> list = graph.get(pop);
            for (Integer item : list) {
                if(visited[item] != 0) continue; // 방문한 노드 스킵
                stack.push(item);
            }
        }
        for(int i=1; i<=N; i++) {
            System.out.println(visited[i]);
        }
        sc.close();
    }
}
