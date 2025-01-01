package programmers.hash;

import java.util.HashMap;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/131127
public class DiscountEvent {

    public static void main(String[] args) {

    }

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        final Map<String, Integer> map = new HashMap<>();
        for (int day = 0; day < discount.length; day++) {
            boolean isSuccess = true;

            // 남은 날짜가 10개보다 작아지면 실패처리
            if (discount.length - day < 10) break;

            // 10일간 할인 품목 집계
            map.clear();
            for (int i = day; i <= day + 9; i++) {
                final String productName = discount[i];
                Integer productCnt = map.getOrDefault(productName, 0);
                map.put(productName, productCnt + 1);
            }

            // 할인 품목과 원하는 품목 비교
            // 1. 원하는 품목이 없으면 실패
            // 2. 원하는 품목의 갯수보다 작으면 실패
            for (int i = 0; i < want.length; i++) {
                final String productName = want[i];
                if (!map.containsKey(productName)) {
                    isSuccess = false;
                    break;
                }
                if (map.get(productName) != number[i]) {
                    isSuccess = false;
                    break;
                }
            }

            if (isSuccess) {
                answer++;
            }
        }

        return answer;
    }
}
