package programmers.graph;

import java.util.*;

public class BFS {
    public static void main(String[] args) {
        int[][] graph1 = {
                {1, 2},
                {1, 3},
                {2, 4},
                {2, 5},
                {3, 6},
                {3, 7},
                {4, 8},
                {5, 8},
                {6, 9},
                {7, 9}
        };
        int start = 1;
        int n1 = 9;

        int[][] graph2 = {
                {1, 3},
                {3, 4},
                {3, 5},
                {5, 2}
        };
        int n2 = 5;

        Integer[] answers = solution(start, n2, graph2);
        System.out.print("[");
        for (int i = 0; i < answers.length; i++) {
            System.out.print(answers[i]);
            if (i == answers.length - 1) break;
            System.out.print(", ");
        }
        System.out.println("]");
    }

    public static Integer[] solution(int start, int n, int[][] graph) {
        final Queue<Integer> queue = new ArrayDeque<>();
        final List<Integer> visited = new ArrayList<>();
        final Map<Integer, List<Integer>> graphMap = new HashMap<>(n);

        for (int[] nodes : graph) {
            List<Integer> orDefault = graphMap.getOrDefault(nodes[0], new ArrayList<>());
            orDefault.add(nodes[1]);
            graphMap.put(nodes[0], orDefault);
        }


        queue.add(start);

        while (!queue.isEmpty()) {
            final Integer now = queue.poll();
            if (!visited.contains(now)) visited.add(now);
            List<Integer> nodes = graphMap.getOrDefault(now, new ArrayList<>());
            queue.addAll(nodes);
        }

        return visited.toArray(Integer[]::new);
    }
}
