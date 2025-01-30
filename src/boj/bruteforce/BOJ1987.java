package boj.bruteforce;

import java.util.*;

// https://www.acmicpc.net/problem/1987
public class BOJ1987 {

    static int answer = 0;
    static char[][] table;
    static boolean[][] visited;
    static int r;
    static int c;
    static int[][] move = {
            {1,0},{-1,0},{0,1},{0,-1}
    };

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        r = scanner.nextInt();
        c = scanner.nextInt();
        table = new char[r][c];
        visited = new boolean[r][c];
        String inputStr = scanner.nextLine();
        for (int i = 0; i < r; i++) {
            inputStr = scanner.nextLine();
            for (int j = 0; j < c; j++) {
                table[i][j] = inputStr.charAt(j);
            }
        }
        dfs(new ArrayList<>(), 0,0);
        System.out.println(answer);
        scanner.close();
    }

    public static void dfs(List<Character> alphabetList, int row, int col) {
        // 행렬 밖으로 벗어나는 경우 예외처리
        if(row <0 || col <0 || row  >= r || col >= c){
            return;
        }


        // 이미 방문한 노드인 경우 skip
        if(visited[row][col]){
            return;
        }

        // 이전에 처리한 알파벳과 같으면 skip
        if (alphabetList.contains(table[row][col])) {
            return;
        }

        // 현재 노드 방문 처리
        visited[row][col] = true;
        alphabetList.add(table[row][col]);

        answer = Math.max(answer,alphabetList.size());

        for (int[] m: move){
            int nextRow = row + m[0];
            int nextCol = col + m[1];
            dfs(alphabetList, nextRow, nextCol);
        }

        // 백트래킹
        visited[row][col] = false;
        alphabetList.remove(alphabetList.size() - 1);
    }
}
