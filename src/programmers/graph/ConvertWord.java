package programmers.graph;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/43163 : 단어변환
public class ConvertWord {
    public static void main(String[] args) {
        ConvertWord convertWord = new ConvertWord();
        String begin1 = "hit";
        String end1 = "cog";
        String[] words1 = {"hot", "dot", "dog", "lot", "log", "cog"};
        int solution = convertWord.solution(begin1, end1, words1);
        System.out.println(solution);
    }

    /**
     [문제 요구사항 분석]
     - begin -> target으로 변환
     - 단, 변환시 words에 있는 단어로만 변환가능
     - 변환 한번에 단어 내부 알파벳 하나씩 변경 가능
     1. words -> 각 단어를 연관된 MAP 형태의 그래프로 만든다.
     2. 1에서 만든 그래프를 begin에서 시작하여 target으로 도착하는 최단 거리를 구한다 -> (그래프 가중치가 없으므로 BFS)
     */
    public int solution(String begin, String target, String[] words) {
        // 단어 그래프 초기화
        final Map<String, List<String>> graph = new HashMap<>();
        for(int i=0; i<words.length; i++){
            final String word = words[i];
            final List<String> list = new ArrayList<>();
            for(int j=0; j<words.length; j++){
                if(i==j) continue;
                String otherWord = words[j];
                int sameAlpha = 0;
                for(int l=0; l<word.length(); l++){
                    if(word.charAt(l) == otherWord.charAt(l)) sameAlpha++;
                }
                // 알파벳 한개만 다르면 해당 단어 리스트에 추가
                if(word.length() -1 == sameAlpha){
                    list.add(otherWord);
                }
            }
            graph.put(word, list);
        }
        // 시작점 그래프에 초기화
        final List<String> list = new ArrayList<>();
        for(int j=0; j<words.length; j++){
            String otherWord = words[j];
            int sameAlpha = 0;
            for(int l=0; l<begin.length(); l++){
                if(begin.charAt(l) == otherWord.charAt(l)) sameAlpha++;
            }
            // 알파벳 한개만 다르면 해당 단어 리스트에 추가
            if(begin.length() -1 == sameAlpha){
                list.add(otherWord);
            }
        }
        graph.put(begin, list);

        // 최단 경로 찾기
        return bfs(begin, target, graph);
    }

    public int bfs(String begin, String target, Map<String, List<String>> graph){
        int answer = 0;
        final Queue<String> queue = new ArrayDeque<>();
        final Map<String, Boolean> visited = new HashMap<>();
        final Map<String, Integer> dists = new HashMap<>();

        for(String key : graph.keySet()){
            visited.put(key, false);
        }
        queue.add(begin);
        visited.put(begin, true);
        dists.put(begin, 0);

        while(!queue.isEmpty()){
            String now = queue.poll();
            if(now.equals(target)){
                answer = dists.get(now);
                break;
            }

            for(String next : graph.getOrDefault(now, new ArrayList<>())){
                if(visited.get(next)) continue;
                visited.put(next, true);
                dists.put(next, dists.get(now) + 1);
                queue.add(next);
            }
        }
        return answer;
    }
}
