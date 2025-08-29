package programmers;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/17682
public class Programmers17682 {

    public static void main(String[] args) {
        Programmers17682 programmers17682 = new Programmers17682();
        String dartResult1 = "1S2D*3T";
        String dartResult2 = "1D2S#10S";
        String dartResult3 = "1D2S0T";
        String dartResult4 = "1S*2T*3S";
        String dartResult5 = "10T#1T*2D*"; // -1988 = -1000 * 2 + 1 * 2 * 2 + 4 * 2
        int solution = programmers17682.solution(dartResult5);
        System.out.println(solution);
    }

    public int solution(String dartResult) {
        int answer = 0;
        int idx = 0;
        int beforePoint = 0;
        Set<Character> options = Set.of('*', '#');
        while(idx < dartResult.length()){
            // 두자리수 점수 고려
            int point = 0;
            if(dartResult.charAt(idx +1) >= '0' && dartResult.charAt(idx +1) <= '9'){
                point = Integer.parseInt(dartResult.substring(idx, idx+2));
                idx ++;
            } else {
             point = Integer.parseInt(dartResult.substring(idx, idx+1));
            }
            char bonus = dartResult.charAt(idx + 1);
            int addPoint = calAddPoint(point, bonus);

            if(idx + 2 < dartResult.length() && options.contains(dartResult.charAt(idx + 2))){
                char option = dartResult.charAt(idx + 2);
                if(option == '*'){
                    answer += beforePoint;
                    addPoint = addPoint * 2;
                    answer += addPoint;
                } else {
                    // option == '#'
                    addPoint = -addPoint;
                    answer += addPoint;
                }

                beforePoint = addPoint;
                idx += 3;
                continue;
            }

            answer += addPoint;
            beforePoint = addPoint;
            idx += 2;
        }
        return answer;
    }

    public int calAddPoint(int point, char bonus){
        if(bonus == 'S'){
            return point;
        }

        if(bonus == 'D'){
            return point * point;
        }

        // bonus == 'T'
        return point * point * point;
    }
}
