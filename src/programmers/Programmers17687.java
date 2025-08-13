package programmers;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/17687
public class Programmers17687 {
    public static void main(String[] args) {
        Programmers17687 programmers17687 = new Programmers17687();
        int n= 2;
        int t = 4;
        int m = 2;
        int p = 1;
        String solution = programmers17687.solution(n, t, m, p);
        System.out.println(solution);
    }


    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        Queue<Character> queue = new ArrayDeque<>();
        int originNum = 0;
        int countTurn = 0;
        while(true){
            if(answer.length() >= t) break;
            if(queue.isEmpty()){
                String formationNum = makeFormationNum(originNum, n);
                for(int i=formationNum.length()-1; i>=0; i--){
                    queue.add(formationNum.charAt(i));
                }
                originNum++;
            }
            char nextNum = queue.poll();
            if((countTurn % m) +1 == p){
                answer.append(nextNum);
            }
            countTurn++;
        }
        return answer.toString();
    }


    public String makeFormationNum(int originNum, int formation){
        StringBuilder builder = new StringBuilder();
        if(originNum == 0) return "0";
        while(originNum > 0){
            int i = originNum % formation;
            if(i>=10){
                int c = 'A' + (i - 10);
                builder.append((char)c);
                originNum /= formation;
                continue;
            }
            builder.append(i);
            originNum /= formation;
        }
        return builder.toString();
    }
}
