package programmers.dp;

import java.math.BigInteger;
import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/12936
public class Permutations {

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        int n1=3;
        long k1 =5;
        int n2 =4;
        long k2 = 10;
        int[] solution = permutations.solution(n2, k2);
        System.out.println("Arrays.toString(solution) = " + Arrays.toString(solution));
    }

    static long[] factorial;
    /**
     완전 탐색으로 모든 경우의 수를 구하지 않고 k번째 원소만 구하기
     k는 n!이하의 자연수 이기 때문에, 엄청 큰수도 가능 -> 완전탐색으로 구현시 무조건 시간초과 및 메모리 초과 발생
     [k번째 수열 구하는 방법]
     - 순열의 가지수는 n! 개 이므로 1xxxxx... 는 (n-1)! 개 존재
     예를 들어) n = 4, k = 10 라고 하면 1234, 1243, 1324, 1342, 1423, 1432, 2134, 2143, 2314, 2341(답)
     1. 9 / (4-1)! = 10 / 6 = 1 , 9 % 3! = 3 => 두번째 그룹의 숫자 2 선택 list = [1,2,3,4] , k = 9, n = 4
     2. 3 / (3-1) ! = 4 / 2 = 1 , 3 % 2! = 1 => 두번째 그룹의 숫자 3 선택 list = [1,3,4], k = 3, n =3
     3. 1 / (2-1) ! = 1 / 1 = 1 , 1 % 1! = 1 => 두번째 그룹의 숫자 4 선택 list = [1,4], k = 1, n = 2
     4. 0 => 남은 숫자 추가
     - k 가 주어졌을때 첫번째 자리수는 k % (n-1)!
     */
    public int[] solution(int n, long k) {
        final List<Integer> answer = new ArrayList<>();
        final List<Integer> list = new ArrayList<>();
        for(int i=1; i<=n; i++) list.add(i);

        factorial = new long[n + 1];
        makeFactorial(n);

        k = k-1;

        // k번째 순열 찾기
        for(int i = n; i > 0; i--) {
            int index = (int)(k / factorial[i-1]);
            k %= factorial[i-1];

            answer.add(list.get(index));
            list.remove(index);
        }

        return answer.stream().mapToInt(i->i).toArray();
    }

    public void makeFactorial(int n){
        factorial[0] = 1;
        factorial[1] = 1;

        for(int i = 2; i <= n; i++) {
            factorial[i] = factorial[i-1] * i;
        }
    }
}
