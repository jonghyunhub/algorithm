package boj.graph;

import java.io.*;
import java.util.*;

public class BOJ1707 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            final List<List<Integer>> graph = new ArrayList<>();
            String[] split = br.readLine().split(" ");
            int V = Integer.parseInt(split[0]); // 정점 갯수
            int E = Integer.parseInt(split[1]); // 간선 갯수

            // 그래프 초기화
            for(int v = 0; v <= V; v++) {
                graph.add(new ArrayList<>()); // 정점 갯수만큼 리스트 추가
            }

            for (int j = 0; j < E; j++) {
                split = br.readLine().split(" ");
                int u = Integer.parseInt(split[0]);
                int v = Integer.parseInt(split[1]);
                graph.get(u).add(v);
                graph.get(v).add(u);
            }

            int[] visited = new int[V+1]; // 0 : 비어있음, 1 : a 집합, 2 : b 집합
            Arrays.fill(visited, 0);

            boolean isBipartite = true;
            for(int node=1; node <= V; node++) {
                if(visited[node] > 0) continue; // 끊어진 그래프도 처리하기 위해 모든 노드 순회
                if(!dfs(node, graph, visited)) {
                    isBipartite = false;
                    break;
                }
            }
            if(isBipartite) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }

    public static boolean dfs(int start,
                           List<List<Integer>> graph,
                           int[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = 1; // 시작 노드 a 집합에 넣기
        while (!stack.isEmpty()) {
            Integer now = stack.pop();
            for(int next: graph.get(now)) {
                if(visited[next] == visited[now]) return false;
                if(visited[next] > 0) continue;
                visited[next] = 3 - visited[now];
                stack.push(next);
            }
        }
        return true;
    }


}
