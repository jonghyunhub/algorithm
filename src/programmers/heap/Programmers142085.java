package programmers.heap;

import java.util.*;


// https://school.programmers.co.kr/learn/courses/30/lessons/142085
public class Programmers142085 {
    public static void main(String[] args) {
        Programmers142085 solution = new Programmers142085();
        int n1 = 7;
        int k1 = 3;
        int[] enemy1 = {4, 2, 4, 5, 3, 3, 1};
        int solution1 = solution.solution(n1, k1, enemy1);
        System.out.println(solution1);
    }

    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        // 최대 힙(내림차순 우선순위 큐) 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int round : enemy){
            n -= round;
            pq.add(round);
            if(n < 0){ // 모든 병사 소진시
                if(k<=0) break; // 무적권 없으면 종료
                Integer maxEnemy = pq.poll();
                n += maxEnemy;
                k--;
            }
            answer++;
        }
        return answer;
    }
}
