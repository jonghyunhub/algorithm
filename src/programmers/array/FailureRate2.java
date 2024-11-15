package programmers.array;

import java.util.*;

public class FailureRate2 {
    public static void main(String[] args) {
        int N = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};
        FailureRate2 failureRate2 = new FailureRate2();
        int[] solution = failureRate2.solution(N, stages);
        System.out.print("[");
        for (int i = 0; i< solution.length; i++) {
            System.out.print(solution[i]);
            if (i != solution.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public int[] solution(int N, int[] stages) {
        int[] blockedStages = new int[N +1];
        int[] reachStages = new int[N +1];
        double[] failureRateStages = new double[N+1];
        List<Integer> answer = new ArrayList<>();

        // 사용자가 멈춰있는 배열 구하기
        for (final int nowStage : stages) {
            if (nowStage > N) {
                continue;
            }
            blockedStages[nowStage]++;
        }

        reachStages[0] = stages.length; // 전체인원 넣어놓고 시작
        // 각 스테이지에 도달한 플레이어 수 구하기
        for(int i=1; i<N+1; i++){
            reachStages[i] = reachStages[i-1]-blockedStages[i-1];
        }

        // 실패율 구하기
        for(int i=1; i<N+1; i++){
            if(reachStages[i] == 0){
                failureRateStages[i] = 0;
                continue;
            }
            failureRateStages[i] =  (double) blockedStages[i] / reachStages[i];
        }


        for(int i=1; i<=N; i++){
            answer.add(i);
        }

        return answer.stream().sorted((idx1, idx2) ->
                Double.compare(failureRateStages[idx2], failureRateStages[idx1])
        ).mapToInt(i->i.intValue()).toArray();
    }
}
