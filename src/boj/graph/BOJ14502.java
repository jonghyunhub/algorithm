package boj.graph;

import java.util.*;

// https://www.acmicpc.net/problem/14502
public class BOJ14502 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int N = scanner.nextInt();
        final int M = scanner.nextInt();
        final int[][] maps = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                maps[i][j] = scanner.nextInt();
            }
        }
        scanner.close();

        int answer = 0;
        // 빈칸의 정보와 바이러스의 위치를 수집한다.
        final List<Node> blankNodes = new ArrayList<>();
        final List<Node> viruseNodes = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maps[i][j] == 0) blankNodes.add(new Node(i, j));
                if (maps[i][j] == 2) viruseNodes.add(new Node(i, j));
            }
        }

        // 모든 빈칸중 3개를 선택하는 경우를 구한다.
        final List<List<Node>> blankCombinations = new ArrayList<>();
        makeCombinations(blankCombinations, blankNodes, new ArrayList<>(), 0, 3);

        // 선택된 모든 경우를 순회하여
        // 1. 벽을 세운다.
        // 2. 바이러스가 퍼진 상태를 확인한다.
        // 3. 안전지대의 갯수를 센다.
        final int[][] move = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        for (List<Node> newWalls : blankCombinations) {
            int safeCnt = 0;
            // 새로운 지도 복사
            final int[][] newMaps = new int[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    newMaps[i][j] = maps[i][j];
                }
            }

            // 벽을 세운다.
            for (Node newWall : newWalls) {
                newMaps[newWall.row][newWall.col] = 1;
            }

            // BFS를 통해 바이러스가 퍼진 상태를 확인한다.
            for (Node viruses : viruseNodes) {
                spreadViruses(newMaps, viruses, move);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(newMaps[i][j] == 0) safeCnt++;
                }
            }
            if(safeCnt > answer) answer = safeCnt;
        }

        System.out.println(answer);
    }

    public static void makeCombinations(List<List<Node>> result,
                                        List<Node> original,
                                        List<Node> subList,
                                        int now, int size) {
        if (size <= 0) {
            result.add(new ArrayList<>(subList)); // 추가할때는 복사본으로 넣어야 정상적으로 동작함
            return;
        }

        for (int i = now; i < original.size(); i++) {
            subList.add(original.get(i));
            makeCombinations(result, original, subList, i + 1, size - 1);
            subList.remove(subList.size() - 1); // 백트래킹
        }
    }

    public static void spreadViruses(int[][] maps,
                                     Node viruseNode,
                                     int[][] move) {
        final Queue<Node> queue = new ArrayDeque<>();
        queue.add(viruseNode);
        while (!queue.isEmpty()) {
            final Node now = queue.poll();
            for (int[] nextMove : move) {
                int nextRow = now.row + nextMove[0];
                int nextCol = now.col + nextMove[1];

                // 맵 밖으로 벗어난 경우 스킵
                if (nextRow < 0 || nextRow >= maps.length || nextCol < 0 || nextCol >= maps[0].length)
                    continue;

                // 벽을 만난 경우 스킵
                if (maps[nextRow][nextCol] == 1) continue;

                // 바이러스가 이미 전염된 경우 스킵
                if (maps[nextRow][nextCol] == 2) continue;

                // 큐에 추가하고 바이러스 전염 처리
                queue.add(new Node(nextRow, nextCol));
                maps[nextRow][nextCol] = 2;
            }
        }
    }


    private static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


}
