package programmers.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42586
 */
public class FeatureDevelopment {

    public static void main(String[] args) {
        int[] progresses1 = {93, 30, 55};
        int[] speeds1 = {1, 30, 5};

        int[] progresses2 = {95, 90, 99, 99, 80, 99};
        int[] speeds2 = {1, 1, 1, 1, 1, 1};
        FeatureDevelopment featureDevelopment = new FeatureDevelopment();
        int[] solution = featureDevelopment.solution(progresses2, speeds2);
        for (int i : solution) {
            System.out.println("i = " + i);
        }
    }

    public int[] solution(int[] progresses, int[] speeds) {
        final Queue<Integer> daysQueue = new ArrayDeque<>();
        final List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < progresses.length; i++) {
            Integer days = (int) Math.ceil((100 - progresses[i]) / (double) speeds[i]);
            daysQueue.add(days);
        }

        while (!daysQueue.isEmpty()) {
            final Integer nowDay = daysQueue.poll();
            Integer groupCnt = 1;
            for (Integer day : daysQueue) {
                if (nowDay < day) break;
                daysQueue.poll();
                groupCnt++;
            }
            answer.add(groupCnt);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }


}
