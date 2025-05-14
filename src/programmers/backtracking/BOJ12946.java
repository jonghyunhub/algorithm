package programmers.backtracking;

import java.util.*;


public class BOJ12946 {
    public static void main(String[] args) {
        BOJ12946 boj12946 = new BOJ12946();
        int[][] solution = boj12946.solution(2);
        System.out.println(Arrays.deepToString(solution));
    }

    public int[][] solution(int n) {
        List<int[]> answer = new ArrayList<>();
        List<List<Integer>> pillars = new ArrayList<>();
        for(int i=0; i<3; i++){
            pillars.add(new ArrayList<>());
        }
        for(int i=n; i>=1; i--)
            pillars.get(0).add(i);

        // 1번 기둥에서 3번 기둥으로 n개의 원판을 이동 (0-indexed로 0→2)
        hanoi(n, 0, 1, 2, answer);

        return answer.stream().toArray(int[][]::new);
    }


    /**
     from : 옮기려는 기둥의 인덱스
     to : 원판을 새롭게 추가할 기둥의 인덱스
     n-1개의 원판을 1번 기둥 -> 2번 기둥으로 옮긴다
     1개 (가장 큰) 원판을 1번 기둥 -> 3번 기둥으로 옮긴다.
     n-1개의 원판을 2번 기둥 -> 3번 기둥으로 옮긴다
     */
    public boolean doHanoi(List<int[]> answer,
                           List<List<Integer>> pillars,
                           int n,
                           int targetPillar){
        // 종료 조건: 목표 기둥에 모든 원판이 있는지 확인
        if (pillars.get(targetPillar).size() == n) {
            return true; // 성공적으로 모든 원판을 옮김
        }

        // 모든 가능한 이동 시도 (from -> to)
        for (int from = 0; from < 3; from++) {
            for (int to = from+1; to < 3; to++) {

                List<Integer> fromPillar = pillars.get(from);
                List<Integer> toPillar = pillars.get(to);

                // 비어있는 기둥에서는 원판을 가져올 수 없음
                if (fromPillar.isEmpty()) continue;

                // 목적지 기둥이 비어있지 않고, 옮기려는 원판이 더 크면 이동 불가
                if (!toPillar.isEmpty() &&
                        fromPillar.get(fromPillar.size() - 1) > toPillar.get(toPillar.size() - 1)) {
                    continue;
                }

                // 유효한 이동이므로 원판 이동 수행
                int plate = fromPillar.remove(fromPillar.size() - 1);
                toPillar.add(plate);
                answer.add(new int[]{from+1, to+1});

                // 재귀적으로 다음 이동 시도
                if (doHanoi(answer, pillars, n, targetPillar)) {
                    return true; // 이 이동으로 최종 목표에 도달함
                }

                // 이 이동으로는 목표에 도달할 수 없으므로 원상복구 (백트래킹)
                answer.remove(answer.size() - 1);
                toPillar.remove(toPillar.size() - 1);
                fromPillar.add(plate);
            }
        }

        // 모든 가능한 이동을 시도했지만 해결책을 찾지 못함
        return false;
    }

    public void hanoi(int n, int from, int aux, int to, List<int[]> answer) {
        if (n == 1) {
            // 원판 1개일 때는 직접 목적지로 이동
            answer.add(new int[]{from, to});
            return;
        }

        // 1. n-1개 원판을 from에서 aux로 이동 (to를 보조 기둥으로 사용)
        hanoi(n-1, from, to, aux, answer);

        // 2. 가장 큰 원판을 from에서 to로 이동
        answer.add(new int[]{from, to});

        // 3. n-1개 원판을 aux에서 to로 이동 (from을 보조 기둥으로 사용)
        hanoi(n-1, aux, from, to, answer);
    }
}
