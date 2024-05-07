package programmers;

import java.util.*;
import java.util.Map.Entry;

public class MockExam {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        MockExam mockExam = new MockExam();
        int[] solution = mockExam.solution(arr1);
        System.out.println(Arrays.toString(solution));
    }

    // 1번 : 1,2,3,4,5 반복
    // 2번 : 2,1,2,3,2,4,2,5 반복
    // 3번 : 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 반복
    public int[] solution(int[] answers) {
        List<Entry<String, Integer>> entries = new ArrayList<>();

        int[] supo1 = {1, 2, 3, 4, 5};
        int[] supo2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] supo3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int countSupo1 = 0;
        int countSupo2 = 0;
        int countSupo3 = 0;

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == supo1[i % supo1.length])
                countSupo1++;
            if (answers[i] == supo2[i % supo2.length])
                countSupo2++;
            if (answers[i] == supo3[i % supo3.length])
                countSupo3++;
        }

        entries.add(Map.entry("1", countSupo1));
        entries.add(Map.entry("2", countSupo2));
        entries.add(Map.entry("3", countSupo3));

        //sorted 메서드 이후에 filter를 사용하면 값이 달라짐, stream은 커밋될때까지 원본 데이터를 유지하기 때문
        entries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        return entries.stream()
                .filter(entry -> entry.getValue() >= entries.get(0).getValue())
                .mapToInt(entry -> Integer.parseInt(entry.getKey()))
                .toArray();
    }
}
