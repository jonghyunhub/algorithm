package leetcode;

// https://leetcode.com/problems/sum-of-beauty-of-all-substrings/description/
public class LeetCode1781 {

    public static void main(String[] args) {
        String s1 = "aabcb";
        String s2 = "woqrqcvfdgkrafoqdktsfpeygawfpdlvaylgpxhufpvucmmztjoqmxhegdpeczbtvwrmnwrvlptscwwqbjstanyqbgoagxopvgtlyzsemgktcgciualltsquepotmtszbmejbwbtzlavpxnujdsdyrypfcfcfwdidglybzvzuznytwndidzumoekzuukxtpouudsfcohapfcjjmqwjgcvalzarugmzucheydwsncxgyojnfvgroihfckmbtqewxhuqihplprgyeaqhocivaupdfokwpliziwcmuxnebxeszxbsrmffwwdz";
        LeetCode1781 leetCode1781 = new LeetCode1781();
        int i = leetCode1781.beautySum(s1);
        System.out.println("i = " + i);
    }

    public int beautySum(String s) {
        int answer = 0;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            int[] freq = new int[26];  // 빈도수 배열
            builder.setLength(0);      // StringBuilder 초기화

            for (int j = i; j < s.length(); j++) {
                char currentChar = s.charAt(j);
                freq[currentChar - 'a']++;  // 빈도수 갱신
                builder.append(currentChar); // 현재 문자 추가

                if (j - i + 1 >= 2) {  // 길이가 2 이상인 경우만 처리
                    int max = 0, min = s.length();
                    for (int f : freq) {
                        if (f > 0) {
                            max = Math.max(max, f);
                            min = Math.min(min, f);
                        }
                    }
                    answer += (max - min);
                }
            }
        }
        return answer;
    }
}
