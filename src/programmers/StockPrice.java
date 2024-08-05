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
        final List<Integer> answers = new ArrayList<>();
        for(int i=0; i<prices.length; i++){
            Integer time = 0;
            for(int l=i+1; l<prices.length; l++){
                time ++;
                if(prices[i] > prices[l]) break;
            }
            answers.add(time);
        }

        return answers.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     *
     *
     * @param prices
     * @return
     */
    public int[] solution2(int[] prices) {
        final List<Integer> answer = new ArrayList<>();




        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
