package programmers.graph;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/81305
public class DivideTestSite {

    public static void main(String[] args) {
        int k1 = 3;
        int[] num1 = {12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1};
        int[][] links1 = {
                {-1, -1},
                {-1, -1},
                {-1, -1},
                {-1, -1},
                {8, 5},
                {2, 10},
                {3, 0},
                {6, 1},
                {11, -1},
                {7, 4},
                {-1, -1},
                {-1, -1}
        };
        DivideTestSite divideTestSite = new DivideTestSite();
        divideTestSite.solution(k1, num1, links1);
    }

    public int solution(int k, int[] num, int[][] links) {
        int answer = 0;
        final Map<Integer, Child> tree = new HashMap<>();
        for(int i=0; i<links.length; i++){
            int[] link = links[i];
            int left = link[0];
            int right = link[1];
            tree.put(i, new Child(left, right));
        }

        return answer;
    }

    static class Child {
        int left;
        int right;

        public Child(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}
