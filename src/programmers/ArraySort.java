package programmers;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class ArraySort {

    public static void main(String[] args) {
        int[] arr1 = {4, 2, 2, 1, 3, 4};
        int[] arr2 = {2, 1, 1, 3, 2, 5, 4};

        int[] solution1 = solution(arr1);
        int[] solution2 = solution(arr2);

        System.out.println(Arrays.toString(solution1));
        System.out.println(Arrays.toString(solution2));
    }

    public static int[] solution(int[] arr) {
        Integer[] array = Arrays.stream(arr).boxed().distinct().toArray(Integer[]::new);
        Arrays.sort(array, Collections.reverseOrder());
        return Arrays.stream(array).mapToInt(Integer::intValue).toArray();
    }
}
