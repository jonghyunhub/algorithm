package programmers.sort;

import java.util.*;
import java.util.stream.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42746
public class Programmers42746 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] numbers1 = {6, 10, 2};
        int[] numbers2 = {3, 30, 34, 5, 9};

        String answer = solution.solution(numbers2);
        System.out.println(answer);
    }

    static class Solution {
        public String solution(int[] numbers) {
            StringBuilder answer = new StringBuilder();

            List<Integer> sorted = Arrays.stream(numbers)
                    .boxed()
                    .sorted((a, b) -> {
                        String ab = String.valueOf(a) + String.valueOf(b);  // a를 앞에
                        String ba = String.valueOf(b) + String.valueOf(a);  // b를 앞에
                        return ba.compareTo(ab);  // 내림차순
                    })
                    .collect(Collectors.toList());

            for(int num : sorted) {
                answer.append(num);
            }

            String string = answer.toString();
            if(string.charAt(0) == '0') return "0";

            return string;
        }
    }
}
