package boj.dp;

import java.util.*;

/**
 * [점화식]
 * dp(n) = max(dp(i)) (0<=i<n, dp(i)<dp(n))
 * 길이가 n인 것들중 가장 긴 길이의 리스트를 찾아서 출력
 */
public class BOJ14002 {
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final Map<Integer, List<Integer>> memo = new HashMap<>();
        final int n = sc.nextInt();
        final int[] arr = new int[n];
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        memo.put(0, new ArrayList<>());
        memo.get(0).add(arr[0]);
        dp(n-1, arr, memo);
        List<Integer> answer = new ArrayList<>();
        for(int i=0; i<n; i++) {
            List<Integer> list = memo.get(i);
            if(list.size() > answer.size())
                answer = list;
        }
        System.out.println(answer.size());
        for (int item : answer) {
            System.out.print(item + " ");
        }
        sc.close();
    }

    public static void dp(int n, int[] arr, Map<Integer, List<Integer>> memo) {
        List<Integer> list = memo.get(n);
        if(list!= null && !list.isEmpty()){
            return;
        }

        List<Integer> max = new ArrayList<>();
        for(int i=0; i<n; i++) {
            dp(i, arr, memo);
            if(arr[i] >= arr[n]) continue;
            final List<Integer> beForeList = memo.get(i);
            if(beForeList.size() > max.size()){
                max =  new ArrayList<>(beForeList);
            }
        }
        max.add(arr[n]);
        memo.put(n, max);
    }
}
