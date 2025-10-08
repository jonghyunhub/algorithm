package programmers;

import java.util.*;

public class Programmers17679 {
    public static void main(String[] args) {
        Programmers17679 programmers17679 = new Programmers17679();
        int m1 = 4;
        int n1 = 5;
        String[] board1 = {"CCBDE", "AAADE", "AAABF", "CCBBF"};

        int m2 = 6;
        int n2 = 6;
        String[] board2 = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};
        int solution = programmers17679.solution(m1, n1, board1);
        System.out.println(solution);
    }

    public int solution(int m, int n, String[] board) {
        int answer = 0;
        char[][] map = new char[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                map[i][j] = board[i].charAt(j);
            }
        }

        while(true){
            // 해당 순회에서 삭제할 노드들
            Set<Node> deleteNodes = new HashSet<>();
            for(int i=0; i<m; i++){
                for(int j=0; j<n; j++){
                    if(map[i][j] == '0') continue;
                    // 현재 노드 기준으로 이웃한 4개 삭제할거 찾기
                    findDeleteNodes(map, new Node(i,j), deleteNodes);
                }
            }
            if(deleteNodes.isEmpty()) break; // 더이상 삭제할게 없으면 게임 종료
            // 삭제처리
            answer += deleteNodes.size();
            for(Node node : deleteNodes){
                // 영어 대문자아닌걸로 삭제된거 처리
                map[node.row][node.col] = '0';
            }
            // 삭제 이후 아래로 내리기
            for(int row=m-2; row>=0; row--) {
                for (int col = 0; col < n; col++) {
                    if (map[row][col] == '0') continue;
                    int nextRow = row + 1;
                    if (map[nextRow][col] != '0') continue;
                    for (; nextRow <= m - 1; nextRow++) {
                        if (map[nextRow][col] != '0') break;
                    }
                    map[nextRow - 1][col] = map[row][col];
                    map[row][col] = '0';
                }
            }
        }

        return answer;
    }

//            "CCBDE"
//            "000DE"
//            "000BF"
//            "CCBBF"
//
//                    "000DE"
//                    "000DE"
//                    "CCBBF"
//                    "CCBBF"
//
//                    "000DE"
//                    "000DE"
//                    "0000F"
//                    "0000F"
    public void findDeleteNodes(char[][] map, Node now, Set<Node> deleteNodes){
        Set<Node> findDelete = new HashSet<>();
        int[][] search = {{0,1}, {1,0}, {1,1}};
        for(int[] find : search){
            int nextRow = now.row + find[0];
            int nextCol = now.col + find[1];
            if(nextRow >= map.length || nextCol >= map[0].length) break;
            char nowType = map[now.row][now.col];
            char nextType = map[nextRow][nextCol];
            if(nextType == '0') break; // 이미 삭제된 곳이면 스킵
            if(nowType != nextType) break; // 타입이 다르면 스킵
            findDelete.add(new Node(nextRow, nextCol));
        }

        if(findDelete.size() >=3) {
            deleteNodes.addAll(findDelete);
            deleteNodes.add(now);
        }
    }


    class Node {
        int row;
        int col;

        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }

        public boolean equals(Object other) {
            Node node = (Node)other;
            return this.row == node.row && this.col == node.col;
        }

        public int hashCode() {
            return Objects.hash(row, col);
        }
    }
}
