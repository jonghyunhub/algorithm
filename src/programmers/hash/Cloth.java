package programmers.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/42578
public class Cloth {

    public static void main(String[] args) {
        Cloth cloth = new Cloth();
        String[][] cloth1 = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        String[][] cloth2 = {{"crow_mask", "face"}, {"blue_sunglasses", "face"}, {"smoky_makeup", "face"}};
        String[][] cloth3 = {{"a", "A"},{"b", "B"},{"c", "C"},{"d", "D"},{"e", "E"},{"f", "F"},{"g", "G"},
                {"h", "H"},{"i", "I"},{"j", "J"},{"k", "K"},{"l", "L"},{"m", "M"},{"n", "N"},{"o", "O"},{"p", "P"},
                {"q", "Q"},{"r", "R"},{"s", "S"},{"t", "T"},{"u", "U"},{"v", "V"},{"w", "W"},{"x", "X"},{"y", "Y"},
                {"z", "Z"},{"ab", "AB"},{"ac", "AC"},{"ad", "AD"},{"ae", "AE"}};
        int solution = cloth.solution(cloth3);
        System.out.println("solution = " + solution);
    }


    public int solution(String[][] clothes) {
        // 옷의 종류별 개수만 저장하는 Map으로 단순화
        Map<String, Integer> clothCountMap = new HashMap<>();

        // 옷의 종류별 개수 계산
        for (String[] cloth : clothes) {
            String kind = cloth[1];
            clothCountMap.put(kind, clothCountMap.getOrDefault(kind, 0) + 1);
        }

        // 정답 계산: (각 종류별 옷의 개수 + 1)을 모두 곱한 후 1을 뺌
        // +1을 하는 이유: 해당 종류의 옷을 선택하지 않는 경우를 포함
        // 마지막에 -1을 하는 이유: 아무것도 선택하지 않는 경우를 제외
        int answer = 1;
        for (int count : clothCountMap.values()) {
            answer *= (count + 1);
        }

        return answer - 1;
    }


    public int solutionWithRecursive(String[][] clothes) {
        final List<List<String>> answer = new ArrayList<>();
        // <옷의 종류, 옷 리스트> 해시
        final Map<String, List<String>> clothMap = new HashMap<>();
        // 옷의 종류 가능한 조합을 저장할 리스트
        final List<List<String>> kindCombinations = new ArrayList<>();


        // 주어진 2차원 배열을 Map<옷의 종류, 옷의 리스트> 해시로 변환
        for (String[] cloth : clothes) {
            final String name = cloth[0];
            final String kind = cloth[1];
            final List<String> clothList = clothMap.getOrDefault(kind, new ArrayList<>());
            clothList.add(name);
            clothMap.put(kind, clothList);
        }

        // 가능한 옷 종류의 조합을 만든다.
        final List<String> kinds = new ArrayList<>(clothMap.keySet());
        // 길이가 0인 것은 제외
        for (int size = 1; size <= kinds.size(); size++) {
            makeSubList(kindCombinations, kinds, new ArrayList<>(), 0, size);
        }

        // 생성한 옷의 조합에서 포함되는 종류의 옷을 한개씩 고른다.
        for (List<String> combination : kindCombinations) {
            makeClothCombinations(answer, clothMap, combination, new ArrayList<>(), 0);
        }

        return answer.size();
    }

    // size의 길이만큼의 부분 문자열 생성 재귀
    public void makeSubList(final List<List<String>> result,
                            final List<String> original,
                            final List<String> subList,
                            int now, int size) {
        if (size <= 0) {
            // 복사본 추가, subList를 그대로 담으면 subList의 주소 한개만 담김
            result.add(new ArrayList<>(subList));
            return;
        }

        for (int i = now; i < original.size(); i++) {
            subList.add(original.get(i));
            makeSubList(result, original, subList, i + 1, size - 1);
            subList.remove(subList.size() - 1); // 백트래킹
        }
    }

    /**
     * 옷 종류의 조합에 맞게 옷 리스트 생성하는 재귀함수
     * @param result : 최종 결과 담을 리스트
     * @param clothMap : <옷의 종류, 옷들> 담긴 HashMap
     * @param kindCombinations : 현재 선택된 옷의 종류의 조합
     * @param current : 현재 담은 옷은 리스트
     * @param depth : 탐색하는 옷의 종류의 깊이
     */
    public void makeClothCombinations(final List<List<String>> result,
                                      final Map<String, List<String>> clothMap,
                                      final List<String> kindCombinations,
                                      final List<String> current,
                                      int depth) {
        // 모든 리스트에서 하나씩 선택하면 재귀 종료
        if (depth == kindCombinations.size()) {
            result.add(new ArrayList<>(current)); // 복사본 저장해야함
            return;
        }

        String kind = kindCombinations.get(depth);
        List<String> clothesByKind = clothMap.get(kind);
        for (String cloth : clothesByKind) {
            current.add(cloth);
            makeClothCombinations(result, clothMap, kindCombinations, current, depth + 1);
            current.remove(current.size() - 1); // 백트래킹
        }
    }
}
