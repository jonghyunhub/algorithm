package programmers.tree;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/92343
public class SheepAndWolf {

    static int MAX = 0;

    public static void main(String[] args) {
        final SheepAndWolf sheepAndWolf = new SheepAndWolf();
        final int[] info1 = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        final int[][] edges1 = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};

        final int[] info2 = {0,1,0,1,1,0,1,0,0,1,0};
        final int[][] edges2 = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6},{3,7},{4,8},{6,9},{9,10}};
        int solution = sheepAndWolf.solution(info2, edges2);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] info, int[][] edges) {
        // 트리 초기화
        final Map<Integer, List<Integer>> tree = new HashMap();
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];

            List<Integer> children = tree.getOrDefault(parent, new ArrayList<>());
            children.add(child);
            tree.put(parent, children);
        }

        // 트리 탐색
        final boolean[] visited = new boolean[info.length];
        traverseTree(tree, info, 0, visited, new ArrayList<>(), 0, 0);
        return MAX;
    }

    /**
     * [종료 조건]
     * 더 이상 탐색할 자식 노드가 없으면 종료
     */
    public static void traverseTree(Map<Integer, List<Integer>> tree,
                                    int[] info,
                                    int search,
                                    boolean[] visited,
                                    List<Integer> canMove,
                                    int sheep,
                                    int wolf) {
        // 이미 방문한 노드인 경우 탐색 종료
        if (visited[search]) return;
        // 현재 노드 방문 처리
        visited[search] = true;

        if (info[search] == 0) { // 양인 경우
            sheep++;
            MAX = Math.max(MAX, sheep);
        } else wolf++; //늑대인 경우


        // 늑대의 수가 양보다 크거나 같아지면 종료
        if (wolf >= sheep) {
            visited[search] = false;
            return;
        }

        List<Integer> children = tree.getOrDefault(search, new ArrayList<>());
        List<Integer> nextCanMove = new ArrayList<>(canMove);
        nextCanMove.addAll(children);

        for (Integer next : nextCanMove) {
            traverseTree(tree, info, next, visited, nextCanMove, sheep, wolf);
        }

        // 백트래킹을 위해 방문 표시 해제
        visited[search] = false;
    }
}
