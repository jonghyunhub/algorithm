package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/42584
public class StockPrice {

    public static void main(String[] args) {
        final StockPrice stockPrice = new StockPrice();
        final int[] prices = {1, 2, 3, 2, 3};
        final int[] result = stockPrice.solution(prices);
        System.out.println("result = " + Arrays.toString(result));
    }

    public int[] solution(int[] prices) {
        final List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < prices.length; i++) {
            int count = 0;
            for (int j = i + 1; j < prices.length; j++) {
                count++;
                if (prices[i] > prices[j]) {
                    break;
                }
            }
            answer.add(count);
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
