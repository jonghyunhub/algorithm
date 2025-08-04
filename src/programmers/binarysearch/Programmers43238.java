package programmers.binarysearch;

public class Programmers43238 {
    public static void main(String[] args) {
        Programmers43238 programmers43238 = new Programmers43238();
        int n = 6;
        int[] times = {7, 10};
        long solution = programmers43238.solution(n, times);
        System.out.println(solution);
    }

    /**
     1 ~ 사람수*[각 판매원 처리 시간의 최대값] 중에서 모든 인원을 처리할 수 있는 최소의 시간을 찾는다.
     - 모든 인원을 처리할 수 있는 최소의 시간의 조건
     - 전체 인원수 <= Sum(주어진 전체 시간 / 각 심사관이 한명을 처리하는 시간)
     */
    public long solution(int n, int[] times) {
        long timeMax = 0;
        for(int time : times){
            timeMax = Math.max(timeMax, time);
        }

        long left = 1;
        long right = n * timeMax;
        while(left < right){
            long mid = (left + right) / 2;
            if(canProcess(n, mid, times)){
                right = mid;
                continue;
            }
            left = mid+1;
        }

        return left;
    }

    /**
     [모든 인원을 처리할 수 있는 최소의 시간의 조건]
     - 전체 인원수 <= Sum(주어진 전체 시간 / 각 심사관이 한명을 처리하는 시간)
     */
    public boolean canProcess(int totalPeople, long totalTime, int[] times){
        long canProcessPeopleCount = 0;
        for(int time : times){
            canProcessPeopleCount += totalTime / time;
        }
        return canProcessPeopleCount >= totalPeople;
    }
}
