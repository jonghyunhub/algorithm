package programmers.heap;

import java.util.*;

public class Programmers42627 {

    public static void main(String[] args) {
        Programmers42627 p = new Programmers42627();
        int[][] jobs = {{0, 3}, {1, 9}, {3, 5}};
        int solution = p.solution(jobs);
    }

    public int solution(int[][] jobs) {
        int nowTime = 0;
        // 우선순위 : 소요시간이 짧은것 > 작업의 요청 시각이 빠른 것 > 작업의 번호가 작은 것
        PriorityQueue<Task> waitQueue = new PriorityQueue<>((a,b) -> {
            // 1순위: 소요시간이 짧은 것
            if(a.duration != b.duration){
                return a.duration - b.duration; // 작을수록 우선
            }
            // 2순위: 작업의 요청 시각이 빠른 것
            if(a.reqTime != b.reqTime){
                return a.reqTime - b.reqTime; // 작을수록 우선
            }
            // 3순위: 작업의 번호가 작은 것
            return a.taskId - b.taskId; // 작을수록 우선
        });

        List<Task> jobList = new ArrayList<>();
        List<Task> jobListCopy = new ArrayList<>();
        Task nowTask = null;
        for(int i=0; i<jobs.length; i++) {
            int reqTime = jobs[i][0];
            int duration = jobs[i][1];
            Task task = new Task(i, reqTime, duration);
            jobList.add(task);
            jobListCopy.add(task);
        }

        while(true){
            // task 실행전 넣을 테스크 있는지 확인
            Iterator<Task> iterator = jobListCopy.iterator();
            while (iterator.hasNext()) {
                Task task = iterator.next();
                if(task.reqTime <= nowTime && task.duration > 0){
                    waitQueue.add(task);
                    iterator.remove();
                }
            }

            if(nowTask == null && waitQueue.isEmpty() && jobListCopy.isEmpty()){
                break;
            }

            // 태스크 처리
            if(nowTask != null){
                nowTime += nowTask.duration;
                nowTask.finishTime = nowTime;
                nowTask = null;
                continue;
            }


            if(!waitQueue.isEmpty()){
                nowTask = waitQueue.poll();
                continue;
            }

            // waitQueue에도 없고 jobList 요청시간이 아직 안된 경우 시간 증가 <- 엣지케이스 : 하드디스크 컨트롤러는 놀고 있을수도 있다.ex. {0, 1}, {100, 1} -> 99초동안 놀다가 100초부터 일 다시 시작
            nowTime++;
        }

        int answer = 0;
        for(Task task : jobList) {
            int time = task.finishTime - task.reqTime;
            answer += time;
        }

        answer =  answer / jobList.size();

        return answer;
    }

    class Task {
        int taskId;
        int reqTime;
        int duration;
        int finishTime;

        Task(int taskId, int reqTime, int duration){
            this.taskId = taskId;
            this.reqTime = reqTime;
            this.duration = duration;
        }
    }
}
