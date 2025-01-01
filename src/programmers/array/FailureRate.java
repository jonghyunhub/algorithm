package programmers.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/42889
public class FailureRate {

    public static void main(String[] args) {
        FailureRate failureRate = new FailureRate();
        int[] stage1 = {4, 4, 4, 4, 4};
        int[] solution = failureRate.solution(5, stage1);
        System.out.println(Arrays.toString(solution));
    }

    /**
     * 스테이지의 갯수 : N (1<= N <=500)
     * stages : 사용자가 실패한 스테이지의 번호가 담긴 배열
     * -> stages의 각 배열에 들어있는 값은 N의 범위(1~N+1, N+1은 마지막 스테이지까지 클리어한 유저)
     * -> 1<= stages.length <= 200,000
     * 실패율 : 스테이지에 도달했지만, 클리어못한 유저수 / 스테이지에 도달한 유저수
     * -> k번째 스테이지의 실패율 : stages의 값 = k 인 인덱스의 갯수 / stages의 값 >= k 인 인덱스의 갯수
     */
    public int[] solution(int N, int[] stages) {
        List<Map.Entry<Integer, Double>> answer = new ArrayList<>();
        // 각 스테이지의 실패율 구하기 1~N 스테이지
        // stages의 값이 i(스테이지)와 크거나 같은지 비교해서 실패율 비교
        for (int numOfStage = 1; numOfStage <= N; numOfStage++) {
            int countOfNowStageTop = 0;
            int countOfNowStage = 0;
            for (int stage : stages) {
                if (stage == numOfStage)
                    countOfNowStageTop++;
                if (stage >= numOfStage)
                    countOfNowStage++;
            }
            // 스테이지에 도달한 유저가 없는 경우
            if (countOfNowStage == 0) {
                answer.add(Map.entry(numOfStage, 0.0));
                continue;
            }
            Double failureRate = (double) countOfNowStageTop / countOfNowStage;
            answer.add(Map.entry(numOfStage, failureRate));
        }

        return answer.stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }
}
