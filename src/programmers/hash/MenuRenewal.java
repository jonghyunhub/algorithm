package programmers.hash;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/72411
public class MenuRenewal {

    public static void main(String[] args) {
        final String[] order1 = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        final int[] course1 = {2, 3, 4};

        final String[] order2 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        final int[] course2 = {2, 3, 5};

        final String[] order3 = {"XYZ", "XWY", "WXA"};
        final int[] course3 = {2, 3, 4};
        final MenuRenewal menuRenewal = new MenuRenewal();
        final String[] solution = menuRenewal.solution2(order3, course3);
        System.out.print("[");
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i]);
            if (i == solution.length - 1) break;
            System.out.print(", ");
        }
        System.out.println("]");
    }

    public String[] solution(String[] orders, int[] course) {
        final List<String> answer = new ArrayList<>();
        final Map<String, Integer> orderMap = new HashMap<>();

        // 메뉴를 순회하여 각 메뉴의 조합 메뉴를 생성
        for (String order : orders) {
            // order는 사전순이 아닐수도 있으므로 먼저 사전순으로 정렬
            char[] orderArr = order.toCharArray();
            Arrays.sort(orderArr);
            String sortedOrder = new String(orderArr);

            final List<String> orderCombinations = new ArrayList<>();
            // 현재 코스와 일치하는 길이의 조합을 생성한다.
            for (int courseNum : course) {
                int start = 0;
                recursiveCombination(orderCombinations, sortedOrder, new StringBuilder(), start, courseNum);
            }
            // 메뉴의 조합을 Map에 담는다.
            for (String orderCombination : orderCombinations) {
                final Integer orDefault = orderMap.getOrDefault(orderCombination, 0);
                orderMap.put(orderCombination, orDefault + 1);
            }
        }

        // 마지막 결과 생성
        // 각 코스 요리 갯수 중 제일 많이 나온걸 찾는다.
        for (int courseNum : course) {
            // 각 코스 요리의 나온 횟수 최댓값 찾기
            int maxCnt = 0;
            for (String orderCom : orderMap.keySet()) {
                // 문자열의 길이와 같지 않으면 스킵
                if (orderCom.length() != courseNum) continue;
                Integer orderCnt = orderMap.get(orderCom);
                if (maxCnt <= orderCnt) maxCnt = orderCnt;
            }

            // 최댓값과 일치하는 코스요리 찾기
            for (String orderCom : orderMap.keySet()) {
                // 문자열의 길이와 같지 않으면 스킵
                if (orderCom.length() != courseNum) continue;
                // 최댓값과 일치하지 않으면 스킵
                if (orderMap.get(orderCom) != maxCnt) continue;
                // 최댓값이 1 이하면 스킵
                if (maxCnt <= 1) continue;
                answer.add(orderCom);
            }
        }

        return answer.stream().sorted().toArray(String[]::new);
    }

    /**
     * combinations : 결과를 저장할 리스트
     * order : 원본 문자열
     * currentStr : 현재 부분 문자열
     * now : 현재 처리할 인덱스
     * size : 남은 처리할 부분 문자열 길이
     * 원본 문쟈열에서 size만큼의 now에서 시작하고 size 만큼에 길이만큼의 부분문자열을 결과 리스트에 담는다.
     */
    public void recursiveCombination(List<String> combinations, String order, StringBuilder currentStr, int now, int size) {
        /*
         * [종료조건]
         *  남은 길이가 0 일때
         *  인덱스(now + size)가 원본 문자열을 벗어난 경우 <- 이 경우는 감안할 필요 x order에 접근할때 for문 범위가 now~order.lenght 라서 애초에 못벗어남
         */
        if (size <= 0) {
            combinations.add(currentStr.toString());
            return;
        }

        // start부터 시작하여 남은 문자들 중에서 선택
        for (int i = now; i < order.length(); i++) {
            currentStr.append(order.charAt(i)); // 현재 문자 선택

            // 다음 문자 선택을 위한 재귀 호출
            recursiveCombination(combinations, order, currentStr, i + 1, size - 1);

            // 백트래킹: 현재 문자 선택 취소 (StringBuilder에서 마지막 문자 제거)
            currentStr.setLength(currentStr.length() - 1);
        }
    }

    public String[] solution2(String[] orders, int[] course) {
        final List<String> list = new ArrayList<>();
        for (String order : orders) {
            char[] charArray = order.toCharArray();
            Arrays.sort(charArray);
            list.add(new String(charArray));
        }
        return list.stream().toArray(String[]::new);
    }
}
