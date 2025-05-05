package programmers.bruteforce;

import java.util.*;


// https://school.programmers.co.kr/learn/courses/30/lessons/86971
public class Programmers86971 {
    public static void main(String[] args) {
        Programmers86971 programmers86971 = new Programmers86971();
        int n1 = 9;
        int[][] wires1 = {{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}};
        int n2 = 4;
        int[][] wires2 = {{1, 2}, {2, 3}, {3, 4}};
        int n3 = 7;
        int[][] wires3 = {{1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}};
        int solution = programmers86971.solution(n3, wires3);
        System.out.println(solution);
    }

    public int solution(int n, int[][] wires) {
        int answer = 100000000;
        Map<Integer, List<Integer>> tree = new HashMap<>();

        for(int[] wire : wires) {
            List<Integer> orDefault = tree.getOrDefault(wire[0], new ArrayList<>());
            orDefault.add(wire[1]);
            tree.put(wire[0],orDefault);

            List<Integer> reverseOrDefault = tree.getOrDefault(wire[1], new ArrayList<>());
            reverseOrDefault.add(wire[0]);
            tree.put(wire[1],reverseOrDefault);
        }

        for (int[] deletedWire : wires) {
            final Set<Integer> node1 = new HashSet<>();
            final Set<Integer> node2 = new HashSet<>();

            // 부분 노드 초기화
            searchTree(deletedWire[0],deletedWire[1], tree, node1);

            searchTree(deletedWire[1], deletedWire[0], tree, node2);

            int count1 = node1.size();
            int count2 = node2.size();
            int abs = count1 - count2 > 0 ? count1 - count2 : count2 - count1;
            answer = Math.min(abs, answer);
        }

        return answer;
    }

    public void searchTree(int now, int skip, Map<Integer, List<Integer>> tree, Set<Integer> nodes) {
        if(now == skip) return;
        if(nodes.contains(now)) return;
        nodes.add(now);
        for(int next: tree.getOrDefault(now,new ArrayList<>())){
            if(next == skip) continue;
            searchTree(next,skip,tree,nodes);
        }
    }
}
