package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/11659
public class BOJ11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int n = Integer.parseInt(st.nextToken());
        final int m = Integer.parseInt(st.nextToken());

        final List<Integer> list = new ArrayList<>();
        final List<Long> subSumList = new ArrayList<>();
        final List<Long> result = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        subSumList.add((long)list.get(0));
        for (int i = 1; i < list.size(); i++) {
            Long l = subSumList.get(i - 1);
            subSumList.add(l + list.get(i));
        }

        // 그냥 반복문으로 더하면 O(N^2) 으로 시간초과 발생
        // 입력값이 100,000까지 가능
        // 직접 더하지 않고 부분합을 저장한 배열로 덧셈 구하는 방식으로 해결 -> O(N)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            result.add(subSumList.get(end) - subSumList.get(start) + list.get(start));
        }

        for (long sum : result) {
            System.out.println(sum);
        }
    }
}
