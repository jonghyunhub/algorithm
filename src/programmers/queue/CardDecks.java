package programmers.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/159994
public class CardDecks {
    public static void main(String[] args) {
        CardDecks cardDecks = new CardDecks();
//        String[] cards1 = {"i", "drink", "water"};
//        String[] cards2 = {"want", "to"};
//        String[] goal = {"i", "want", "to", "drink", "water"};

        String[] cards1 = {"i", "water", "drink"};
        String[] cards2 = {"want", "to"};
        String[] goal = {"i", "want", "to", "drink", "water"};

        System.out.println(cardDecks.solution(cards1, cards2, goal));
    }

    public String solution(String[] cards1, String[] cards2, String[] goal) {
        final StringBuilder stringBuilder = new StringBuilder();
        final List<String> combination = new ArrayList<>();
        generateCombination(cards1.length, cards2.length, stringBuilder, combination);
        for (String com : combination) {
            final List<String> compare = new ArrayList<>();

            final Queue<String> queue1 = new ArrayDeque<>();
            for (String card : cards1) {
                queue1.add(card);
            }

            final Queue<String> queue2 = new ArrayDeque<>();
            for (String card : cards2) {
                queue2.add(card);
            }

            for (char c : com.toCharArray()) {
                if (c == 'a') {
                    compare.add(queue1.poll());

                } else {
                    compare.add(queue2.poll());
                }
                if (isGoal(compare, goal)) return "Yes";
            }
        }

        return "No";
    }

    public void generateCombination(int numA, int numB, StringBuilder current, List<String> combination) {
        if (numA == 0 && numB == 0) {
            combination.add(current.toString());
            return;
        }
        if (numA > 0) {
            current.append("a");
            generateCombination(numA - 1, numB, current, combination);
            current.setLength(current.length() - 1); // 백트래킹 처리
        }
        if (numB > 0) {
            current.append("b");
            generateCombination(numA, numB - 1, current, combination);
            current.setLength(current.length() - 1); // 백트래킹 처리
        }
    }

    public boolean isGoal(List<String> compare, String[] goal) {
        if (compare.size() != goal.length) {
            return false;
        }
        for (int i = 0; i < goal.length; i++) {
            if (!compare.get(i).equals(goal[i])) {
                return false;
            }
        }
        return true;
    }
}
