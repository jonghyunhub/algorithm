package programmers.hash;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42576
public class UnfinishedRunner {

    public static void main(String[] args) {
        String[] participant1 = {"leo", "kiki", "eden"};
        String[] completion1 = {"eden", "kiki"};

        String[] participant2 = {"marina", "josipa", "nikola", "vinko", "filipa"};
        String[] completion2 = {"josipa", "filipa", "marina", "nikola"};

        String[] participant3 = {"mislav", "stanko", "mislav", "ana"};
        String[] completion3 = {"stanko", "ana", "mislav"};

        String[] participant4 = {"ana", "ana", "ana", "ana"};
        String[] completion4 = {"ana", "ana", "ana"};

        String[] participant5 = {"ana"};
        String[] completion5 = {};

        String[] participant6 = {"a", "a", "b", "b", "c"};
        String[] completion6 = {"a", "a", "b", "c"};

        UnfinishedRunner unfinishedRunner = new UnfinishedRunner();
        String solution = unfinishedRunner.solution(participant6, completion6);
        System.out.println(solution);

    }

    public String solution(String[] participant, String[] completion) {
        final Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < completion.length; i++) {
            if (map.containsKey(completion[i])) {
                map.get(completion[i]).add(completion[i]);
                continue;
            }
            map.put(completion[i], new ArrayList<>(Arrays.asList(completion[i])));
        }

        for (String now : participant) {
            // keys에 해당하는 완주자 이름이 없는 경우
            if (!map.containsKey(now)) {
                return now;
            }
            // 중복되는 경우 keys에 해당하는 완주자 이름이 있으나, 리스트에 값이 있는지 여부를 확인해야함
            List<String> values = map.get(now);
            if (values.isEmpty()) {
                return now;
            }
            values.remove(values.size() - 1);
        }

        return "";
    }
}
