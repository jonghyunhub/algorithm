package programmers.sort;

import java.util.*;
import java.util.stream.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/250121
public class Programmers250121 {

    public static void main(String[] args) {
        Programmers250121 programmers250121 = new Programmers250121();
        int[][] data = {{1, 20300104, 100, 80}, {2, 20300804, 847, 37}, {3, 20300401, 10, 8}};
        String ext = "date";
        int val_ext = 20300501;
        String sort_by = "remain";
        int[][] solution = programmers250121.solution(data, ext, val_ext, sort_by);
        System.out.println(Arrays.deepToString(solution));
    }

    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        List<int[]> answer = new ArrayList<>();

        Map<String,Integer> sortByIdxMap = Map.of(
                "code", 0,
                "date", 1,
                "maximum", 2,
                "remain", 3
        );

        // filtering
        int extKey = sortByIdxMap.get(ext);
        for(int[] item : data){
            if(item[extKey] >= val_ext){
                continue;
            }
            answer.add(item);
        }

        // sorting
        answer = answer.stream().sorted((a,b) -> {
            int sortKey = sortByIdxMap.get(sort_by);
            return a[sortKey] - b[sortKey];
        }).collect(Collectors.toList());

        return answer.toArray(new int[answer.size()][]);
    }

}
