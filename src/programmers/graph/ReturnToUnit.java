package programmers.graph;

import java.util.*;

public class ReturnToUnit {
    public static void main(String[] args) {
        int n1 = 3;
        int[][] roads1 = {{1, 2}, {2, 3}};
        int[] sources1 = {2, 3};
        int destination1 = 1;

        int n2 = 5;
        int[][] roads2 = {{1, 2}, {1, 4}, {2, 4}, {2, 5}, {4, 5}};
        int[] sources2 = {1, 3, 5};
        int destination2 = 5;

        ReturnToUnit returnToUnit = new ReturnToUnit();
        int[] solution = returnToUnit.solution(n2, roads2, sources2, destination2);
        System.out.print("[");
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i]);
            if (i == solution.length - 1) break;
            System.out.print(", ");
        }
        System.out.println("]");
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer> answer = new ArrayList<>();

        // 그래프 리스트 초기화
        // 그래프 초기화시 각 노드의 번호 1~N까지 할당필요
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++)
            graph.add(new ArrayList<>());

        // 인접리스트 변환
        for (int[] edge : roads) {
            int from = edge[0];
            int to = edge[1];
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        for (int start : sources) {
            int shortestPath = findShortestWithBfs(start, destination, graph);
            answer.add(shortestPath);
        }


        return answer.stream().mapToInt(i -> i).toArray();
    }

    // 다익스트라 탐색 - 이거 사용시 시간 초과 + 메모리 초과에 걸림
    // 우선순위 큐에서 담을때마다 O(NlogN) 정렬 사용됨
    public int findShortestPath(int start, int destination, List<List<Integer>> graph) {
        int inf = Integer.MAX_VALUE - 1000000;
        final List<Integer> dists = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            dists.add(inf);
        }
        final PriorityQueue<Path> queue = new PriorityQueue<>();
        queue.add(new Path(start, 0));
        while (!queue.isEmpty()) {
            final Path now = queue.poll();
            // 현재 노드가 도착점이면 거리 리턴
            if (now.idx == destination) return now.dist;
            // 인접 노드들 거리가 더 짧으면 우선순위 큐에 담기
            for (int node : graph.get(now.idx)) {
                int nodeDist = now.dist + 1;
                if (dists.get(node) > nodeDist)
                    queue.add(new Path(node, nodeDist));
            }
        }

        return -1;
    }

    static class Path implements Comparable<Path> {
        int idx;
        int dist;

        Path(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        public int compareTo(Path other) {
            // 오름차순 정렬
            return Integer.compare(this.dist, other.dist);
        }
    }

    // bfs 탐색
    public int findShortestWithBfs(int start, int destination, List<List<Integer>> graph) {
        int inf = Integer.MAX_VALUE - 1000000;
        final List<Integer> dists = new ArrayList<>();
        for (int i = 0; i < graph.size(); i++) {
            dists.add(inf);
        }
        final Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        dists.set(start, 0);
        while (!queue.isEmpty()) {
            final Integer now = queue.poll();
            // 현재 노드가 도착점이면 거리 리턴
            if (now == destination) return dists.get(now);
            // 인접 노드들 거리가 더 짧으면 최단 거리 업데이트하고 큐에 담기
            for (int node : graph.get(now)) {
                int nowDist = dists.get(now) + 1;
                if (dists.get(node) > nowDist) {
                    // 이후 노드가 도착점이면 탐색 종료
                    if (node == destination) return nowDist;
                    dists.set(node, nowDist);
                    queue.add(node);
                }
            }
        }
        return -1;
    }
}
