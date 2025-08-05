package programmers.binarysearch;

import java.util.*;

public class Programmers43236 {

    public static void main(String[] args) {
        Programmers43236 solution = new Programmers43236();
        int distance = 25;
        int[] rocks = {2, 14, 11, 21, 17};
        int n = 2;
        int solution1 = solution.solution(distance, rocks, n);
        System.out.println(solution1);
    }


    public int solution(int distance, int[] rocks, int n) {
        List<Integer> rockList = new ArrayList<>();

        rockList.add(0); // 시작점
        for(int rock : rocks){
            rockList.add(rock);
        }
        rockList.add(distance); // 종료지점
        Collections.sort(rockList);

        long left = 1;
        long right = distance;
        while(left < right){
            long mid = (left + right) / 2;

            if(canProceed(rockList, n, mid)){
                left = mid + 1;
                continue;
            }

            right = mid-1;
        }

        return (int) right;
    }

    /**
     - targetDist 거리보다 작은 바위들을 지운다.
     - 지운 바위들의 수가 n개 보다 작으면 성공
     */
    public boolean canProceed(List<Integer> rockList, int n, long targetDist){
        int deleteCount = 0;
        int prevPos = 0;  // 이전 위치 추적 <- 단순히 이전 인덱스가 아닌 삭제 했으면 그 이전 바위의 인덱스와 비교

        for (int i = 1; i < rockList.size(); i++) {
            if (rockList.get(i) - prevPos < targetDist) {
                deleteCount++;
                // 바위를 제거하므로 prevPos 업데이트 안함
                continue;
            }
            prevPos = rockList.get(i);  // 바위를 유지할 때만 위치 업데이트
        }

        return deleteCount <= n;
    }

}
