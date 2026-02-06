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

    static Map<String, Set<Integer>> languageIndex = new HashMap<>();
    static Map<String, Set<Integer>> jobIndex = new HashMap<>();
    static Map<String, Set<Integer>> levelIndex = new HashMap<>();
    static Map<String, Set<Integer>> foodIndex = new HashMap<>();
    static List<String> rows = new ArrayList<>();

    public int[] solution(String[] info, String[] query) {
        List<Integer> answer = new ArrayList<>();
        // info - 언어, 직군, 연차, 음식으로 이루어져 있고 해당 값으로 탐색 필요
        // idea : 각 데이터를 어떻게 저장하고 빠르게 검색할 것인가? => 각 쿼리 키워드 기준으로 rows의 index를 저장한 map(일종의 index) 를 통해 탐색
        for (String row : info) {
            add(row);
        }

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
        int rowId = rows.size();
        rows.add(row);

        String[] split = row.split(" ");
        String language = split[0];
        String job = split[1];
        String level = split[2];
        String food = split[3];

        languageIndex.computeIfAbsent(language, k -> new HashSet<>()).add(rowId);
        jobIndex.computeIfAbsent(job, k -> new HashSet<>()).add(rowId);
        levelIndex.computeIfAbsent(level, k -> new HashSet<>()).add(rowId);
        foodIndex.computeIfAbsent(food, k -> new HashSet<>()).add(rowId);
    }


    public int query(String language, String job, String level, String food, int score) {
        Set<Integer> result = null;

        if (!language.equals("-")) {
            result = new HashSet<>(languageIndex.getOrDefault(language, Set.of()));
        }
        if (!job.equals("-")) {
            Set<Integer> jobs = jobIndex.getOrDefault(job, Set.of());
            result = result == null ? new HashSet<>(jobs) : intersect(result, jobs);
        }
        if (!level.equals("-")) {
            Set<Integer> levelSet = levelIndex.getOrDefault(level, Set.of());
            result = result == null ? new HashSet<>(levelSet) : intersect(result, levelSet);
        }
        if (!food.equals("-")) {
            Set<Integer> foodSet = foodIndex.getOrDefault(food, Set.of());
            result = result == null ? new HashSet<>(foodSet) : intersect(result, foodSet);
        }

        if (result == null) {
            Set<Integer> tmp = new HashSet<>();
            for(int i=0; i<rows.size(); i++) tmp.add(i);
            result = tmp;
        }

        return (int) result
                .stream()
                .filter(i -> {
                    String origin = rows.get(i);
                    int targetScore = Integer.parseInt(origin.split(" ")[4]);
                    return targetScore >= score;
                }).count();
    }

    private Set<Integer> intersect(Set<Integer> set1, Set<Integer> set2) {
        set1.retainAll(set2);
        return set1;
    }
}
