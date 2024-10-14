package programmers.queue;

import java.util.ArrayDeque;
import java.util.Scanner;

/**
 * 요세푸스 문제
 * https://www.acmicpc.net/problem/1158
 */
public class Josephus {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 7
        int k = sc.nextInt(); // 3
        int[] result = solution(n, k);
        System.out.print("<");
        for (int i=0; i<result.length; i++) {
            System.out.print(result[i]);
            if(i != result.length-1) {
                System.out.print(", ");
            }
        }
        System.out.print(">");
        sc.close();
    }

    /**
     * 1. 1부터 N까지 순서대로 큐에 적재한다.
     * 2. 반복문을 실행하여 큐의 Front부터 시작하여 K번째 원소를 꺼낸다 - poll()
     * 3. 2에서 꺼낸 큐의 맨 처음 원소를 큐의 맨 뒤로 보낸다 - add()
     * 4. if) K번째 원소를 만나면 큐에서 꺼내서 정답 배열에 넣는다.
     *     1. 여기서는 정답 큐를 사용
     *     2. 별도의 배열 인덱스를 관리 할 필요 없이 큐가 넣은 순서를 보장해주기 때문
     *     3. 정답을 담을 자료구조로 배열을 사용하면 넣을때마다 배열의 인덱스를 알아야함
     * 5. 큐에서 모든 원소를 꺼내면 멈춘다. if(queue.isEmpty()) ⇒ break;
     *
     * @param n
     * @param k
     */
    public static int[] solution(int n, int k) {
        final ArrayDeque<Integer> queue = new ArrayDeque<>(n);
        final ArrayDeque<Integer> answer = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            queue.add(i);
        }

        int idx = 1;
        while (!queue.isEmpty()) {
            if (idx % k == 0) {
                answer.add(queue.poll());
                idx++;
                continue;
            }
            Integer first = queue.poll();
            queue.add(first);
            idx++;
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
