package programmers.graph;

import java.util.*;

public class Dijkstra {

    public static void main(String[] args) {
        int[][] graph1 = {
                {0, 1, 9},
                {0, 2, 3},
                {1, 0, 5},
                {2, 1, 1}
        };
        int start1 = 0;
        int n1 = 3;

        int[][] graph2 = {
                {0, 1, 1},
                {1, 2, 5},
                {2, 3, 1}
        };
        int start2 = 0;
        int n2 = 4;

        int[] answers = solution(graph2, start2, n2);
        System.out.print("[");
        for (int i = 0; i < answers.length; i++) {
            System.out.print(answers[i]);
            if (i == answers.length - 1) break;
            System.out.print(", ");
        }
        System.out.println("]");
    }

    public static int[] solution(int[][] graph, int start, int n) {
        final Integer MAX_VALUE = Integer.MAX_VALUE - 100000;

        // 그래프 인접 리스트 생성
        final Map<Integer, List<Vertex>> graphMap = new HashMap<>(n);
        for (int[] nodes : graph) {
            int fromNode = nodes[0];
            int toNode = nodes[1];
            int weight = nodes[2];
            List<Vertex> orDefault = graphMap.getOrDefault(fromNode, new ArrayList<>());
            orDefault.add(new Vertex(toNode, weight));
            graphMap.put(fromNode, orDefault);
        }

        // 각 노드 최단 경로 저장 리스트 생성
        final List<Dist> dists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dists.add(new Dist(MAX_VALUE, -1));
        }

        // 출발지 설정
        Dist startDist = dists.get(start);
        startDist.value = 0;
        startDist.prevNode = start;

        final PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Vertex(start, 0));

        // 노드간 최단 거리 정보 갱신
        while (!priorityQueue.isEmpty()) {
            final Vertex poll = priorityQueue.poll();
            List<Vertex> vertices = graphMap.getOrDefault(poll.index, new ArrayList<>());
            for (Vertex vertex : vertices) {
                int nextNode = vertex.index;
                int weight = vertex.weight;
                Dist dist = dists.get(nextNode);
                if (dist.value > poll.weight + weight) {
                    dist.value = poll.weight + weight;
                    dist.prevNode = poll.index;
                    priorityQueue.add(new Vertex(nextNode, dist.value));
                }
            }
        }

        printChannels(dists);

        return dists.stream().mapToInt(dist -> dist.value).toArray();
    }

    private static void printChannels(List<Dist> dists) {
        for (int index = 0; index < dists.size(); index++) {
            System.out.print(index + ": ");
            printPrevNode(dists.get(index), index, dists);
            System.out.println();
        }
    }

    private static void printPrevNode(Dist dist, int index, List<Dist> dists) {
        if (dist.prevNode == index) {
            System.out.print(index + " ");
            return;
        }
        int prevNode = dist.prevNode;
        printPrevNode(dists.get(prevNode), prevNode, dists);
        System.out.print(index + " ");
    }

    static class Vertex implements Comparable<Vertex> {
        int index;
        int weight;

        Vertex(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }

        public int compareTo(Vertex other) {
            return Integer.compare(this.weight, other.weight); // 오름차순 정렬
        }
    }

    static class Dist {
        int value;
        int prevNode;

        Dist(int value, int prevNode) {
            this.value = value;
            this.prevNode = prevNode;
        }
    }
}
