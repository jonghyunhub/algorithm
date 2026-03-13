package leetcode;

import java.util.*;

// https://leetcode.com/problems/find-if-path-exists-in-graph/
public class FindIfPathExistsInGraph {
    public static void main(String[] args) {
        FindIfPathExistsInGraph findIfPathExistsInGraph = new FindIfPathExistsInGraph();
        int n = 10;
        int[][] edges = {{4,3},{1,4},{4,8},{1,7},{6,4},{4,2},{7,4},{4,0},{0,9},{5,4}};
        int source = 5;
        int destination = 9;
        System.out.println(findIfPathExistsInGraph.validPath(n, edges, source, destination));
    }

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        boolean[] visited = new boolean[n];

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge : edges){
            int from = edge[0];
            int to = edge[1];
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            graph.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
        }

        return dfs(graph, visited, source, destination);
    }

    boolean dfs(Map<Integer, List<Integer>> graph,
                boolean[] visited,
                int now, int destination){
        if(now == destination) {
            return true;
        }

        for(int next : graph.getOrDefault(now, new ArrayList<>())){
            if(visited[next]) continue;
            visited[next] = true;
            if(dfs(graph, visited, next, destination)) return true;
            // 해당 문제는 경로가 존재하는지 여부만 탐색하면 돼서 백트래킹 X , 오히려 백트래킹 하면 시간복잡도 초과 발생
        }

        return false;
    }
}
