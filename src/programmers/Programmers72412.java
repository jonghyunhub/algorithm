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

    public int[] solution(String[] info, String[] query) {
        List<Integer> answer = new ArrayList<>();
        Map<String, List<Integer>> scores = new HashMap<>();
        String[] languages = {"cpp", "java", "python", "-"};
        String[] jobs = {"backend", "frontend", "-"};
        String[] levels = {"junior", "senior", "-"};
        String[] foods = {"chicken", "pizza", "-"};

        // info 순회하면서 데이터 적재
        for (String row : info) {
            String[] split = row.split(" ");
            String language = split[0];
            String job = split[1];
            String level = split[2];
            String food = split[3];
            int score = Integer.parseInt(split[4]);
            // 모든 하위 조합 키에 맞춰서 해당 키를 추가
            for (String l : languages) {
                if (!l.equals("-") && !l.equals(language)) continue;
                for (String j : jobs) {
                    if (!j.equals("-") && !j.equals(job)) continue;
                    for (String le : levels) {
                        if (!le.equals("-") && !le.equals(level)) continue;
                        for (String f : foods) {
                            if (!f.equals("-") && !f.equals(food)) continue;
                            StringBuilder key = new StringBuilder();
                            key.append(l);
                            key.append(" ");
                            key.append(j);
                            key.append(" ");
                            key.append(le);
                            key.append(" ");
                            key.append(f);
                            List<Integer> scoreList = scores.computeIfAbsent(key.toString(), k -> new ArrayList());
                            scoreList.add(score);
                        }
                    }
                }
            }
        }

        for (List<Integer> scoreList : scores.values()) {
            Collections.sort(scoreList);
        }

        // query 순회하면서 조건에 맞는결과 리턴
        for (String row : query) {
            String[] split = row.split(" and ");
            String food = split[3].split(" ")[0];
            int score = Integer.parseInt(split[3].split(" ")[1]);
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                key.append(split[i]);
                key.append(" ");
            }
            key.append(food);

            // 오름차순 정렬되어있음
            List<Integer> scoreList = scores.getOrDefault(key.toString(), new ArrayList<>());
            answer.add(countScore(scoreList, score));
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    public int countScore(List<Integer> scoreList, int score) {
        if(scoreList.isEmpty()) return 0;
        int left = 0;
        int right = scoreList.size();
        int mid = (left + right) / 2;
        // [50, 80, 150, 150, 210, 260]에서 150 이상을 찾는다면 => 150이 처음 등장하는 인덱스 2를 찾아야함
        while (left < right) {
            if (scoreList.get(mid) < score) {
                left = mid + 1;
                mid = (left + right) / 2;
                continue;
            }

            right = mid;
            mid = (left + right) / 2;
        }

        if (left == 0 && scoreList.get(left) < score) {
            return 0;
        }

        // 0,1,2,3,4
        // 5개중 3번부터 만족 => 5 - 3
        return scoreList.size() - left;
    }
}
