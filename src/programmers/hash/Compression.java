package programmers.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/17684
public class Compression {

    public static void main(String[] args) {
        String msg1 = "KAKAO";
        String msg2 = "TOBEORNOTTOBEORTOBEORNOT";
        String msg3 = "ABABABABABABABAB";

        Compression compression = new Compression();
        int[] solution = compression.solution(msg3);
        System.out.print("[");
        for (int i = 0; i < solution.length; i++) {
            System.out.print(solution[i]);
            if (i == solution.length - 1) break;
            System.out.print(", ");
        }
        System.out.println("]");
    }

    public int[] solution(String msg) {
        final List<Integer> answer = new ArrayList<>();
        final Map<String, Integer> dictionary = new HashMap<>();

        // 사전 초기화
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int dictionaryIdx = 1;
        for (char c : alphabet.toCharArray()) {
            String key = String.valueOf(c);
            dictionary.put(key, dictionaryIdx);
            dictionaryIdx++;
        }

        while (!msg.isEmpty()) {
            StringBuilder find = new StringBuilder();
            for (int findIdx = 0; findIdx < msg.length(); findIdx++) {
                find.append(msg.charAt(findIdx));
                String nowFind = find.toString();

                // 마지막 한글자 남은 경우에는 결과 배열에 담고 함수 종료
                if (msg.length() == 1) {
                    answer.add(dictionary.get(msg));
                    msg = "";
                    break;
                }

                // msg == nowFind 이고 현재 키 포함되는 경우에는 함수 종료
                if(msg.equals(nowFind) && dictionary.containsKey(nowFind)){
                    answer.add(dictionary.get(msg));
                    msg = "";
                    break;
                }

                // 현재 문자열 기준으로 다음 문자열이 포함되어 있는 않은 경우 현재 문자열의 색인을 결과배열에 담는다.
                String nextFind = nowFind + msg.charAt(findIdx + 1);
                if (!dictionary.containsKey(nextFind)) {
                    Integer idx = dictionary.get(nowFind);
                    answer.add(idx);

                    // w + c 를 다음 색인번호로 사전에 담는다.
                    dictionary.put(nextFind, dictionaryIdx++);

                    // msg 에서 w 만큼 뺀다. , w의 길이는 findIdx
                    msg = msg.substring(findIdx+1);
                    break;
                }
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
