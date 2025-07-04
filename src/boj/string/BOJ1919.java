package boj.string;

import java.io.*;
import java.util.*;

public class BOJ1919 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Integer> charMap1 = new HashMap<>();
        String input = br.readLine();
        for(int i = 0; i < input.length(); i++) {
            char key = input.charAt(i);
            Integer keyCount = charMap1.getOrDefault(key, 0);
            charMap1.put(key, keyCount + 1);
        }
        Map<Character, Integer> charMap2 = new HashMap<>();
        input = br.readLine();
        for(int i = 0; i < input.length(); i++) {
            char key = input.charAt(i);
            Integer keyCount = charMap2.getOrDefault(key, 0);
            charMap2.put(key, keyCount + 1);
        }
        Set<Character> keySet = new HashSet<>(charMap1.keySet());
        keySet.addAll(charMap2.keySet());
        int answer = 0;
        for (Character key : keySet) {
            if(!charMap1.containsKey(key)){
                answer += charMap2.get(key);
                continue;
            }
            if(!charMap2.containsKey(key)){
                answer += charMap1.get(key);
                continue;
            }
            Integer count1 = charMap1.get(key);
            Integer count2 = charMap2.get(key);
            if(!count1.equals(count2)) {
                answer += Math.abs(count1 - count2);
            }
        }
        System.out.println(answer);
    }
}
