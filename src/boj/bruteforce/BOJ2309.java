package boj.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ2309 {

    static boolean findAnswer = false;
    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> dwarfHeights = new ArrayList<>();
        List<Integer> find = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null && !(line.isEmpty())) {
            line = line.trim();
            if (line.isEmpty()) continue;
            dwarfHeights.add(Integer.parseInt(line));
        }
        boolean[] visited = new boolean[dwarfHeights.size()];
        findDwarfs(dwarfHeights, 0, visited, find);
        List<Integer> sortedFind = answer.stream().sorted((a, b) -> a - b).collect(Collectors.toList());
        for (Integer i : sortedFind) {
            System.out.println(i);
        }
    }

    public static void findDwarfs(List<Integer> dwarfHeights,
                                  int nowIdx,
                                  boolean[] visited,
                                  List<Integer> find) {
        if (findAnswer) return;

        if (find.size() == 7) {
            int sum = calSumHeights(find);
            if (sum == 100) {
                findAnswer = true;
                answer.addAll(find);
                return;
            }
        }

        if (nowIdx >= dwarfHeights.size()) return;
        for (int i = nowIdx; i < dwarfHeights.size(); i++) {
            if (visited[i]) return;
            Integer height = dwarfHeights.get(i);
            find.add(height);
            visited[i] = true;

            findDwarfs(dwarfHeights, i + 1, visited, find);

            visited[i] = false;
            find.remove(find.size() - 1);
        }
    }

    public static int calSumHeights(List<Integer> find) {
        return find.stream().mapToInt(i -> i).sum();
    }
}
