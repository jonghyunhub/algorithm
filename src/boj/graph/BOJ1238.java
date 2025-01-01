package boj.graph;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ1238 {
    private final static int INF = 1000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int m = sc.nextInt();
        final int x = sc.nextInt();
        final int[][] graph = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) graph[i][j] = INF;
            }
        }
        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();
            graph[start][end] = weight;
        }

        int answer = 0;
        int[] xToI = dijsktra(graph, x); // x -> i로 가는건 한번만 계산
        for (int i = 1; i < graph.length; i++) {
            if (i == x) continue;
            int[] iToX = dijsktra(graph, i);
            if (answer < iToX[x] + xToI[i])
                answer = iToX[x] + xToI[i];
        }
        System.out.println(answer);
        sc.close();
    }

    public static int[] dijsktra(final int[][] graph, final int start) {
        final int[] dists = new int[graph.length];
        for (int i = 1; i < graph.length; i++) dists[i] = INF;
        dists[start] = 0;
        final PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0)); // 시작점 노드 넣고 시작
        while (!queue.isEmpty()) {
            final Node now = queue.poll();
            final int nowIndex = now.index;
            final int nowDist = now.dist;
            for (int i = 1; i < graph.length; i++) { // 사용편의를 위해 1부터 시작
                if (dists[i] > nowDist + graph[nowIndex][i]) {
                    dists[i] = nowDist + graph[nowIndex][i];
                    queue.add(new Node(i, dists[i]));
                }
            }
        }
        return dists;
    }

    static class Node implements Comparable<Node> {
        int index;
        int dist;

        Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        // 우선순위 큐 오름차순 순서 유지를 위해  compareTo 오버라이드
        @Override
        public int compareTo(Node other) {
            return this.dist - other.dist;
        }
    }
}
