package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// https://leetcode.com/problems/word-break-ii/
public class WordBreak2 {
    public static void main(String[] args) {
        WordBreak2 wordBreak2 = new WordBreak2();
        String s1 = "catsanddog";
        List<String> wordDict1 = List.of("cat", "cats", "and", "sand", "dog");
        List<String> answers = wordBreak2.wordBreak(s1, wordDict1);
        System.out.println(answers);
    }


    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordDict);
        find(s, s, wordSet, new StringBuilder(), result);
        return result;
    }

    public void find(String origin,
                     String now,
                     Set<String> wordSet,
                     StringBuilder builder,
                     List<String> result) {
        String tmp = builder.toString().replace(" ", "");
        if (tmp.equals(origin)) {
            builder.setLength(builder.length() - 1); // 마지막 공백 삭제
            result.add(builder.toString());
            builder.append(" ");
            return;
        }

        for (int len = 1; len <= now.length(); len++) {
            String substring = now.substring(0, len);
            if (wordSet.contains(substring)) {
                builder.append(substring).append(" ");
                find(origin, now.substring(len), wordSet, builder, result);
                builder.setLength(builder.length() - substring.length() - 1);
            }
        }
    }
}
