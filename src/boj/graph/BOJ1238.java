package boj.graph;
<<<<<<< HEAD
import java.util.*;

//https://www.acmicpc.net/problem/1238
public class BOJ1238 {
    static final int MAX_WEIGHT = 100; // 그래프 가중치 최댓값
    static final int INF = Integer.MAX_VALUE - MAX_WEIGHT;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();

        // 그래프 초기화
        final int[][] graph = new int[n+1][n+1];
        for(int i=0; i<m; i++){
            int p = sc.nextInt();
            int q = sc.nextInt();
            graph[p][q] = sc.nextInt();
        }

        // 그래프 초기화시 0인 값은 INF로 변경
        for (int i=0; i<=n; i++) {
            for (int j=0; j<=n; j++) {
                if (graph[i][j] == 0) {
                    graph[i][j] = INF;
                }
            }
        }

        int answer = 0;
        int[] xToNode = solution(graph, x, n);
        for(int i=1; i<=n; i++){
            if(x == i) continue;
            int[] nodeToX = solution(graph, i, n);
            if (answer < nodeToX[x] + xToNode[i]) {
                answer = nodeToX[x] + xToNode[i];
            }
        }

        System.out.println(answer);
    }

    /**
     * 1. 길이 배열을 초기화 한다.
     * 2. 시작 정점을 큐에 값과 함께 넣는다.
     * 3. 큐에서 정점을 꺼내고 해당 정점과 연결된 정점들을 확인한다.(graph[i] 배열)
     * 4. 현재 거리 배열의 값과 (현재 정점의 거리 값 + 연결된 정점의 거리 값)을 비교하여 작은 값을 넣는다.
     * 5. 거리 배열의 값이 변경되면 그 인덱스와 거리 값을 우선순위 큐에 넣는다.
     * 6. 3~5의 과정을 큐가 비어 있을 때까지 반복한다.
     *
     * @param graph
     * @param start
     * @return
     */
    public static int[] solution(int[][] graph, int start, int N) {
        final List<Integer> dist = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            dist.add(INF);
        }
        final PriorityQueue<Vertex> queue = new PriorityQueue<>();
        dist.set(start, 0);  // 시작 정점의 거리를 0으로 초기화
        queue.add(new Vertex(start, 0));

        while (!queue.isEmpty()) {
            final Vertex poll = queue.poll();
            final int nowIndex = poll.index;
            final int nowDistance = poll.distance;

            // 현재 길이가 이전 길이보다 길면 연산이 무의미하므로 스킵
            if (nowDistance > dist.get(nowIndex)) {
                continue;
            }

            for (int i = 1; i < graph[nowIndex].length; i++) {
                if (dist.get(i) > nowDistance + graph[nowIndex][i]) {
                    dist.set(i, nowDistance + graph[nowIndex][i]); // 4. 현재 거리 배열의 값과 (현재 정점의 거리 값 + 연결된 정점의 거리 값)을 비교하여 작은 값을 넣는다.
                    queue.add(new Vertex(i, nowDistance + graph[nowIndex][i])); // 5. 거리 배열의 값이 변경되면 그 인덱스와 거리 값을 우선순위 큐에 넣는다.
                }
            }
        }

        return dist.stream().mapToInt(Integer::intValue).toArray();
    }

    public static class Vertex implements Comparable<Vertex> {
        int index;
        int distance;

        public Vertex(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        // 거리가 작은 순서대로 정렬(오름차순)
        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

=======

import java.util.*;

public class BOJ1238 {
    private final static int INF = 1000000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final int m = sc.nextInt();
        final int x = sc.nextInt();
        final int[][] graph = new int[n+1][n+1];
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=n; j++) {
                if(i != j) graph[i][j] = INF;
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
        for(int i=1; i< graph.length; i++){
            if(i==x) continue;
            int[] iToX = dijsktra(graph, i);
            if(answer < iToX[x] + xToI[i])
                answer = iToX[x] + xToI[i];
        }
        System.out.println(answer);
        sc.close();
    }

    public static int[] dijsktra(final int[][] graph, final int start) {
        final int[] dists = new int[graph.length];
        for (int i=1; i< graph.length; i++) dists[i] = INF;
        dists[start] = 0;
        final PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0)); // 시작점 노드 넣고 시작
        while (!queue.isEmpty()) {
            final Node now = queue.poll();
            final int nowIndex = now.index;
            final int nowDist = now.dist;
            for(int i=1; i< graph.length; i++){ // 사용편의를 위해 1부터 시작
                if(dists[i] > nowDist +  graph[nowIndex][i]){
                    dists[i] = nowDist +  graph[nowIndex][i];
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
>>>>>>> origin/main
}
