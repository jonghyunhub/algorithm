package programmers.graph;

import java.util.*;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/43164 - 여행경로 문제
public class TravelCourse {
    public static void main(String[] args) {
        TravelCourse travelCourse = new TravelCourse();
        String[][] tickets1 = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        String[][] tickets3 = {{"ICN", "BBB"}, {"BBB", "ICN"}, {"ICN", "AAA"}}; // 반례 1
        String[][] tickets4 = {{"ICN", "AAA"},
                {"ICN", "BBB"},
                {"ICN", "CCC"},
                {"CCC", "ICN"},
                {"BBB", "ICN"}
        }; // 반례 2
        String[][] tickets5 = {
                {"EZE", "TIA"},
                {"EZE", "HBA"},
                {"AXA", "TIA"},
                {"ICN", "AXA"},
                {"ANU", "ICN"},
                {"ADL", "ANU"},
                {"TIA", "AUA"},
                {"ANU", "AUA"},
                {"ADL", "EZE"},
                {"ADL", "EZE"},
                {"EZE", "ADL"},
                {"AXA", "EZE"},
                {"AUA", "AXA"},
                {"ICN", "AXA"},
                {"AXA", "AUA"},
                {"AUA", "ADL"},
                {"ANU", "EZE"},
                {"TIA", "ADL"},
                {"EZE", "ANU"},
                {"AUA", "ANU"}
        }; // 반례 3
        String[] solution = travelCourse.solution(tickets5);
        for (String s : solution) {
            System.out.println(s);
        }
    }

    public String[] solution(String[][] tickets) {
        final List<String> answer = new ArrayList<>();
        final Map<String, List<String>> graph = new HashMap<>();
        for (String[] item : tickets) {
            final String from = item[0];
            final String to = item[1];
            final List<String> toList = graph.getOrDefault(from, new ArrayList<>());
            toList.add(to);
            graph.put(from, toList);
        }
        // 모든 노드 카운트
        final Set<String> keys = graph.keySet();
        for (String key : keys) {
            final List<String> toList = graph.get(key);
            List<String> sorted = toList.stream()
                    .sorted()
                    .collect(Collectors.toList());//  오름차순 정렬
            graph.put(key, sorted); // 정렬된 리스트로 덮어쓰기
        }

        final List<List<String>> remainTicket = new ArrayList<>(); // 방문 해야 하는 경로
        for (String[] item : tickets) {
            List<String> newTicket = new ArrayList<>();
            newTicket.add(item[0]);
            newTicket.add(item[1]);
            remainTicket.add(newTicket);
        }
        answer.add("ICN");
        dfs("ICN", graph, answer, remainTicket);
        return answer.stream().toArray(String[]::new);
    }

    // 티켓 되돌리기 위해서 재귀함수 기반의 dfs 필요
    public void dfs(String now,
                    Map<String, List<String>> graph,
                    List<String> answer,
                    List<List<String>> remainTicket) {
        if (remainTicket.isEmpty()) { // 남은 티켓 없으면 종료
            return;
        }

        for (String next : graph.getOrDefault(now, new ArrayList<>())) {
            final List<String> ticket = new ArrayList<>();
            ticket.add(now);
            ticket.add(next);
            if (!remainTicket.contains(ticket)) continue; // 이미 방문한 경우 skip
            remainTicket.remove(ticket); // 방문처리
            answer.add(next);
            dfs(next, graph, answer, remainTicket);
            if (remainTicket.isEmpty()) { // 모든 티켓을 모두 사용했으면 종료
                return;
            }
            remainTicket.add(ticket); // 백트래킹
            answer.remove(answer.size() - 1);
        }
    }
}
