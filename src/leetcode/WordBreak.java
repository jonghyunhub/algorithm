package leetcode;

import java.util.*;
import java.util.stream.Collectors;

// https://leetcode.com/problems/word-break
public class WordBreak {
    public static void main(String[] args) {
        String s1 = "leetcode";
        List<String> wordDict1 = List.of("leet","code");

        String s2 = "cars";
        List<String> wordDict2 = List.of("car","ca","rs");

        String s3 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict3 = List.of("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa");
        WordBreak wordBreak = new WordBreak();
        boolean answer = wordBreak.wordBreakDp(s1, wordDict1);
        System.out.println(answer);
    }

    // 순수 재귀 방식 - 시간복잡도 : O(2^n) (n은 s의 길이)
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s.isEmpty()) return true;
        for(String word : wordDict){
            int length = word.length();
            if(length > s.length()) continue;
            String sub = s.substring(0, length);
            if(sub.equals(word)){
                if(wordBreak(s.substring(length), wordDict)) return true;
            }
        }
        return false;
    }


    // 메모이제이션 활용해서 최적화한 방식
    // 최적 부분 구조 - 큰 문제의 답이 작은 문제의 답으로 구성됨
    // wordBreak("leetcode") = wordBreak("leet") + wordBreak("code")
    public boolean wordBreakDp(String s, List<String> wordDict) {
        // dp[i] : s.substring(0,i) 가 wordDict로 처리할 수 있는지 여부
        // dp[i] = true 이면 s.substring(i+1, len) 까지만 찾아서 가능하면 됨
        boolean[] dp = new boolean[s.length() + 1];
        Set<String> wordSet = new HashSet<>(wordDict);
        dp[0] = true; // 빈 문자열은 true(초기값)

        // 점화식(Bottom-up) :  dp[len] : dp[i] && wordSet.contains(s.substring(i, len))
        for(int len=1; len<=s.length(); len++){
            for(int i=0; i<len; i++){
                if(dp[i] && wordSet.contains(s.substring(i,len))){
                    dp[len] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
