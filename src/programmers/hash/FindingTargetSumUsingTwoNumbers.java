package programmers.hash;

import java.util.*;

// 알고리즘 책 hash 문제 1번
public class FindingTargetSumUsingTwoNumbers {

    public static void main(String[] args) {
        FindingTargetSumUsingTwoNumbers findingTargetSumUsingTwoNumbers = new FindingTargetSumUsingTwoNumbers();

        final int[] numbers1 = {1, 2, 3, 4, 8};
        final int target1 = 6;

        final int[] numbers2 = {2, 3, 5, 9};
        final int target2 = 10;

        final int[] numbers3 = new int[100000];
        for (int i = 1; i <= numbers3.length; i++) {
            numbers3[i-1] = i;
        }
        final int target3 = 1;


        // 가장 기본적인 방법으로, 밀리초 단위의 측정이 가능합니다
        long startTime = System.currentTimeMillis();

        // 측정하고자 하는 코드
        System.out.println(findingTargetSumUsingTwoNumbers.solution1(numbers3, target3));
        long endTime = System.currentTimeMillis();
        long timeElapsed = endTime - startTime;

        System.out.println("실행 시간: " + timeElapsed + "ms");
    }

    // 완전 탐색 방식
    public boolean solution1(int[] numbers, int target) {
        boolean answer = false;
        for(int i=0; i<numbers.length; i++){
            for (int j=i+1; j<numbers.length; j++){
                if(numbers[i] + numbers[j] == target){
                    answer = true;
                    break;
                }
            }
        }
        return answer;
    }


    // 해시 방식
    public boolean solution2(int[] numbers, int target) {
        boolean answer = false;
        final Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<numbers.length; i++){
            map.put(numbers[i],i); // key: numbers[i], value: index
        }
        for(int now=0; now<numbers.length; now++){
            int find = target - numbers[now];
            if(map.containsKey(find)){
                Integer index = map.get(find);
                if(index != now){
                    answer = true;
                    break;
                }
            }
        }
        return answer;
    }
}
