package boj.graph;

import java.util.*;
import java.util.stream.Collectors;

public class BOJ24480 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int R = sc.nextInt();
        final List<List<Integer>> graph = new ArrayList<>(N+1);
        final Stack<Integer> stack = new Stack<>();
        final int[] visited = new int[N+1];
        Arrays.fill(visited, 0);
        // 그래프 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 1; i <= M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u); // 무방향 그래프
        }
        // 간선들 내림차순 정렬
        for(int i=1; i<=N; i++) {
            final List<Integer> sorted = graph.get(i)
                    .stream()
                    .sorted()
                    .collect(Collectors.toList());
            graph.set(i, sorted);
        }

        // DFS 탐색
        int count = 1;
        stack.push(R);
        while (!stack.isEmpty()) {
            final int pop = stack.pop();
            if(visited[pop] > 0) continue; // 방문 노드 스킵
            visited[pop] = count++; // 방문 처리
            final List<Integer> list = graph.get(pop);
            for (Integer item : list) {
                if(visited[item] > 0) continue; // 방문한 노드는 스킵
                stack.push(item);
            }
        }
        for(int i=1; i<=N; i++) {
            System.out.println(visited[i]);
        }
        sc.close();
    }
}
