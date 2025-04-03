package boj.graph;

import java.util.*;
import java.util.stream.Collectors;

public class BOJ24444 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int M = sc.nextInt();
        final int R = sc.nextInt();
        final List<List<Integer>> graph = new ArrayList<>(N+1);
        final int[] visited = new int[N+1];
        Arrays.fill(visited, 0);
        final Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<N+1; i++){
            graph.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            final int u = sc.nextInt();
            final int v = sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u); // 무방향 그래프
        }
        for(int i=1; i<N+1; i++){
            final List<Integer> sorted = graph.get(i)
                    .stream()
                    .sorted()
                    .collect(Collectors.toList());
            graph.set(i, sorted);
        }

        int count = 1;
        queue.add(R);
        visited[R] = count++;
        while (!queue.isEmpty()){
            final int poll = queue.poll();
            for(int item :  graph.get(poll)){
                if(visited[item] > 0) continue;
                visited[item] = count++;
                queue.add(item);
            }
        }

        for(int i=1; i<N+1; i++){
            System.out.println(visited[i]);
        }

        sc.close();
    }
}
