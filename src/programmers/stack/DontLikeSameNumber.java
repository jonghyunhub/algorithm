package programmers.stack;

import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/12906?language=java
public class DontLikeSameNumber {


    // [TODO]
    // 1. cache Stack을 만든다.
    // 2. stack이 비어있는 경우 -> cache.push(현재원소)
    // 3. if(cache의 가장 위 원소 == 현재 원소) -> 스킵한다.
    // 4. if(cache의 가장 위 원소 != 현재 원소) -> cache.push(현재원소)
    // 5. cache를 배열로 변환하여 리턴한다(answer)

    // [결과]
    // 시간 복잡도 : O(N) , N은 arr의 길이
    public int[] solution(int[] arr) {
        Stack<Integer> cache = new Stack<>();
        for (int i : arr) {
            if (cache.isEmpty()) { // stack 비어있는 경우 예외처리
                cache.push(i);
                continue;
            }
            if (cache.peek() == i) continue;
            cache.push(i);
        }
        return cache.stream().mapToInt(Integer::intValue).toArray();
    }
}
