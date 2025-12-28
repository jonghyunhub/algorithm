package programmers;

import java.util.HashMap;
import java.util.Map;

// https://school.programmers.co.kr/learn/courses/30/lessons/258712
// 이 문제의 교훈 : 선물 주고받는 횟수 따지기 위해 {주는 사람 -> 받는 사람}, {받는 사람 -> 주는 사람} 형식의 map을 두개 만들필요없이   {주는 사람 -> 받는 사람} 하나만 있어도 됨
public class Programmers258712 {
    public static void main(String[] args) {
        Programmers258712 programmers258712 = new Programmers258712();
        String[] friends1 = {"muzi", "ryan", "frodo", "neo"};
        String[] gifts1 = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};
        String[] friends2 = {"joy", "brad", "alessandro", "conan", "david"};
        String[] gifts2 = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};
        String[] friends3 = {"a", "b", "c"};
        String[] gifts3 = {"a b", "b a", "c a", "a c", "a c", "c a"};
        programmers258712.solution(friends1, gifts1);
    }

    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        Map<String, Map<String, Integer>> giftsSentTo = new HashMap<>();
        for(String gift : gifts){
            String[] split = gift.split(" ");
            String sentMan = split[0];
            String receiveMan = split[1];
            Map<String, Integer> sentMap = giftsSentTo.computeIfAbsent(sentMan, k -> new HashMap<>());
            sentMap.put(receiveMan, sentMap.getOrDefault(receiveMan, 0) + 1);
        }
        // 선물 지수 계산
        Map<String ,Integer> giftScores = new HashMap<>();
        for(String me : friends){
            int giftScore = 0;
            // 준 선물 갯수 카운트 +
            for(int sentCnt : giftsSentTo.getOrDefault(me, new HashMap<>()).values()){
                giftScore += sentCnt;
            }
            // 받은 선물 갯수 카운트 -
            for(String friend : friends){
                if(me.equals(friend)) continue;
                giftScore -= giftsSentTo.getOrDefault(friend, new HashMap<>()).getOrDefault(me, 0);
            }
            giftScores.put(me, giftScore);
        }

        // 선물 갯수 카운트
        for(String me : friends){
            int giftCnt = 0;
            for(String friend : friends){
                if(me.equals(friend)) continue;
                int meToFriend = giftsSentTo.getOrDefault(me, new HashMap<>()).getOrDefault(friend, 0);
                int friendToMe = giftsSentTo.getOrDefault(friend, new HashMap<>()).getOrDefault(me, 0);
                if(meToFriend > friendToMe){
                    giftCnt++;
                    continue;
                }
                if(meToFriend == friendToMe || (meToFriend == 0 && friendToMe ==0)){
                    if(giftScores.get(me) > giftScores.get(friend)){
                        giftCnt++;
                    }
                }
            }
            answer = Math.max(answer, giftCnt);
        }
        return answer;
    }
}
