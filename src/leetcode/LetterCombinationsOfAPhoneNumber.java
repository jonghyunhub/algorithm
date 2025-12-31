package leetcode;

import java.util.*;

// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        String digits = "23";
        LetterCombinationsOfAPhoneNumber letterCombinationsOfAPhoneNumber = new LetterCombinationsOfAPhoneNumber();
        List<String> strings = letterCombinationsOfAPhoneNumber.letterCombinations(digits);
        System.out.println(strings);
    }

    public List<String> letterCombinations(String digits) {
        Map<Integer, List<Character>> phoneMap = Map.of(
                2, List.of('a','b','c'),
                3, List.of('d','e','f'),
                4, List.of('g','h','i'),
                5, List.of('j','k','l'),
                6, List.of('m','n','o'),
                7, List.of('p','q','r','s'),
                8, List.of('t','u','v'),
                9, List.of('w','x','y','z')
        );
        List<String> result = new ArrayList<>();
        makeCombinations(result, new StringBuilder(),phoneMap, digits, 0);
        return result;
    }

    public void makeCombinations(
            List<String> result,
            StringBuilder builder,
            Map<Integer, List<Character>> phoneMap,
            String input,
            int now
    ){
        if(builder.length() >= input.length()){
            result.add(builder.toString());
            return;
        }
        for(int i=now; i<input.length(); i++){
            int nowPhoneNum = Integer.parseInt(input.substring(i,i+1));
            for(char c : phoneMap.get(nowPhoneNum)){
                builder.append(c);
                makeCombinations(result, builder, phoneMap, input, i+1);
                builder.setLength(builder.length()-1);
            }
        }
    }
}
