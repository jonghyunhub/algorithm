package programmers.array;

import java.util.stream.IntStream;

// https://school.programmers.co.kr/learn/courses/30/lessons/120817
public class AverageOfArray {
    public double solution(int[] numbers) {
        double answer = 0;
        int sum = IntStream.of(numbers).reduce(0, Integer::sum);
        answer = (double) sum / numbers.length;
        return answer;
    }
}
