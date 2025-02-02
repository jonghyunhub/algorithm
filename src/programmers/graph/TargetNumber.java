package programmers.graph;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/43165
public class TargetNumber {

    public static void main(String[] args) {
        TargetNumber targetNumber = new TargetNumber();
        int[] numbers1 = {1, 1, 1, 1, 1};
        int target1 = 3;
        int[] numbers2 = {4,1,2,1};
        int target2 = 4;
        int solution = targetNumber.solution(numbers2, target2);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] numbers, int target) {
        int answer = 0;
        final Queue<Calculate> queue = new ArrayDeque<>();
        queue.add(new Calculate(0,0));
        while(!queue.isEmpty()){
            final Calculate now = queue.poll();
            if(now.sum == target && now.nextIdx == numbers.length) {
                answer++;
                continue;
            }
            if(now.nextIdx >= numbers.length) continue;
            queue.add(new Calculate(now.sum + numbers[now.nextIdx], now.nextIdx+1)); // 덧셈 처리
            queue.add(new Calculate(now.sum - numbers[now.nextIdx], now.nextIdx+1)); // 뺄셈 처리
        }
        return answer;
    }

    static class Calculate{
        int sum;
        int nextIdx;

        public Calculate(int sum, int nextIdx){
            this.sum = sum;
            this.nextIdx = nextIdx;
        }
    }

}
