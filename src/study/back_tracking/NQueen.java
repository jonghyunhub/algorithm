package study.back_tracking;

import java.util.ArrayList;
import java.util.List;

public class NQueen {
    public static void main(String[] args) {
        List<List<Integer>> result = nQueen(4);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    /**
     * 1. DFS 함수의 순회 : 각 row를 DFS로 탐색하면서, 각 row에 queen을 놓을 수 있는지 확인한다.
     * 2. 유망함수 정의 : queen을 놓을 수 있는지 확인하는 방법은, 같은 column, 같은 대각선에 queen이 있는지 확인한다.
     * 3. DFS 종료 조건 : row가 N이 되면, queen을 모두 놓은 것이므로, 결과를 출력한다.
     * 4. DFS 탐색 : 각 row에 queen을 놓을 수 있는지 확인하고, 놓을 수 있다면, 다음 row로 넘어간다.
     * 5. DFS 백트래킹 : 유망함수를 만족하지 않으면 다음 row로 넘어가지 않고, 이전 row로 돌아간다.
     * 6. DFS 탐색 종료 : 모든 경우의 수를 탐색하면 종료한다.
     */
    public static List<List<Integer>> nQueen(int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentCandidate = new ArrayList<>();
        dfs(n, 0, currentCandidate, result);
        return result;
    }

    public static void dfs(int n, int currentRow, List<Integer> currentCandidate, List<List<Integer>> result) {
        if(currentRow == n){
            result.add(currentCandidate);
            return;
        }

        for (Integer currentColumn : currentCandidate) {
            if(isPromising(currentRow, currentColumn, currentCandidate)){
                currentCandidate.add(currentColumn);
                dfs(n, currentRow + 1, currentCandidate, result);
                currentCandidate.remove(currentCandidate.size() - 1);
            }
        }
    }

    public static boolean isPromising(int currentRow, int currentColumn, List<Integer> currentCandidate) {
        for (int i = 0; i < currentRow; i++) {
            if(currentCandidate.get(i) == currentColumn || Math.abs(currentRow - i) == Math.abs(currentColumn - currentCandidate.get(i))){
                return false;
            }
        }
        return true;
    }


}
