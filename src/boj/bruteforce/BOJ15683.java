package boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ15683 {

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] split = line.split(" ");
        int n = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            split = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }
        // CCTV의 종류에 따라 감시 모드를 저장
        Map<Integer, Integer> moveMap = new HashMap<>();
        moveMap.put(1, 4);
        moveMap.put(2, 2);
        moveMap.put(3, 4);
        moveMap.put(4, 4);
        moveMap.put(5, 1);

        List<Cctv> cctvList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvList.add(new Cctv(i, j, map[i][j]));
                }
            }
        }
        bruteForce(map, cctvList, 0, moveMap);
        System.out.println(answer);
    }

    public static void bruteForce(int[][] map,
                                  List<Cctv> cctvList,
                                  int now,
                                  Map<Integer, Integer> moveMap) {
        // 모두 순회했으면 카운트하기
        if (now >= cctvList.size()) {
            answer = Math.min(answer, countBlindSpot(map));
            return;
        }

        Cctv cctv = cctvList.get(now);
        // 각 CCTV 별로 모드를 순회하면서 감시 처리
        for (int mode = 1; mode <= moveMap.get(cctv.type); mode++) {
            List<int[]> watchedCells = new ArrayList<>();
            updateMap(map, cctv, mode, watchedCells);
            bruteForce(map, cctvList, now + 1, moveMap);
            rollbackMap(map, watchedCells);
        }
    }

    private static int countBlindSpot(int[][] map) {
        int count = 0;
        for (int[] ints : map) {
            for (int anInt : ints) {
                if (anInt == 0) count++;
            }
        }
        return count;
    }

    public static void rollbackMap(int[][] map,  List<int[]> watchedCells) {
        for (int[] rollbackCell : watchedCells) {
            map[rollbackCell[0]][rollbackCell[1]] = 0;
        }
    }


    // # => -1로 대체하여 처리
    public static void updateMap(int[][] map, Cctv cctv, int mode, List<int[]> watchedCells) {
        int row = cctv.row;
        int col = cctv.col;
        if (map[row][col] == 1) {
            switch (mode) {
                case 1 :
                    moveColForward(map, row, col, watchedCells);
                    break;
                case 2 :
                    moveRowForward(map, row, col, watchedCells);
                    break;
                case 3 :
                    moveColBackward(map, row, col, watchedCells);
                    break;
                case 4 :
                    moveRowBackward(map, row, col, watchedCells);
                    break;
            }
            return;
        }

        if (map[row][col] == 2) {
            switch (mode) {
                case 1 :
                    moveColForward(map, row, col, watchedCells);
                    moveColBackward(map, row, col, watchedCells);
                    break;
                case 2 :
                    moveRowForward(map, row, col, watchedCells);
                    moveRowBackward(map, row, col, watchedCells);
                    break;
            }
            return;
        }

        if (map[row][col] == 3) {
            switch (mode) {
                case 1 :
                    moveRowBackward(map, row, col, watchedCells);
                    moveColForward(map, row, col, watchedCells);
                    break;
                case 2 :
                    moveColForward(map, row, col, watchedCells);
                    moveRowForward(map, row, col, watchedCells);
                    break;
                case 3 :
                    moveRowForward(map, row, col, watchedCells);
                    moveColBackward(map, row, col, watchedCells);
                    break;
                case 4 :
                    moveColBackward(map, row, col, watchedCells);
                    moveRowBackward(map, row, col, watchedCells);
                    break;
            }
            return;
        }

        if (map[row][col] == 4) {
            switch (mode) {
                case 1 :
                    moveColBackward(map, row, col, watchedCells);
                    moveRowBackward(map, row, col, watchedCells);
                    moveColForward(map, row, col, watchedCells);
                    break;
                case 2 :
                    moveRowBackward(map, row, col, watchedCells);
                    moveColForward(map, row, col, watchedCells);
                    moveRowForward(map, row, col, watchedCells);
                    break;
                case 3 :
                    moveColForward(map, row, col, watchedCells);
                    moveRowForward(map, row, col, watchedCells);
                    moveColBackward(map, row, col, watchedCells);
                    break;
                case 4 :
                    moveRowForward(map, row, col, watchedCells);
                    moveColBackward(map, row, col, watchedCells);
                    moveRowBackward(map, row, col, watchedCells);
                    break;
            }
            return;
        }

        if (map[row][col] == 5) {
            moveColBackward(map, row, col, watchedCells);
            moveRowBackward(map, row, col, watchedCells);
            moveColForward(map, row, col, watchedCells);
            moveRowForward(map, row, col, watchedCells);
        }
    }

    private static void moveRowBackward(int[][] map, int row, int col, List<int[]> watchedCells) {
        for (int j = row - 1; j >= 0; j--) {
            if (map[j][col] == 6) break;
            if (map[j][col] != 0) continue;
            map[j][col] = -1;
            watchedCells.add(new int[]{j, col});
        }
    }

    private static void moveColBackward(int[][] map, int row, int col, List<int[]> watchedCells) {
        for (int j = col - 1; j >= 0; j--) {
            if (map[row][j] == 6) break;
            if (map[row][j] != 0) continue;
            map[row][j] = -1;
            watchedCells.add(new int[]{row, j});
        }
    }

    private static void moveRowForward(int[][] map, int row, int col, List<int[]> watchedCells) {
        for (int j = row + 1; j < map.length; j++) {
            if (map[j][col] == 6) break;
            if (map[j][col] != 0) continue;
            map[j][col] = -1;
            watchedCells.add(new int[]{j, col});
        }
    }

    private static void moveColForward(int[][] map, int row, int col, List<int[]> watchedCells) {
        for (int j = col + 1; j < map[0].length; j++) {
            if (map[row][j] == 6) break;
            if (map[row][j] != 0) continue;
            map[row][j] = -1;
            watchedCells.add(new int[]{row, j});
        }
    }

    static class Cctv {
        int row;
        int col;
        int type;

        Cctv(int row, int col, int type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }
    }
}
