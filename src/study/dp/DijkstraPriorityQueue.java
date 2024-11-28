package study.dp;

import java.util.*;

public class DijkstraPriorityQueue {
    static final int inf = Integer.MAX_VALUE-100;

    public static void main(String[] args) {
        final int[][] graph1 = {
                {0, 8, 1, 2, inf, inf},
                {inf, 0, inf, inf, inf, inf},
                {inf, 5, 0, 2, inf, inf},
                {inf, inf, inf, 0, 3, 5},
                {inf, inf, inf, inf, 0, 1},
                {5, inf, inf, inf, inf, 0}
        };

        int[] dijkstra = dijkstra(graph1, 0);// [0, 6, 1, 2, 5, 6]
        System.out.print("[");
        for (int i : dijkstra) {
            System.out.print(i + " ");
        }
        System.out.println("]");
    }

    /**
     * 1. 길이 배열을 초기화 한다.
     * 2. 시작 정점을 큐에 값과 함께 넣는다.
     * 3. 큐에서 정점을 꺼내고 해당 정점과 연결된 정점들을 확인한다.(graph[i] 배열)
     * 4. 현재 거리 배열의 값과 (현재 정점의 거리 값 + 연결된 정점의 거리 값)을 비교하여 작은 값을 넣는다.
     * 5. 거리 배열의 값이 변경되면 그 인덱스와 거리 값을 우선순위 큐에 넣는다.
     * 6. 3~5의 과정을 큐가 비어 있을 때까지 반복한다.
     * @param graph
     * @param start
     * @return
     */
    public static int[] dijkstra(int[][] graph, int start) {
        final List<Integer> dist = new ArrayList<>(List.of(inf, inf, inf, inf, inf, inf));
        final PriorityQueue<Vertex> queue = new PriorityQueue<>();
        queue.add(new Vertex(start, 0));

        while (!queue.isEmpty()){
            final Vertex poll = queue.poll();
            final int nowIndex = poll.index;
            final int nowDistance = poll.distance;

            for (int i=0; i< graph[nowIndex].length; i++){
                if(dist.get(i) > nowDistance + graph[nowIndex][i]){
                    dist.set(i,nowDistance + graph[nowIndex][i]); // 4. 현재 거리 배열의 값과 (현재 정점의 거리 값 + 연결된 정점의 거리 값)을 비교하여 작은 값을 넣는다.
                    if(graph[nowIndex][i] != 0)
                        queue.add(new Vertex(i, nowDistance + graph[nowIndex][i])); // 5. 거리 배열의 값이 변경되면 그 인덱스와 거리 값을 우선순위 큐에 넣는다.
                }
            }
        }

        return dist.stream().mapToInt(Integer::intValue).toArray();
    }

    public static class Vertex implements Comparable<Vertex>{
        int index;
        int distance;

        public Vertex(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

}
