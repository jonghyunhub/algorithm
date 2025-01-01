package programmers.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/42584
public class StockPrice {

    public static void main(String[] args) {
        final StockPrice stockPrice = new StockPrice();
//        final int[] prices = {1, 2, 3, 2, 3};
        final int[] prices = {1, 6, 9, 5, 3, 2, 7};
//        final int[] prices = {1,1,1,7,5,6};
        final int[] result = stockPrice.solution3(prices);
        System.out.println("result = " + Arrays.toString(result));
    }

    public int[] solution(int[] prices) {
        final List<Integer> answers = new ArrayList<>();
        for (int i = 0; i < prices.length; i++) {
            Integer time = 0;
            for (int l = i + 1; l < prices.length; l++) {
                time++;
                if (prices[i] > prices[l]) break;
            }
            answers.add(time);
        }

        return answers.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 1. prices 배열에서 각 원소를 stack에 넣는다.
     * 2. stack에 넣을때, '현재 넣으려고 하는 원소' 보다 '큰 원소가' 존재하면 그 원소들의 길이를 확정한다.
     * 3. 이때, 길이는 '현재 넣으려고 하는 원소'의 index - '큰 원소'의 index 이다. (index를 빼지말고 앞에 원소가 몇개있는지 카운트해서 넣는게 더 편하고 간단)
     * 4. 1,2,3 의 결과를 거치고 남은 주식가격을 확정하지 못한 원소는 가격이 떨어지지 않은 원소이므로 -> prices.length - index - 1이다.
     *
     * @param prices
     * @return
     */
    public int[] solution2(int[] prices) {
        final int[] answer = new int[prices.length];
        final Stack<Price> stack = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            int time = 1;
            while (!stack.isEmpty() && prices[i] < stack.peek().value) {
                Price pop = stack.pop();
                answer[pop.index] = time;
                time++;
            }
            stack.push(new Price(i, prices[i]));
        }

        // stack에 남아있는 원소들은 가격이 떨어지지 않은 원소들이다.
        // 이 원소들도 시간을 처리한다.
        while (!stack.isEmpty()) {
            Price pop = stack.pop();
            answer[pop.index] = prices.length - pop.index - 1;
        }

        return answer;
    }

    public int[] solution3(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[prices.length];
        for (int now = 0; now < prices.length; now++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[now]) {
                Integer pop = stack.pop();
                answer[pop] = now - pop;
            }
            stack.push(now);
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            answer[pop] = prices.length - 1 - pop;
        }

        return answer;
    }

    static class Price {
        int index;
        int value;

        public Price(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
