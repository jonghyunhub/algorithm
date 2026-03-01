package programmers.greedy;

import java.util.*;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/42861
public class Programmers42861 {
    public static void main(String[] args) {
        Programmers42861 programmers42861 = new Programmers42861();
        int n = 4;
        int[][] costs = {{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}};
        int solution = programmers42861.solution(n, costs);
        System.out.println(solution);
    }


    /**
     * 섬 연결하기 => MST 만들기
     * [프림 알고리즘]
     * 1. 임의의 정점 하나를 MST에 추가
     * 2. 현재 정점과 연결된 정점(MST에 추가 안된)중 가중치가 가장 적은 정점 추가
     * 3. 해당 정점을 추가했을때 사이클이 생기면 롤백 => dfs 로 순환후 방문한 노드 존재 유무 판별
     * 4. MST 완성 이후 각 간선의 가중치를 전부 더해서 리턴
     */
//    public int solution(int n, int[][] costs) {
//        Map<Integer, List<Node>> graph = new HashMap<>();
//        for (int[] cost : costs) {
//            graph.computeIfAbsent(cost[0], k -> new ArrayList<>()).add(
//                    new Node(cost[1], cost[2])
//            );
//            graph.computeIfAbsent(cost[1], k -> new ArrayList<>()).add(
//                    new Node(cost[0], cost[2])
//            );
//        }
//
//        Map<Integer, List<Node>> mst = new HashMap<>();
//        Queue<Integer> queue = new ArrayDeque<>();
//        int startNum = 0;
//        queue.add(startNum);
//        while (!queue.isEmpty()) {
//            Integer poll = queue.poll();
//            System.out.println("현재 처리 중인 노드: " + poll);
//            mst.computeIfAbsent(poll, k -> new ArrayList<>());
//            List<Node> orDefault = graph.getOrDefault(poll, new ArrayList<>()).stream()
//                    .sorted((a, b) -> a.dist - b.dist)
//                    .collect(Collectors.toList());
//            for (Node node : orDefault) { // 현재 정점과 연결된 간선중 가중치가 가장 작은 간선 탐색
//                System.out.println("MST 현재 상태: " + mst);
//                if (mst.containsKey(node.num)) continue;
//                mst.computeIfAbsent(poll, k -> new ArrayList<>()).add(node);
//                mst.computeIfAbsent(node.num, k -> new ArrayList<>()).add(new Node(poll, node.dist));
//                if (hasCycle(mst, 0, n)) { // 사이클 탐색
//                    mst.get(poll).remove(node);
//                    mst.get(node.num).remove(new Node(poll, node.dist));  // 롤백도 양방향
//                    continue;
//                }
//                queue.add(node.num);
//                break;
//            }
//        }
//
//        return lookUpAnswer(mst, 0, new boolean[n]);
//    }

    public int solution(int n, int[][] costs) {
        Map<Integer, List<Node>> graph = new HashMap<>();
        for (int[] cost : costs) {
            graph.computeIfAbsent(cost[0], k -> new ArrayList<>()).add(new Node(cost[1], cost[2]));
            graph.computeIfAbsent(cost[1], k -> new ArrayList<>()).add(new Node(cost[0], cost[2]));
        }

        Map<Integer, List<Node>> mst = new HashMap<>();
        // 노드 번호 대신 간선(from, to, dist)을 가중치 기준으로 정렬
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        // 시작 노드 0의 인접 간선을 모두 후보로 추가
        mst.computeIfAbsent(0, k -> new ArrayList<>());
        for (Node next : graph.getOrDefault(0, new ArrayList<>())) {
            pq.offer(new int[]{0, next.num, next.dist});
        }

        while (!pq.isEmpty()) {
            int[] edge = pq.poll(); // 현재 가장 가중치가 작은 간선
            int from = edge[0], to = edge[1], dist = edge[2];

            if (mst.containsKey(to)) continue; // 이미 MST에 포함 → 사이클 방지

            // 양방향 추가
            mst.computeIfAbsent(from, k -> new ArrayList<>()).add(new Node(to, dist));
            mst.computeIfAbsent(to, k -> new ArrayList<>()).add(new Node(from, dist));

            // to의 인접 간선을 후보에 추가
            for (Node next : graph.getOrDefault(to, new ArrayList<>())) {
                if (!mst.containsKey(next.num)) {
                    pq.offer(new int[]{to, next.num, next.dist});
                }
            }
        }

        return lookUpAnswer(mst, 0, new boolean[n]);
    }


    public boolean hasCycle(Map<Integer, List<Node>> mst, int startNode, int n) {
        boolean[] visited = new boolean[n];
        return dfs(mst, startNode, startNode, visited);
    }

    // 부모 노드 제외하고 사이클 탐색
    boolean dfs(Map<Integer, List<Node>> mst, int now, int parent, boolean[] visited) {
        visited[now] = true;
        for (Node next : mst.getOrDefault(now, new ArrayList<>())) {
            if (!visited[next.num]) {
                if (dfs(mst, next.num, now, visited)) return true;
            } else if (next.num != parent) { // 부모가 아닌 방문 노드 = 사이클
                return true;
            }
        }
        return false;
    }

    public int lookUpAnswer(Map<Integer, List<Node>> mst, int now, boolean[] visited) {
        int answer = 0;
        visited[now] = true;
        for (Node next : mst.getOrDefault(now, new ArrayList<>())) {
            if (visited[next.num]) continue;
            answer += next.dist;
            answer += lookUpAnswer(mst, next.num, visited);
        }
        return answer;
    }

    static class Node {
        int num;
        int dist;

        Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Node node)) return false;
            return num == node.num && dist == node.dist;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num, dist);
        }
    }
}
