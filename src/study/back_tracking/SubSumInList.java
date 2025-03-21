package study.back_tracking;

import java.util.ArrayList;
import java.util.List;

/**
 * [문제 요구사항] : 정수 N을 입력받아 1부터 N까지의 숫자 중에서 합이 10이 되는 조합을 리스트로 반환
 */
public class SubSumInList {
    public static void main(String[] args) {
        int n1 = 5;
        int n2 = 2;
        int n3 = 7;
        SubSumInList subSumInList = new SubSumInList();
        List<List<Integer>> solution = subSumInList.solution(n3);
        System.out.println("solution = " + solution);
    }

    public List<List<Integer>> solution(int n) {
        final List<List<Integer>> answer = new ArrayList<>();
        final List<Integer> original = new ArrayList<>();
        for (int i = 1; i <= n; i++) original.add(i);
        makeSubList(answer, original, new ArrayList<>(), 0, 0);
        return answer;
    }

    public void makeSubList(List<List<Integer>> answer,
                            List<Integer> original,
                            List<Integer> subList,
                            int nowIdx,
                            int sum) {
        if (sum > 10) {
            return;
        }

        if (sum == 10) {
            answer.add(new ArrayList<>(subList));
            return;
        }

        for(int i=nowIdx; i < original.size(); i++) {
            Integer now = original.get(i);
            subList.add(now);
            makeSubList(answer, original, subList, i + 1, sum + now);
            subList.remove(subList.size() - 1);
        }
    }

}
