package boj.graph;

import java.util.*;

// https://www.acmicpc.net/problem/13023
public class BOJ13023 {

    /**
     * [입력값 분석]
     * N : 노드의 갯수 => 0~N-1
     * M : 간선의 갯수
     * <p>
     * [문제 요구사항 분석]
     * 1. 주어진 입력값으로 그래프의 인접리스트를 생성한다.
     * - 그래프 생성시 가중치가 없는 그래프 이므로 양방향으로 생성해야함
     * 2. 생성한 그래프의 모든 노드를 순회한다.
     * 3. 각 노드를 시작점으로 DFS 기법으로 탐색한다.
     * - if) 다음에 탐색할 노드는 남아있는데, 현재 노드와 인접한 노드 중 방문하지 않은 노드가 없으면 백트래킹 처리
     * 4. DFS depth>=4 이면 탐색을 종료하고 1을 출력한다.
     * 5. 2~4를 마쳤는데 depth>=4를 만족하는 노드가 없으면 0을 출력한다.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int N = scanner.nextInt();
        final int M = scanner.nextInt();
        Map<Integer, List<Integer>> graph = new HashMap<>(N);
        for (int i = 0; i < M; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            List<Integer> toOrDefault = graph.getOrDefault(from, new ArrayList<>());
            toOrDefault.add(to);
            graph.put(from, toOrDefault);

            List<Integer> fromOrDefault = graph.getOrDefault(to, new ArrayList<>());
            fromOrDefault.add(from);
            graph.put(to, fromOrDefault);
        }
        scanner.close();

        if (findFriends(graph)) {
            System.out.println(1);
            return;
        }
        System.out.println(0);
    }

    public static boolean findFriends(Map<Integer, List<Integer>> graph) {
        for (int start : graph.keySet()) {
            final Set<Integer> visited = new HashSet<>();
            findRecursive(graph, visited, start);
            if (visited.size() >= 5) return true;
        }
        return false;
    }


    public static void findRecursive(Map<Integer, List<Integer>> graph, Set<Integer> visited, int now) {
        // 현재 노드 방문처리
        visited.add(now);
        for (int nextNode : graph.getOrDefault(now, new ArrayList<>())) {
            // 아직 방문하지 않은 노드에 대해서만 탐색
            if (!visited.contains(nextNode)) findRecursive(graph, visited, nextNode);
        }
        // 방문한 노드의 갯수가 5 이상이면 탐색 종료
        if (visited.size() >= 5) {
            return;
        }
        // 현재 노드 백 트래킹
        visited.remove(now);
    }
}
