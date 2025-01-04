package programmers.hash;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/92334
public class GetTheReportResult {

    public static void main(String[] args) {
        String[] id_list1 = {
                "muzi", "frodo", "apeach", "neo"
        };
        String[] report1 = {
                "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"
        };
        int k1 = 2;

        String[] id_list2 = {
                "con", "ryan"
        };
        String[] report2 = {
                "ryan con", "ryan con", "ryan con", "ryan con"
        };
        int k2 = 3;

        GetTheReportResult getTheReportResult = new GetTheReportResult();
        int[] solution = getTheReportResult.solution(id_list1, report1, k1);
        System.out.print("[");
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i]);
            if (i == solution.length - 1) break;
            System.out.print(", ");
        }
        System.out.println("]");
    }

    public int[] solution(String[] id_list, String[] report, int k) {
        // Map<신고당한 유저의 아이디, Set<신고한 유저의 아이디>>
        final Map<String, Set<String>> reportLogs = new HashMap<>();
        // 정지 여부를 나타내는 Map<신고당한 유저의 아이디, 정지 여부>
        final Map<String, Boolean> accountSuspendMap = new HashMap<>();
        // Map<신고한 유저, 메일의 갯수>
        final Map<String, Integer> mailMap = new HashMap<>();

        // 1. report 배열을 순회하면서 신고 로그를 집계한다.
        for(String log : report){
            String[] split = log.split(" ");
            String reportFromUser = split[0]; // 신고한 유저
            String reportToUser = split[1]; // 신고 당한 유저
            Set<String> reportSet = reportLogs.getOrDefault(reportToUser, new HashSet<>());
            reportSet.add(reportFromUser);
            reportLogs.put(reportToUser, reportSet);
        }

        // 2. 신고 당한 유저의 Map에서 몇명의 유저들에게 신고들 당했는지 여부로 이 유저가 정지되는지 여부를 판단.
        for(String reportToUser : reportLogs.keySet()){
            Set<String> reportSet = reportLogs.get(reportToUser);
            if(k > reportSet.size()){ // 정지 기준 갯수보다 작으면 정지 x
                accountSuspendMap.put(reportToUser, false);
                continue;
            }
            accountSuspendMap.put(reportToUser, true); // 정지 갯수보다 크거나 같으면 정지처리
        }

        // 3. 각 유저가 받을 메일의 갯수를 집계한다.
        for(String reportToUser : accountSuspendMap.keySet()){
            // 정지가 아니라면 skip
            if(!accountSuspendMap.get(reportToUser)) continue;
            Set<String> reportSet = reportLogs.get(reportToUser);
            // 신고한 유저의 Set을 순회하면서 각 유저가 받을 메일 갯수를 1증가시킨다.
            for(String reportFromUser : reportSet){
                Integer mailCount = mailMap.getOrDefault(reportFromUser, 0);
                mailMap.put(reportFromUser, mailCount+1);
            }
        }

        // 4. id_list의 순서대로 3의 Map 에서 id → int의 배열로 변경한다.
        return Arrays.asList(id_list).stream()
                .map(id -> mailMap.getOrDefault(id,0))
                .mapToInt(i -> i)
                .toArray();
    }
}
