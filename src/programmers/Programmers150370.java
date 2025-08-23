package programmers;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/150370
public class Programmers150370 {
    public static void main(String[] args) {
        Programmers150370 programmers150370 = new Programmers150370();
        String today1 = "2022.05.19";
        String[] terms1 = {"A 6", "B 12", "C 3"};
        String[] privacies1 = {"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"};

        String today2 = "2020.01.01";
        String[] terms2 = {"Z 3", "D 5"};
        String[] privacies2 = {"2019.01.01 D", "2019.11.15 Z",
                "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z"};

        String today3 =  "2009.12.28";
        String[] terms3 = {"A 13"};
        String[] privacies3 = {"2008.11.03 A"};

        int[] solution = programmers150370.solution(today3, terms3, privacies3);
        for (int i : solution) {
            System.out.println(i);
        }
    }


    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        Map<String, Integer> termMap = new HashMap<>();

        // 약관명, 약관 기간 매핑
        for(String term : terms){
            String[] split = term.split(" ");
            termMap.put(split[0],Integer.parseInt(split[1]));
        }

        for(int i=0; i<privacies.length; i++){
            String[] split = privacies[i].split(" ");
            String registerDay = split[0];
            String termKind = split[1];
            String expiredDay = makeExpiredDay(registerDay, termKind, termMap);
            if(isExpired(today, expiredDay)){
                answer.add(i+1);
            }
        }

        return answer.stream().mapToInt(i->i).toArray();
    }

    public String makeExpiredDay(String registerDay,
                                 String termKind,
                                 Map<String, Integer> termMap){
        int termMonth = termMap.getOrDefault(termKind, 0);

        String[] split = registerDay.split("\\.");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);

        int totalDays = termMonth * 28 - 1;

        // 년도 추가
        int yearsToAdd = totalDays / (12 * 28);
        int remainingDays = totalDays % (12 * 28);
        year += yearsToAdd;

        // 월 추가
        int monthsToAdd = remainingDays / 28;
        remainingDays = remainingDays % 28;
        month += monthsToAdd;

        /**
         * 24월을 처리할 때:
         * 잘못된 방식: 24 / 12 = 2년, 24 % 12 = 0월 → 0월은 존재하지 않음!
         * 올바른 방식: (24-1) / 12 = 1년, ((24-1) % 12) + 1 = 12월 → 1년 12월
         *
         * 12월을 처리할 때:
         * 잘못된 방식: 12 / 12 = 1년, 12 % 12 = 0월 → 0월 문제
         * 올바른 방식: (12-1) / 12 = 0년, ((12-1) % 12) + 1 = 12월 → 0년 12월
         */
        // 월이 12를 넘으면 조정
        if (month > 12) {
            year += (month - 1) / 12; // 추가할 년도
            month = ((month - 1) % 12) + 1; // 조정된 월
        }

        // 일 추가
        day += remainingDays;

        // 일이 28을 넘으면 조정
        if (day > 28) {
            month += (day - 1) / 28;
            day = ((day - 1) % 28) + 1;

            // 다시 월 조정
            if (month > 12) {
                year += (month - 1) / 12;
                month = ((month - 1) % 12) + 1;
            }
        }

        String answer = year + ".";

        if(month < 10){
            answer += "0";
        }

        answer += (month + ".");
        if(day < 10){
            answer += "0";
        }
        answer += day;

        return answer;
    }

    public boolean isExpired(String today,
                             String expiredDay){
        String split[] = today.split("\\.");
        int toDayYear = Integer.parseInt(split[0]);
        int toDayMonth = Integer.parseInt(split[1]);
        int toDayDay = Integer.parseInt(split[2]);

        split = expiredDay.split("\\.");
        int expiredDayYear = Integer.parseInt(split[0]);
        int expiredDayMonth = Integer.parseInt(split[1]);
        int expiredDayDay = Integer.parseInt(split[2]);

        if(expiredDayYear < toDayYear){
            return true;
        }

        if(expiredDayYear == toDayYear && expiredDayMonth < toDayMonth){
            return true;
        }

        return expiredDayYear == toDayYear && expiredDayMonth == toDayMonth && expiredDayDay < toDayDay;
    }
}
