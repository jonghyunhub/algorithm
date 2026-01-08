package leetcode;

import java.util.*;
import java.util.stream.*;

// https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/submissions/1878351004/
public class AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod {
    public static void main(String[] args) {
        AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod solution = new AlertUsingSameKeyCardThreeOrMoreTimesInAOneHourPeriod();
        String[] keyName1 = {"daniel", "daniel", "daniel", "luis", "luis", "luis", "luis"};
        String[] keyTime1 = {"10:00","10:40","11:00","09:00","11:00","13:00","15:00"};

        String[] keyName2 = {"leslie","leslie","leslie","clare","clare","clare","clare"};
        String[] keyTime2 = {"13:00","13:20","14:00","18:00","18:51","19:30","19:49"};

        String[] keyName3 = {"john", "john", "john"};
        String[] keyTime3 = {"23:58","23:59","00:01"};

        String[] keyName4 = {"a", "a", "a", "a", "a", "a", "a", "b", "b", "b", "b", "b", "b", "b", "c", "c", "c", "c", "c", "c", "c", "c", "c"};
        String[] keyTime4 = {"00:37","11:24","14:35","21:25","15:48","20:28","07:30","09:26","10:32","20:10","19:26","08:13","01:08","15:49","02:34","06:48","04:33","07:18","00:05","06:44","13:33","04:12","03:54"};

        List<String> strings = solution.alertNames(keyName4, keyTime4);
        strings.forEach(System.out::println);
    }

    /**
     * idea : 슬라이딩 윈도우
     * 1. 각 직원별로 시간을 정렬
     * 2. 정렬된 시간에서 연속된 3개를 확인
     * 3. time[i+2] - time[i] <= 60이면 알람 대상
     */
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Set<String> answer = new HashSet<>();
        Map<String, List<Integer>> nameToTime = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            String[] split = keyTime[i].split(":");
            int hour = Integer.parseInt(split[0]);
            int min = Integer.parseInt(split[1]);
            int actualTime = hour * 60 + min;
            nameToTime.computeIfAbsent(keyName[i], k -> new ArrayList<>()).add(actualTime);
        }
        for(String name: nameToTime.keySet()){
            List<Integer> times = nameToTime.get(name);
            Collections.sort(times);
            nameToTime.put(name,times);
        }

        for(String name: nameToTime.keySet()){
            List<Integer> times = nameToTime.get(name);
            for(int i = 0; i < times.size()-2; i++){
                int time1 = times.get(i);
                int time2 = times.get(i+2);
                if(time2 - time1 <= 60){
                    answer.add(name);
                }
            }
        }

        return new ArrayList<>(answer).stream().sorted().collect(Collectors.toList());
    }
}
