package programmers.backtracking;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/87946
public class Programmers87946 {
    public static void main(String[] args) {
        Programmers87946 programmers87946 = new Programmers87946();
        int k = 80;
        int[][] dungeons = {{80, 20}, {50, 40}, {30,10}};
        int solution = programmers87946.solution(k, dungeons);
        System.out.println(solution);
    }

    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        Arrays.fill(visited, false);
        int answer = 0;
        for(int start = 0; start < dungeons.length; start++) {
            answer = Math.max(answer, search(k, start, 0, visited, dungeons));
        }
        return answer;
    }

    public int search(int fatigue,
                      int now,
                      int cnt,
                      boolean[] visited,
                      int[][] dungeons) {
        int maxCount = cnt;

        for (int next = 0; next < visited.length; next++) {
            if(now == next) continue;
            if (visited[next] || dungeons[next][0] > fatigue) continue;
            visited[next] = true;
            maxCount = Math.max(maxCount, search(fatigue - dungeons[next][1], next, cnt+1, visited, dungeons));
            visited[next] = false;
        }

        return maxCount;
    }
}
