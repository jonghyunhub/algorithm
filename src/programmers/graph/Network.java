package programmers.graph;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/43162
public class Network {

    public static void main(String[] args) {
        Network network = new Network();
        int n1 = 3;
        int[][] computers1 = {
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        };
        int n2 = 3;
        int[][] computers2 = {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        int solution = network.solution(n2, computers2);
        System.out.println("solution = " + solution);
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        final List<List<Integer>> graph = new ArrayList<>();
        final boolean[] visited = new boolean[n];
        for(int i=0; i<n; i++){
            graph.add(new ArrayList<>());
        }
        for(int col=0; col<computers.length; col++)
            for(int row=0; row<computers[0].length; row++){
                if(col == row) continue;
                if(computers[col][row] == 1) graph.get(col).add(row);
            }
        final Queue<Integer> nextFind = new ArrayDeque<>();
        for(int i=0; i<n; i++) nextFind.add(i);
        while(!nextFind.isEmpty()){
            answer++;
            final int next = nextFind.poll();
            dfs(graph, next, visited, nextFind);
        }
        return answer;
    }


    public void dfs(List<List<Integer>> graph,
                    int now,
                    boolean[] visited,
                    Queue<Integer> nextFind){
        if(visited[now]) return; // 이미 방문한 노드인 경우 종료
        visited[now] = true;

        // 다음에 탐색할 원소에 현재 원소가 포함되어있으면 큐에서 꺼내기
        // Queue의 remove 연산은 숫자 리터럴을 넘기면 index 연산으로 처리하므로
        // 값 삭제를 위해서는 Wrapper 클래스로 넘겨주어야 함
        if(nextFind.contains(now)) nextFind.remove(Integer.valueOf(now));

        for(int next : graph.get(now))
            dfs(graph, next, visited, nextFind);
    }
}
