package programmers;

public class Programmers388351 {

    public static void main(String[] args) {
        int[] schedules = {700, 800, 1100};
        int[][] timelogs = {{710, 2359, 1050, 700, 650, 631, 659}, {800, 801, 805, 800, 759, 810, 809}, {1105, 1001, 1002, 600, 1059, 1001, 1100}};
        int startDay = 5;
        Programmers388351 programmers388351 = new Programmers388351();
        int solution = programmers388351.solution(schedules, timelogs, startDay);
        System.out.println(solution);
    }

    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        for(int i=0; i<schedules.length; i++){
            int targetSchedule = schedules[i];
            int[] nowTimeLogs = timelogs[i];
            boolean isComplete = true;

            for(int j=0; j<nowTimeLogs.length; j++){
                int nowDay = (startday + j) % 7;
                // 토요일, 일요일 X
                if(nowDay == 6 || nowDay == 0) continue;

                int nowTime = nowTimeLogs[j];
                int nowHour = nowTime / 100;
                int nowMin = nowTime % 100;

                int deadLineHour = targetSchedule / 100;
                int deadLineMin = targetSchedule % 100;

                deadLineMin  = deadLineMin + 10;
                if(deadLineMin >= 60){
                    deadLineHour++;
                    deadLineMin = deadLineMin - 60;
                }

                if(nowHour > deadLineHour){
                    isComplete = false;
                    break;
                }

                if(nowHour == deadLineHour && nowMin > deadLineMin){
                    isComplete = false;
                    break;
                }

            }

            if(isComplete) answer++;
        }

        return answer;
    }
}
