package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/42890
public class Programmers42890 {
    public static void main(String[] args) {
        Programmers42890 programmers42890 = new Programmers42890();
        String[][] relation = {
                {"100", "ryan", "music", "2"},
                {"200", "apeach", "math", "2"},
                {"300", "tube", "computer", "3"},
                {"400", "con", "computer", "4"},
                {"500", "muzi", "music", "3"},
                {"600", "apeach", "music", "2"}
        };
        int solution = programmers42890.solution(relation);
        System.out.println(solution);
    }

    // 후보키 : 각 튜플을 구분할수있는 속성
    public int solution(String[][] relation) {
        List<List<Integer>> answer = new ArrayList<>();
        List<List<Integer>> candidateKeyList = new ArrayList<>();
        boolean[] visited = new boolean[relation[0].length];
        for (int size = 1; size <= relation[0].length; size++) {
            makeCombinations(candidateKeyList, new ArrayList<>(), visited, 0, size);
        }
        for (List<Integer> nowKey : candidateKeyList) {
            // 유일성 만족 + 최소성 만족
            if (canMakeUnique(nowKey, relation)) {
                // 유일성 만족하면 기존에 존재하는 후보키가 해당 키에 포함되는게 있으면 X
                boolean isMinimum = true;
                for (List<Integer> otherKey : answer) {
                    if(ifContains(otherKey, nowKey)) isMinimum = false;
                }
                if(isMinimum) answer.add(nowKey);
            }
        }

        return answer.size();
    }

    // relation[i] 의 각 속성 인덱스를 기준으로 후보키 생성
    public void makeCombinations(List<List<Integer>> result,
                                 List<Integer> tmp,
                                 boolean[] visited,
                                 int now,
                                 int size) {
        if (tmp.size() >= size) {
            result.add(new ArrayList<>(tmp));
            return;
        }

        for (int i = now; i < visited.length; i++) {
            if(visited[i]) continue;
            tmp.add(i);
            visited[i] = true;
            makeCombinations(result, tmp, visited, i, size);
            visited[i] = false;
            tmp.remove(tmp.size() - 1);
        }
    }

    /**
     * 유일성 만족하는지 체크
     */
    public boolean canMakeUnique(List<Integer> candidateKey, String[][] relation) {
        Set<String> keySet = new HashSet<>();
        for (String[] tuple : relation) {
            StringBuilder builder = new StringBuilder();
            for (int key : candidateKey) {
                builder.append(tuple[key]).append("|");
            }
            String tupleStr = builder.toString();
            if (keySet.contains(tupleStr)) return false;
            keySet.add(tupleStr);
        }
        return true;
    }

    /**
     * 기존에 존재하는 후보키가 해당 키에 포함되는게 있으면 X
     */
    public boolean ifContains(List<Integer> otherKey, List<Integer> now) {
        if (otherKey.size() > now.size()) {
            return false;
        }
        Set<Integer> keySet = new HashSet<>(now);
        boolean isContains = true;
        for (int key : otherKey) {
            isContains = keySet.contains(key) && isContains;
        }
        return isContains;
    }
}
