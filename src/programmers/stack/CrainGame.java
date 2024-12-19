package programmers.stack;

import java.util.*;

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

        final int[][] board1 = {
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1},
                {1,1,1,1,1}
        };
        final int[] moves = {1, 5, 3, 5, 1, 2, 1, 4};
        System.out.println(crainGame.solution2(board1, moves));
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
            for (int j = board.length - 1; j >= 0; j--) {
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


    public int solution2(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> bucket = new Stack<>();
        List<Stack<Integer>> boardStack = new ArrayList<>();

        // board 배열을 입력받고 이것을 스택으로 변환한다.
        for(int i=0; i<board.length; i++)
            boardStack.add(new Stack<>());
        for(int col=0; col<board.length; col++)
            for(int row=board.length-1; row>=0; row--){
                if(board[row][col] != 0)
                    boardStack.get(col).push(board[row][col]);
            }


        //moves 배열을 순회하면서 moves 배열의 각 원소의 값의 스택에서 원소를 꺼내서 바구니 스택에 담는다.
        //바구니 스택에 담을때, 스택의 맨 위 원소가 현재 원소와 같으면 꺼내고 정답에 2를 더한다.
        for(int move:moves){
            if(boardStack.get(move-1).isEmpty()) continue; // 스택이 비어있으면 스킵
            Integer pop = boardStack.get(move-1).pop(); //배열은 0부터 시작하므로 move 값에서 1 빼야함
            if(!bucket.isEmpty() && bucket.peek() == pop){
                answer += 2;
                bucket.pop();
                continue;
            }
            bucket.push(pop);
        }

        return answer;
    }
}
