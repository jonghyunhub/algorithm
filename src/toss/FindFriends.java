package toss;

import java.util.*;

// 토스 2번 풀이
//1. BFS 알고리즘으로 relationship, target을 기준으로 거리에 따른 리스트를 만든다.
//2. 거리를 기준으로 친구를 구분한다.
//3. 만든 리스트로 결과를 계산한다.
public class FindFriends {
    public static void main(String[] args) {
//        int[][] relationships = {{1, 2}, {2, 3}, {2, 6}, {3, 4}, {4, 5}};
//        int target = 1;
//        int limit = 2;
        int[][] relationships ={ {1, 2}, {2, 3}, {2, 6}, {3, 4}, {4, 5}};
        int target = 2;
        int limit = 3;

        FindFriends findFriends = new FindFriends();

        List<List<Integer>> relationship = findFriends.findRelationships(relationships, target, limit);
        int result = findFriends.calculateResult(relationship);
        System.out.println("result = " + result);
    }


    public int calculateResult(List<List<Integer>> relationships) {
        int result = 0;
        List<Integer> alreadyFriends = relationships.get(0);
        result += alreadyFriends.size() * 5;

        for (int i = 1; i < relationships.size(); i++) {
            List<Integer> newFriends = relationships.get(i);
            result += newFriends.size() * 10 + newFriends.size();
        }
        return result;
    }

    public List<List<Integer>> findRelationships(int[][] relationships, int target, int limit) {
        Map<Integer, List<Integer>> graph = buildGraph(relationships);
        List<Set<Integer>> levels = bfs(graph, target, limit);

        List<List<Integer>> result = new ArrayList<>();
        for (Set<Integer> level : levels) {
            result.add(new ArrayList<>(level));
        }
        return result;
    }

    private Map<Integer, List<Integer>> buildGraph(int[][] relationships) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] relationship : relationships) {
            int a = relationship[0];
            int b = relationship[1];
            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        return graph;
    }

    private List<Set<Integer>> bfs(Map<Integer, List<Integer>> graph, int target, int limit) {
        List<Set<Integer>> levels = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            levels.add(new HashSet<>());
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);

        int level = 0;
        while (!queue.isEmpty() && level < limit) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                List<Integer> neighbors = graph.get(node);
                if (neighbors != null) {
                    for (int neighbor : neighbors) {
                        if (!visited.contains(neighbor)) {
                            levels.get(level).add(neighbor);
                            queue.offer(neighbor);
                            visited.add(neighbor);
                        }
                    }
                }
            }
            level++;
        }

        return levels;
    }
}

