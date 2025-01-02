package programmers.graph;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/1844
public class GameMapShortestPath {
    public static void main(String[] args) {
        int[][] map1 = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        };

        int[][] map2 = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1}
        };

        GameMapShortestPath gameMapShortestPath = new GameMapShortestPath();
        int solution = gameMapShortestPath.solution(map2);
        System.out.println("solution = " + solution);
    }

    public int solution(int[][] maps) {
        final int[][] dists = new int[maps.length][maps[0].length];
        // 시작 점은 1로 처리
        dists[0][0] = 1;
        final Map<Character, Node> move = Map.of(
                'U', new Node(-1, 0),
                'D', new Node(1, 0),
                'L', new Node(0, -1),
                'R', new Node(0, 1)
        );
        final Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0));
        while (!queue.isEmpty()) {
            final Node poll = queue.poll();
            for (char moveKey : move.keySet()) {
                Node moveValue = move.get(moveKey);
                int newCol = poll.col + moveValue.col;
                int newRow = poll.row + moveValue.row;

                // 맵 밖으로 벗어남 처리
                if (newCol < 0 || newCol >= maps.length || newRow < 0 || newRow >= maps[0].length) {
                    continue;
                }

                // 벽처리
                if (maps[newCol][newRow] == 0) {
                    continue;
                }

                // 현재 위치가 처음 방문한 경우에는 큐에 추가
                if (dists[newCol][newRow] == 0) {
                    queue.add(new Node(newCol, newRow));
                    dists[newCol][newRow] = dists[poll.col][poll.row] + 1;
                    continue;
                }

                // 현재 위치의 거리가 기존보다 짧게 올 수 있으면 값을 갱신
                if (dists[newCol][newRow] > dists[poll.col][poll.row] + 1) {
                    dists[newCol][newRow] = dists[poll.col][poll.row] + 1;
                }
            }
        }

        int destination = dists[maps.length - 1][maps[0].length - 1];
        return destination != 0 ? destination : -1;
    }

    public static class Node {
        int col;
        int row;

        Node(int col, int row) {
            this.col = col;
            this.row = row;
        }
    }


}
