package boj.bruteforce;

import java.io.*;
import java.util.*;

public class BOJ10448 {
    static boolean canMake = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = Integer.parseInt(br.readLine());
        List<Integer> triangularNumbers = new ArrayList<>();
        for(int i=1; i<=500; i++) {
            triangularNumbers.add(getTriangularNumber(i));
        }
        for (int i = 0; i < caseNum; i++) {
            int input = Integer.parseInt(br.readLine());
            List<Integer> findNumbers = new ArrayList<>();
            findPermutations(triangularNumbers, findNumbers, input);
            if(canMake){
                System.out.println(1);
                canMake = false;
                continue;
            }
            System.out.println(0);
        }
    }

    public static int getTriangularNumber(int n) {
        return n * (n + 1) / 2;
    }

    public static void findPermutations(List<Integer> triangularNumbers,
                                           List<Integer> findNumbers,
                                           int find){
        if(canMake) return;

        if(findNumbers.size() == 3){
            if(calSumList(findNumbers) == find) canMake = true;
            return;
        }

        for(int i = 0; i < 500; i++) {
            if(triangularNumbers.get(i) > find) return;
            findNumbers.add(triangularNumbers.get(i));

            findPermutations(triangularNumbers, findNumbers, find);

            findNumbers.remove(findNumbers.size() - 1);
        }
    }

    public static int calSumList(List<Integer> findNumbers) {
        return findNumbers.stream().mapToInt(i -> i).sum();
    }
}
