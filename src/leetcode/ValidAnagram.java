package leetcode;

import java.util.*;

// https://leetcode.com/problems/valid-anagram/
public class ValidAnagram {
    public static void main(String[] args) {
        ValidAnagram validAnagram = new ValidAnagram();
        String s1 = "anagram";
        String t1 = "nagaram";

        String s2 = "rat";
        String t2 = "car";

        boolean anagram = validAnagram.isAnagram(s1, t1);
        System.out.println(anagram);
    }

    public boolean isAnagram(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for(int i = 0; i < t.length(); i++) {
            char now = t.charAt(i);
            Integer orDefault = map.getOrDefault(now, 0);
            orDefault--;
            if(orDefault < 0) return false;
            if(orDefault == 0){
                map.remove(now);
                continue;
            }
            map.put(now, orDefault);
        }

        return map.isEmpty();
    }
}
