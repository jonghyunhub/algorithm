package study.dp;

import java.util.HashSet;
import java.util.Set;

public class DijkstraArray {
    static final Integer INF = 100000000; // 1억

    public static void main(String[] args) {
        final int[][] figure4_8_graph = new int[][]{
                {0,0,0,0,0,0},
                { 0,0, 7, 4, 6, 1},
                { 0,INF, 0, INF, INF, INF},
                { 0,INF, 2, 0, 5, INF},
                { 0,INF, 3, INF, 0, INF},
                { 0,INF, INF, INF, 1, 0}
        }; // 0번 정점은 사용하지 않음 (1번부터 사용)


        final int[][] testData = new int[][]{
                { 0, 0,0,0,0,0,0},  // 0번 정점의 연결
                { 0,0, 2, 5, 1, INF, INF},      // 1번 정점의 연결
                { 0,2, 0, 3, 2, INF, INF},      // 2번 정점의 연결
                { 0,5, 3, 0, 3, 1, 5},          // 3번 정점의 연결
                { 0,1, 2, 3, 0, 1, INF},        // 4번 정점의 연결
                { 0,INF, INF, 1, 1, 0, 2},      // 5번 정점의 연결
                { 0, INF, INF, 5, INF, 2, 0}     // 6번 정점의 연결
        }; // 0번 정점은 사용하지 않음

        int n = figure4_8_graph.length; // 정점 개수
//        int n = testData.length;
        Set<Edge> fSet = new HashSet<>();
        int[] touch = new int[n];  // touch 배열을 저장하기 위해 반환받을 배열 추가

        dijkstra(n, figure4_8_graph, fSet, touch);

        //  결과 출력
        System.out.println("최단 경로 트리의 간선들: " + fSet);

        // 1번 정점에서 각 정점까지의 최단 경로 출력
        for (int i = 2; i < n; i++) {
            System.out.print(i + "번 정점으로의 최단 경로: ");
            printShortestPath(touch, i);
            System.out.println();
        }
    }

    static class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "(" + from + "," + to + ")";
        }
    }

    public static void dijkstra(int n, int[][] W, Set<Edge> F, int[] touch) {
        int i, vnear;
        int[] length = new int[n];

        F.clear();

        // 2번 정점부터 시작 0번은 인덱스 편의상 도입한 정점이라 사용 X, 1번은 시작 정점이라 순회하지 않음
        for (i = 2; i < n; i++) {
            touch[i] = 1; // 처음에는 모든 노드의 이전 노드를 시작점(1)으로 설정
            length[i] = W[1][i];
        }

        // n-2번 반복 (0번과 1번 제외한 모든 정점에 대해)
        for (int count = 0; count < n-2; count++) {
            int min = INF;
            vnear = -1;

            for (i = 2; i < n; i++) {
                if (length[i] >= 0 && length[i] < min) {
                    min = length[i];
                    vnear = i;
                }
            }

            Edge e = new Edge(touch[vnear], vnear);
            F.add(e);

            for (i = 2; i < n; i++) {
                if (length[vnear] + W[vnear][i] < length[i]) {
                    length[i] = length[vnear] + W[vnear][i];
                    touch[i] = vnear; // 더 짧은 경로를 찾으면 이전 노드를 갱신
                }
            }

            System.out.println("vnear: " + vnear);
            System.out.print("length: [");

            for (int t=0; t<length.length; t++) {
                System.out.print(length[t]);
                if (t != length.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");

            System.out.print("touch: [");
            for (int t=0; t<touch.length; t++) {
                System.out.print(touch[t]);
                if (t != touch.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
            System.out.println("최단 경로 트리의 간선들: " + F);
            System.out.println();

            length[vnear] = -1;
        }
    }

    // 최단 경로를 출력하는 함수
    private static void printShortestPath(int[] touch, int current) {
        if (current == 1) {  // 시작점에 도달하면
            System.out.print("1 ");
            return;
        }
        // 현재 정점의 이전 정점으로 재귀 호출
        printShortestPath(touch, touch[current]);
        System.out.print("-> " + current + " ");
    }
}