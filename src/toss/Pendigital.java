package toss;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// 토스 1번 문제
// 재귀함수로 1 부터 N까지 숫자가 포함된 멋쟁이 팬디지털 리스트를 만든다.(내림차순으로 생성)
// 큰 것부터 하나씩 contains를 해본다.
// 있으면 그거 리턴
// 마지막까지 없으면 -1 리턴
public class Pendigital {

    public static void main(String[] args) {
        Pendigital solution = new Pendigital();
        int solution1 = solution.solution("1451232125", 3);
        System.out.println("solution1 = " + solution1);
    }

    public int solution(String s, int N) {
        List<String> strings = createPendigital(N);
        Optional<String> result = strings.stream()
                .filter(s::contains).findFirst();
        return result.map(Integer::parseInt).orElse(-1);
    }

    public List<String> createPendigital(int n) {
        List<String> permutations = new ArrayList<>();
        createPermutationsHelper("", n, permutations);
        return permutations;
    }

    private void createPermutationsHelper(String current, int n, List<String> permutations) {
        if (current.length() == n) {
            permutations.add(current);
            return;
        }

        for (int i = n; i >= 1; i--) {
            if (!current.contains(String.valueOf(i))) {
                createPermutationsHelper(current + i, n, permutations);
            }
        }
    }
}



