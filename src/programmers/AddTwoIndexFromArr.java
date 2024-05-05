package programmers;

import java.util.*;

public class AddTwoIndexFromArr {

    public static void main(String[] args) {
        int[] arr1 = {2,1,3,4,1};
        int[] arr2 = {5,0,2,7};

        AddTwoIndexFromArr addTwoIndexFromArr = new AddTwoIndexFromArr();
        int[] solution = addTwoIndexFromArr.solution(arr1);

        System.out.println(Arrays.toString(solution));
    }

    public int[] solution(int[] numbers) {
        List<Integer> array = new ArrayList<>();
        for(int i=0; i<numbers.length; i++){
            for (int j = i+1; j < numbers.length; j++) {
                if(i != j){
                    array.add(numbers[i] + numbers[j]);
                }
            }
        }
        return array.stream().distinct().sorted().mapToInt(Integer::new).toArray();
    }

    /**
     * set 사용한 풀이
     */
    public int[] solution2(int[] numbers) {
        Set<Integer> set = new HashSet<>();
        for(int i=0; i<numbers.length; i++){
            for(int j = i +1; j<numbers.length; j++){
                if(i!=j){
                    set.add(numbers[i] + numbers[j]);
                }
            }
        }
        return set.stream().sorted().mapToInt(Integer::new).toArray();
    }
}
