package programmers;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/1835
public class Programmers1825 {

    public static void main(String[] args) {
        Programmers1825 programmers1825 = new Programmers1825();
        int n1 = 2;
        String[] data1 = {"N~F=0", "R~T>2"};
        programmers1825.solution(n1, data1);
    }

    public int solution(int n, String[] data) {
        int answer = 0;
        // 아이디어 : 완전탐색
        List<String> origin = List.of("A", "C", "F", "J", "M", "N", "R", "T");
        // 모든 조합 생성
        List<String> combinations = new ArrayList<>();
        List<Boolean> visited = new ArrayList<>();
        for(int i=0; i<origin.size(); i++){
            visited.add(false);
        }
        makeCombinations(combinations, origin, new StringBuilder(), visited);
        // 모든 조합 순회하면서 조건에 맞는 갯수 카운트
        for(String comb : combinations){
            boolean isMatch = true;
            for(String condition: data){
                char firstUser = condition.charAt(0);
                char secondUser = condition.charAt(2);
                String con = condition.substring(3,4);
                Integer interval = Integer.parseInt(condition.substring(4,5));
                int firstUserIdx = 0;
                int secondUserIdx = 0;
                for(int i=0; i<comb.length(); i++){
                    char c = comb.charAt(i);
                    if(c == firstUser){
                        firstUserIdx = i;
                    }
                    if(c == secondUser){
                        secondUserIdx = i;
                    }
                }
                int actualInterval = Math.abs(firstUserIdx - secondUserIdx);

                if(con.equals("=")){
                    // interval = 0 => 이웃하는 경우(인덱스 차이 = 1)
                    // interval = 1 => 1칸 건너뛰는 경우(인덱스 차이 = 2)
                    if(actualInterval != interval+1) {
                        isMatch = false;
                        break;
                    }
                }

                if(con.equals("<")){
                    if(actualInterval >= interval+1){
                        isMatch = false;
                        break;
                    }
                }

                if(con.equals(">")){
                    if(actualInterval <= interval+1){
                        isMatch = false;
                        break;
                    }
                }

            }
            if(isMatch) answer++;
        }
        return answer;
    }

    public void makeCombinations(
            List<String> result,
            List<String> origin,
            StringBuilder tmp,
            List<Boolean> visited
    ){
        if(tmp.length() >= 8){
            result.add(tmp.toString());
            return;
        }
        for(int i=0; i<origin.size(); i++){
            if(visited.get(i)) continue;
            visited.set(i, true);
            tmp.append(origin.get(i));
            makeCombinations(result, origin, tmp, visited);
            tmp.setLength(tmp.length() - 1);
            visited.set(i, false);
        }
    }
}
