package programmers.graph;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/159993
public class EscapeFromMaze {

    public static void main(String[] args) {
        EscapeFromMaze solution = new EscapeFromMaze();
        String[] maps1 = {
                "SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"
        };
        String[] maps2 = {
                "LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"
        };
        int solution1 = solution.solution(maps1);
        System.out.println("solution1 = " + solution1);
    }

    /**
     미로를 최단거리로 이동하여 탈출해야한다.
     S : 시작 지점
     X : 벽은 이동할수 없다
     O : 통로는 이동가능
     L : 출구로 이동하기전에 레버로 먼저 이동해야한다.
     E : 레버를 당긴 이후에 출구로 가야한다.
     최단 거리 탐색을 위해 BFS 방식으로 탐색
     S -> L 이동한 최단거리 계산
     L -> E 이동한 최단거리 계산
     단, L 까지 이동한 이후에 다시 E까지 이동해야하므로, L 도달 이후 visited 초기화 처리
     */
    public int solution(String[] maps) {
        final Map<String, List<Integer>> move = Map.of(
                "UP", List.of(-1,0),
                "DOWN" , List.of(1,0),
                "RIGHT", List.of(0,1),
                "LEFT", List.of(0,-1)
        );
        // 방문 처리 및 거리 저장할 2차원 배열 추가 및 초기화
        final int[][] visited = new int[maps.length][maps[0].length()];
        for (int[] row : visited)
            Arrays.fill(row, -1);

        final Queue<Node> queue = new ArrayDeque<>();
        int startRow = 0;
        int startCol = 0;
        // 시작점 넣고 방문처리
        for(int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (maps[i].charAt(j) == 'S') {
                    startRow = i;
                    startCol = j;
                }
            }
        }
        queue.add(new Node(startRow, startCol));
        visited[startRow][startCol] = 0;
        boolean isLevered = false;

        while(!queue.isEmpty()){
            final Node now = queue.poll();
            int col = now.col;
            int row = now.row;
            switch(maps[col].charAt(row)){
                case 'S' :
                    for(List<Integer> nextMove : move.values()){
                        // 맵을 벗어나면 그 이동은 스킵
                        int nextCol = col + nextMove.get(0);
                        int nextRow = row + nextMove.get(1);
                        if(nextCol < 0 || nextCol >= maps.length) continue;
                        if(nextRow < 0 || nextRow >= maps[0].length()) continue;
                        if(visited[nextCol][nextRow] > 0) continue; // 이미 방문한 노드는 스킵
                        visited[nextCol][nextRow] = visited[col][row] + 1;
                        queue.add(new Node(nextCol, nextRow));
                    }
                    break;
                case 'X' :
                    // 벽은 지나갈수 없으니 스킵
                    break;
                case 'O':
                    // 통로는 지나갈 수 있음
                    for(List<Integer> nextMove : move.values()){
                        // 맵을 벗어나면 그 이동은 스킵
                        int nextCol = col + nextMove.get(0);
                        int nextRow = row + nextMove.get(1);
                        if(nextCol < 0 || nextCol >= maps.length) continue;
                        if(nextRow < 0 || nextRow >= maps[0].length()) continue;
                        if(visited[nextCol][nextRow] > 0) continue; // 이미 방문한 노드는 스킵
                        visited[nextCol][nextRow] = visited[col][row] + 1;
                        queue.add(new Node(nextCol, nextRow));
                    }
                    break;
                case 'L':
                    if(!isLevered){ // 래버 처음 방문시에는 값 처리후 visited 초기화
                        isLevered = true;
                        int saveMove = visited[col][row] + 1;
                        for (int[] tmp : visited)
                            Arrays.fill(tmp, -1);
                        visited[col][row] = saveMove;
                    }
                    for(List<Integer> nextMove : move.values()){
                        // 맵을 벗어나면 그 이동은 스킵
                        int nextCol = col + nextMove.get(0);
                        int nextRow = row + nextMove.get(1);
                        if(nextCol < 0 || nextCol >= maps.length) continue;
                        if(nextRow < 0 || nextRow >= maps[0].length()) continue;
                        if(visited[nextCol][nextRow] > 0) continue; // 이미 방문한 노드는 스킵
                        visited[nextCol][nextRow] = visited[col][row] + 1;
                        queue.add(new Node(nextCol, nextRow));
                    }
                    break;
                case 'E':
                    // 래버를 만난 이후에 방문하면 종료
                    if(isLevered) return visited[col][row];
                    // 래버를 만나기 이전에 방문하면 다음 노드 방문
                    for(List<Integer> nextMove : move.values()){
                        // 맵을 벗어나면 그 이동은 스킵
                        int nextCol = col + nextMove.get(0);
                        int nextRow = row + nextMove.get(1);
                        if(nextCol < 0 || nextCol >= maps.length) continue;
                        if(nextRow < 0 || nextRow >= maps[0].length()) continue;
                        if(visited[nextCol][nextRow] > 0) continue; // 이미 방문한 노드는 스킵
                        visited[nextCol][nextRow] = visited[col][row] + 1;
                        queue.add(new Node(nextCol, nextRow));
                    }
                    break;
            }
        }
        return -1; // 탈출할 수 없다면 -1 리턴
    }

    static class Node {
        int row;
        int col;

        public Node(int row, int col){
            this.row = row;
            this.col = col;
        }
    }
}
