package programmers.graph;

import java.util.*;

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
        int solution = gameMapShortestPath.solution(map1);
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
                int newRow = poll.row + moveValue.row;
                int newCol = poll.col + moveValue.col;

                // 맵 밖으로 벗어남 처리
                if (newRow < 0 || newRow >= maps.length || newCol < 0 || newCol >= maps[0].length) {
                    continue;
                }

                // 벽처리
                if (maps[newRow][newCol] == 0) {
                    continue;
                }

                // 현재 위치가 처음 방문한 경우에는 큐에 추가
                if (dists[newRow][newCol] == 0) {
                    queue.add(new Node(newRow, newCol));
                    dists[newRow][newCol] = dists[poll.row][poll.col] + 1;
                    continue;
                }

                // 현재 위치의 거리가 기존보다 짧게 올 수 있으면 값을 갱신
                if (dists[newRow][newCol] > dists[poll.row][poll.col] + 1) {
                    dists[newRow][newCol] = dists[poll.row][poll.col] + 1;
                }
            }
        }

        int destination = dists[maps.length - 1][maps[0].length - 1];
        return destination != 0 ? destination : -1;
    }

    public static class Node {
        int row;
        int col;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int solution2(int[][] maps) {
        final Map<String, List<Integer>> move = Map.of(
                "UP", List.of(-1, 0),
                "DOWN", List.of(1, 0),
                "RIGHT", List.of(0, 1),
                "LEFT", List.of(0, -1)
        );
        // 방문 처리 및 거리 저장할 2차원 배열 추가 및 초기화
        int[][] visited = new int[maps.length][maps[0].length];
        for (int[] row : visited)
            Arrays.fill(row, -1);

        final Queue<Node> queue = new ArrayDeque<>();
        // 시작점 넣고 방문처리
        queue.add(new Node(0, 0));
        visited[0][0] = 1;

        // BFS 탐색
        while (!queue.isEmpty()) {
            final Node now = queue.poll();
            for (List<Integer> nextMove : move.values()) {
                final int nextRow = now.row + nextMove.get(0);
                final int nextCol = now.col + nextMove.get(1);
                // 맵에서 벗어난 경우 스킵
                if (nextRow < 0 || nextRow >= maps.length || nextCol < 0 || nextCol >= maps[0].length)
                    continue;
                // 벽인 경우 스킵
                if (maps[nextRow][nextCol] == 0) continue;
                // 이미 방문한 노드인 경우 스킵
                if (visited[nextRow][nextCol] != -1) continue;
                // 방문처리
                visited[nextRow][nextCol] = visited[now.row][now.col] + 1;
                // 큐에 삽입
                queue.add(new Node(nextRow, nextCol));
            }
        }

        return visited[visited.length - 1][visited[0].length - 1];
    }


}
