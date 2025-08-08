package programmers;

import java.util.*;

public class Programmers120956 {
    public static void main(String[] args) {
        String[] babbling = {"ayaye", "uuuma", "ye", "yemawoo", "ayaa"};
        int solution = new Programmers120956().solution(babbling);
        System.out.println(solution);
    }

    public int solution(String[] babbling) {
        int answer = 0;
        List<String> babblingList = List.of("aya", "ye", "woo", "ma");
        Set<String> babblingSet = new HashSet<>();
        boolean[] visited = new boolean[babblingList.size()];

        for(int size = 1; size<= babblingList.size(); size++)
            makePermutations(babblingList, babblingSet, new ArrayList<>(), visited, size);

        System.out.println(babblingSet);


        for(String item : babbling){
            if(babblingSet.contains(item)) answer++;
        }

        return answer;
    }


    public void makePermutations(List<String> babblingList,
                                 Set<String> babblingSet,
                                 List<String> tmp,
                                 boolean[] visited,
                                 int size){
        if(tmp.size() >= size){
            StringBuilder builder = new StringBuilder();
            for(String item : tmp){
                builder.append(item);
            }
            babblingSet.add(builder.toString());
            return;
        }

        for(int i=0; i<babblingList.size(); i++){
            if(visited[i]) continue;
            tmp.add(babblingList.get(i));
            visited[i] = true;
            makePermutations(babblingList,babblingSet,tmp,visited, size);
            visited[i] = false;
            tmp.remove(tmp.size()-1);
        }

    }
}
