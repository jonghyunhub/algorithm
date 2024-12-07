package study.graph;

import java.util.*;

public class DFS {
    public static void main(String[] args) {
        final Map<String, List<Edge>> graph = new HashMap<>();
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

        Queue<Edge> dfs = dfs(graph, "A");
        while (!dfs.isEmpty()){
            System.out.print(dfs.poll().to + " ");
        }
    }

    public static Queue<Edge> dfs(Map<String, List<Edge>> graph, String start) {
        final Queue<Edge> visited = new ArrayDeque<>();
        final Stack<Edge> needVisit = new Stack<>();
        needVisit.add(new Edge(start, 1)); // 시작점을 스택에 넣는다.
        while (!needVisit.isEmpty()){
            final Edge now = needVisit.pop();
            if(visited.contains(now)) continue; // 이미 방문한 노드라면 스킵
            visited.add(now);
//            for(Edge edge : graph.get(now.to)){ // 오른쪽 순회
//                needVisit.push(edge);
//            }
            List<Edge> list = new ArrayList<>(graph.get(now.to));
            Collections.reverse(list);
            for(Edge edge : list){ // 왼쪽 순회
                needVisit.push(edge);
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

        public int hashCode(){
            return Objects.hash(to, weight);
        }

        public boolean equals(Object o){
            if(o == this) return true;
            if(! (o instanceof Edge edge)) return false;
            return weight == edge.weight && Objects.equals(to, edge.to);
        }
    }
}
