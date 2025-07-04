package boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ1157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Character, Integer> charMap = new HashMap<>();
        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            char c = convertToUpperCase(input.charAt(i));
            charMap.put(c, charMap.getOrDefault(c, 0) + 1);
        }

//        List<Map.Entry<Character, Integer>> list = charMap.entrySet()
//                .stream()
//                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
//                .collect(Collectors.toList());

//        if(list.size() > 1 && list.get(0).getValue().equals(list.get(1).getValue())) {
//            System.out.println('?');
//            return;
//        }
//        System.out.println(list.get(0).getKey());


        int max = 0;
        char maxKey = 0;
        for (char key : charMap.keySet()) {
            if (charMap.get(key) > max) {
                max = charMap.get(key);
                maxKey = key;
            }
            if (charMap.get(key) == max && maxKey != key) {
                maxKey = '?';
            }
        }

        System.out.println(maxKey);
        br.close();
    }

    public static char convertToUpperCase(char c) {
        if (c >= 'a' && c <= 'z') {
            return (char) (c - 'a' + 'A');
        }
        return c;
    }
}
