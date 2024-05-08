package programmers.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ReverseArray {
    public static void main(String[] args) {

    }
    public int[] solution(int[] numList) {
        List<Integer> list = Arrays.stream(numList).boxed().collect(Collectors.toList());
        Collections.reverse(list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
