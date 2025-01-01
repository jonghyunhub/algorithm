package study.graph;

import java.util.*;

public class BFS {

    public static void main(String[] args) {
        Map<String, List<Edge>> graph = new HashMap<>();
        graph.put("A", List.of(
                new Edge("B", 1),
                new Edge("C", 1))
        );
        graph.put("B", List.of(
                new Edge("A", 1),
                new Edge("D", 1))
        );
        graph.put("C", List.of(
                new Edge("A", 1),
                new Edge("G", 1),
                new Edge("H", 1),
                new Edge("I", 1))
        );
        graph.put("D", List.of(
                new Edge("B", 1),
                new Edge("E", 1),
                new Edge("F", 1))
        );
        graph.put("E", List.of(
                new Edge("D", 1))
        );
        graph.put("F", List.of(
                new Edge("D", 1))
        );
        graph.put("G", List.of(
                new Edge("C", 1))
        );
        graph.put("H", List.of(
                new Edge("C", 1))
        );
        graph.put("I", List.of(
                new Edge("C", 1),
                new Edge("J", 1))
        );
        graph.put("J", List.of(
                new Edge("I", 1))
        );

        Queue<Edge> bfs = bfs(graph, "A");
        while (!bfs.isEmpty()) {
            System.out.print(bfs.poll().to + " ");
        }
    }

    public static Queue<Edge> bfs(Map<String, List<Edge>> graph, String start) {
        final Queue<Edge> visited = new ArrayDeque<>();
        final Queue<Edge> needVisit = new ArrayDeque<>();

        needVisit.add(new Edge(start, 1)); // 시작점을 큐에 넣는다.
        while (!needVisit.isEmpty()) {
            final Edge poll = needVisit.poll();
            if (visited.contains(poll)) continue; // 이미 방문한 노드라면 스킵
            visited.add(poll);
            for (Edge edge : graph.get(poll.to)) { // 오른쪽 순회
                needVisit.add(edge);
            }
        }

        return visited;
    }

    static class Edge {
        String to;
        int weight;

        public Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Edge edge)) return false;
            return weight == edge.weight && Objects.equals(to, edge.to);
        }

        public int hashCode() {
            return Objects.hash(to, weight);
        }
    }

}
