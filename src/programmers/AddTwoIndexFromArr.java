package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            for (int j = 0; j < numbers.length; j++) {
                if(i != j){
                    array.add(numbers[i] + numbers[j]);
                }
            }
        }
        return array.stream().distinct().sorted().mapToInt(Integer::new).toArray();
    }
}
