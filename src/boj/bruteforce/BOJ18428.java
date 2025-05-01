package boj.bruteforce;

import java.util.*;

public class BOJ18428 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        final char[][] map = new char[n][n];
        final List<Node> nothings = new ArrayList<>(); // 빈칸
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.next().charAt(0);
                if(map[i][j] == 'X') {
                    nothings.add(new Node(i, j));
                }
            }
        }
        List<Node> moves = List.of(
                new Node(-1, 0),
                new Node(1, 0),
                new Node(0, -1),
                new Node(0, 1)
        );
        List<List<Node>> combinationList = new ArrayList<>();
        makeCombinations(combinationList, nothings, 0, new ArrayList<>());
        for(List<Node> list : combinationList) {
            // 장애물 설정
            for(Node node : list) {
                map[node.row][node.col] = 'O';
            }

            boolean isAvoid = true;
            //피할 수 있는지 체크
            for(int row = 0; row < n; row++) {
                for(int col = 0; col < n; col++) {
                    if(map[row][col] == 'S'){ // 학생일 때 선생 피할수있는지 체크
                        for(Node move : moves) {
                            int nextRow = row;
                            int nextCol = col;
                            while(true) {
                                nextRow += move.row;
                                nextCol += move.col;
                                if(nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) break;
                                if(map[nextRow][nextCol] == 'O') break; // 장애물 먼저 만나면 스킵
                                if(map[nextRow][nextCol] == 'T') { // 선생 만나면 실패
                                    isAvoid = false;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            if(isAvoid) {
                System.out.println("YES");
                return;
            }

            // 장애물 되돌려 놓기
            for(Node node : list) {
                map[node.row][node.col] = 'X';
            }
        }
        System.out.println("NO");
        sc.close();
    }

    public static void makeCombinations(List<List<Node>> result,
                                        List<Node> originals,
                                        int now,
                                        List<Node> combinations
                                        ){
        if(combinations.size() >= 3){
            result.add(new ArrayList<>(combinations));
            return;
        }

        for (int i=now; i<originals.size(); i++) {
            combinations.add(originals.get(i));
            makeCombinations(result, originals, i+1, combinations);
            combinations.remove(combinations.size()-1);
        }
    }

    static class Node{
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

}
