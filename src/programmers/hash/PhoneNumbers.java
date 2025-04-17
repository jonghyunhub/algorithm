package programmers.hash;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/42577 - 전화번호 목록
public class PhoneNumbers {

    public static void main(String[] args) {
        PhoneNumbers phoneNumbers = new PhoneNumbers();
        String[] phone_book1 = {"119", "97674223", "1195524421"};
        String[] phone_book2 = {"123", "456", "789"};
        String[] phone_book3 = {"12", "123", "1235", "567", "88"};
        boolean solution = phoneNumbers.solution1(phone_book2);
        System.out.println(solution);
    }

    public boolean solution1(String[] phone_book) {
        final Set<String> prefixSet = new HashSet<>();
        for (String phoneNum : phone_book) {
            // 각 전화번호의 길이는 20자가 최대, phone_book의 길이는 100만까지이므로
            // 각 전화번호의 부분 문자열을 순회하면서 prefixSet에 포함되어있는지 확인
            // 접두어는 무조건 index = 0 인 부분문자열만 포함
            // ""은 포함안되게 막기
            for (int i = 1; i <= phoneNum.length(); i++) {
                String sub = phoneNum.substring(0, i);
                if (prefixSet.contains(sub)) return false;
            }
            prefixSet.add(phoneNum);
        }

        // 마지막 전화번호가 다른 번호들의 접두어인 경우도 체크 => O(N^2) 시간초과 발생
        for (String prefix : prefixSet) { // <- 100만
            for (String phoneNum : phone_book) { // <- 100만
                if (prefix.equals(phoneNum)) continue;
                if (prefix.startsWith(phoneNum))
                    return false;
            }
        }

        return true;
    }

    public boolean solution2(String[] phone_book) {
        final Set<String> phoneSet = new HashSet<>(Arrays.asList(phone_book)); // O(N)
        for (String phoneNum : phoneSet) {
            for(int i=0; i<phoneNum.length(); i++) { // 각 전화번호의 길이가 최대 20이므로 O(20*N) => O(N)
                String prefix = phoneNum.substring(0, i);
                if (phoneSet.contains(prefix)) return false;
            }
        }

        return true;
    }
}
