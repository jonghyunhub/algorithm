package programmers;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/12901
public class Programmers12901 {

    public static void main(String[] args) {
        int a= 5;
        int b = 24;
        String result = "TUE";
        Programmers12901 p = new Programmers12901();
        String solution = p.solution(a, b);
        System.out.println(solution.equals(result));
    }

    // 윤년 => 2월 29일까지 있음
    // 1월 => 31, 2월 => 29, 3월 => 30일 =>
    public String solution(int a, int b) {
        List<Integer> monthToDayList = List.of(
                31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
        );
        // 1월 1일 => 금요일
        // 1월 2일 => 1일 지남 => 금 + 1 => 토요일
        int dayDiff = 0;
        // 월 계산 <- 누적해서 계산 필요
        for(int month = 0; month < a-1; month++){
            dayDiff += monthToDayList.get(month);
        }
        // 일자 계산 1월 2일의 경우 1일이 지남
        // 2월 3일의 경우 => 1월 1일 기준으로 31 + 2일 지남
        dayDiff += (b-1);
        dayDiff %= 7;

        Map<Integer, String> dayOfDiffMap = Map.of(
                0, "FRI",
                1, "SAT",
                2, "SUN",
                3, "MON",
                4, "TUE",
                5, "WED",
                6, "THU"
        );

        return dayOfDiffMap.get(dayDiff);
    }
}
