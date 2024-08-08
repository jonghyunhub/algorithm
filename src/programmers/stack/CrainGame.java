package programmers.stack;

import java.util.Stack;

// https://school.programmers.co.kr/learn/courses/30/lessons/64061?language=java
public class CrainGame {

    public static void main(String[] args) {
        final CrainGame crainGame = new CrainGame();
        final int[][] board = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1},
                {4, 2, 4, 4, 2},
                {3, 5, 1, 3, 1}
        };
        final int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        System.out.println(crainGame.solution(board, moves));
    }

    public int solution(int[][] board, int[] moves) {
        final Stack<Integer> basket = new Stack<>();
        final Stack<Integer>[] toyContainers = initToyContainers(board);
        return proceedGame(moves, toyContainers, basket);
    }

    // 1. board 배열을 토대로 각 열에 해당하는 인형들을 Stack에 담는다.
    private Stack<Integer>[] initToyContainers(int[][] board) {
        final Stack<Integer>[] toyContainers = new Stack[board.length];
        for (int i = 0; i < board.length; i++) {
            toyContainers[i] = new Stack<>();
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = board.length - 1; j > 0; j--) {
                if (board[j][i] != 0) toyContainers[i].push(board[j][i]);
            }
        }
        return toyContainers;
    }

    // 2. moves 배열을 토대로 게임을 진행한다.
    private int proceedGame(int[] moves, Stack<Integer>[] toyContainers, Stack<Integer> basket) {
        int answer = 0;
        for (int move : moves) {
            if (toyContainers[move - 1].isEmpty()) continue;
            Integer toyNumber = toyContainers[move - 1].pop();

            if (!basket.isEmpty() && toyNumber.equals(basket.peek())) {
                basket.pop();
                answer += 2;
                continue;
            }

            basket.push(toyNumber);
        }
        return answer;
    }
}
