package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/92342
public class ArcheryCompetition {

    public static void main(String[] args) {

        int n1 = 5;
        int[] info1 = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};
        int[] answer1 = {0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0};
        ArcheryCompetition archeryCompetition = new ArcheryCompetition();
        int[] solution = archeryCompetition.solution(n1, info1);
        System.out.println(Arrays.toString(solution));
        System.out.println(Arrays.equals(answer1, solution));
    }


    static int scoreMax = 0;
    static List<Integer> answer = new ArrayList<>();

    public int[] solution(int n, int[] info) {
        findAnswer(info, new ArrayList<>(), 0, n);

        System.out.println(scoreMax);

        if (answer.isEmpty()) {
            answer.add(-1);
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }

    // todo : n을 어떻게 분배할것인가? ex) [0,1,1,... ,1]
    public void findAnswer(int[] info,
                           List<Integer> tmp,
                           int nowIdx,
                           int leftOver) {
        if (tmp.size() >= info.length) {
            int lionScore = 0;
            int apeachScore = 0;

            for (int i = 0; i < tmp.size(); i++) {
                int lionArrow = tmp.get(i);
                int apeachArrow = info[i];
                if (lionArrow == 0 && apeachArrow == 0) continue;
                if (lionArrow > apeachArrow) {
                    lionScore += (10 - i);
                } else {
                    apeachScore += (10 - i);
                }
            }

            int finalScore = lionScore - apeachScore;

            if (finalScore <= 0) return;

            if (finalScore > scoreMax) {
                scoreMax = finalScore;
                answer = new ArrayList<>(tmp);
            } else if(finalScore == scoreMax) { // 동점인 경우, 역순으로 비교해서 현재 tmp가 더 많이 맞힌 경우에만 answer를 교체
                for (int i = 10; i >= 0; i--) {
                    if (tmp.get(i) > answer.get(i)) {
                        answer = new ArrayList<>(tmp);
                        break;
                    } else if (tmp.get(i) < answer.get(i)) {
                        break; // 더 적게 맞힌 경우엔 그냥 break로 넘어가기
                    }
                }
            }
            return;
        }

        for (int arrow = leftOver; arrow >= 0; arrow--) {
            tmp.add(arrow);
            findAnswer(info, tmp, nowIdx + 1, leftOver - arrow);
            tmp.remove(tmp.size() - 1);
        }
    }
}
