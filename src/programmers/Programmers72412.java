package programmers;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/72412
public class Programmers72412 {
    public static void main(String[] args) {
        Programmers72412 programmers72412 = new Programmers72412();
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        int[] solution = programmers72412.solution(info, query);
        System.out.println(Arrays.toString(solution));
    }

    Map<String, List<Integer>> scoreMap = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        List<Integer> answer = new ArrayList<>();

        // 1. 데이터 추가
        for (String row : info) {
            add(row);
        }

        // 2. 정렬 (이진 탐색 준비)
        postProcess();

        // 3. 쿼리 처리
        for(String tmp: query){
            String[] split = tmp.split(" and ");
            String language = split[0];
            String job = split[1];
            String level = split[2];
            String food = split[3].split(" ")[0];
            int score = Integer.parseInt(split[3].split(" ")[1]);

            int queryResult = query(language, job, level, food, score);
            answer.add(queryResult);
        }

        return answer.stream().mapToInt(i->i).toArray();
    }

    public void add(String row) {
        String[] split = row.split(" ");
        String language = split[0];
        String job = split[1];
        String level = split[2];
        String food = split[3];
        int score = Integer.parseInt(split[4]);

        // 가능한 모든 조합의 키를 생성 (16가지)
        String[] languages = {language, "-"};
        String[] jobs = {job, "-"};
        String[] levels = {level, "-"};
        String[] foods = {food, "-"};

        for (String l : languages) {
            for (String j : jobs) {
                for (String lv : levels) {
                    for (String f : foods) {
                        String key = l + "-" + j + "-" + lv + "-" + f;
                        scoreMap.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
                    }
                }
            }
        }
    }

    public void postProcess() {
        for (List<Integer> scores : scoreMap.values()) {
            Collections.sort(scores);
        }
    }

    public int query(String language, String job, String level, String food, int score) {
        String key = language + "-" + job + "-" + level + "-" + food;
        List<Integer> scores = scoreMap.get(key);

        if (scores == null) {
            return 0;
        }

        return countGreaterOrEqual(scores, score);
    }

    private int countGreaterOrEqual(List<Integer> scores, int target) {
        // 이진 탐색: target 이상인 첫 번째 위치 찾기
        int left = 0;
        int right = scores.size();

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return scores.size() - left;
    }
}
