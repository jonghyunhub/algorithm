package programmers;

import java.util.*;

public class Programmers64064 {
    public static void main(String[] args) {
        Programmers64064 solution = new Programmers64064();
        String[] user_id1 = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id1 = {"fr*d*", "abc1**"};

        String[] user_id2 = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] banned_id2 = {"*rodo", "*rodo", "******"};
        int answer = solution.solution(user_id2, banned_id2);
        System.out.println(answer);
    }

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        // 1. 불량 사용자별로 가능한 유저 아이디를 찾아서 Map에 저장
        // 2. 1을 순회하면서 가능한 조합 생성
        // 3. 2에서 생성한 조합의 리스트와 원본 userId 리스트 비교해서 가능한 리스트인지 판단
        Map<String, List<String>> candidateMap = new HashMap<>();
        for (String bannedId : banned_id) {
            List<String> candidates = candidateMap.computeIfAbsent(bannedId, k -> new ArrayList<>());
            for (String userId : user_id) {
                if (canMake(userId, bannedId)) candidates.add(userId);
            }
        }

        Set<Set<String>> combinations = new HashSet<>();
        // 이거 왜 순서 다르게 되지?
        makeCombinations(combinations, new HashSet<>(), candidateMap, banned_id, 0);

        for (Set<String> comb : combinations) {
            List<String> copy = new ArrayList<>(Arrays.asList(user_id));
            boolean canMake = true;
            for (String candidate : comb) {
                if (!copy.contains(candidate)) {
                    canMake = false;
                    break;
                }
                copy.remove(candidate);
            }
            if (canMake) answer++;
        }

        return answer;
    }

    public boolean canMake(String userId, String bannedId) {
        if (userId.length() != bannedId.length()) return false;
        for (int i = 0; i < userId.length(); i++) {
            char c1 = userId.charAt(i);
            char c2 = bannedId.charAt(i);
            if (c2 != '*' && c1 != c2) return false;
        }
        return true;
    }

    public void makeCombinations(
            Set<Set<String>> result,
            Set<String> tmp,
            Map<String, List<String>> candidateMap,
            String[] bannedIds,
            int index) {

        if (index == bannedIds.length) {
            result.add(new HashSet<>(tmp));
            return;
        }

        // 현재 index의 bannedId에 대해서만 처리
        for (String candidate : candidateMap.get(bannedIds[index])) {
            if (tmp.contains(candidate)) continue;  // 이미 선택된 userId는 스킵

            tmp.add(candidate);
            makeCombinations(result, tmp, candidateMap, bannedIds, index + 1);
            tmp.remove(candidate);
        }
    }
}
