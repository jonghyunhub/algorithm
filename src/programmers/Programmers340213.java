package programmers;

public class Programmers340213 {
    public static void main(String[] args) {
        Programmers340213 programmers340213 = new Programmers340213();
        String video_len = "07:22";
        String pos ="04:05";
        String op_start  = "00:15";
        String op_end ="04:07";
        String[] commands = {"next"};
        String solution = programmers340213.solution(video_len, pos, op_start, op_end, commands);
        System.out.println(solution);
    }

    public String solution(String video_len,
                           String pos,
                           String op_start,
                           String op_end,
                           String[] commands) {
        Time nowTime = new Time(pos);
        Time totalTime = new Time(video_len);
        Time opStartTime = new Time(op_start);
        Time opEndTime = new Time(op_end);

        for(String command : commands){
            ifInOpTimeToSkip(opStartTime, opEndTime, nowTime);
            if(command.equals("next")){
                nowTime.addMin(10, totalTime);
                ifInOpTimeToSkip(opStartTime, opEndTime, nowTime);
                continue;
            }

            if(command.equals("prev")){
                nowTime.miusMin(10);
                ifInOpTimeToSkip(opStartTime, opEndTime, nowTime);
            }
        }

        String hourStr = "" + nowTime.hour;
        if(nowTime.hour >= 0 && nowTime.hour < 10){
            hourStr = "0" + nowTime.hour;
        }

        String minStr = "" + nowTime.min;
        if(nowTime.min >= 0 && nowTime.min < 10){
            minStr = "0" + nowTime.min;
        }


        return hourStr + ":" + minStr;
    }


    public void ifInOpTimeToSkip(Time startTime, Time endTime, Time nowTime){
        if(startTime.isBefore(nowTime) && nowTime.isBefore(endTime)){
            nowTime.hour = endTime.hour;
            nowTime.min = endTime.min;
        }
    }

    static class Time{
        int hour;
        int min;

        public Time(String time){
            String[] split = time.split(":");
            this.hour = Integer.parseInt(split[0]);
            this.min = Integer.parseInt(split[1]);
        }

        public void addMin(int addMin, Time totalTime){
            this.min += addMin;
            if(this.min >= 60){
                this.hour++;
                this.min -= 60;
            }
            if(this.hour > totalTime.hour ||
                    (this.hour == totalTime.hour && this.min > totalTime.min)){
                this.hour = totalTime.hour;
                this.min = totalTime.min;
            }
        }

        public void miusMin(int miusMin){
            this.min -= miusMin;
            if(this.min < 0){
                this.hour--;
                this.min += 60;
            }

            if(this.hour < 0 || (this.hour == 0 && this.min < 0)){
                this.hour = 0;
                this.min = 0;
            }
        }


        public boolean isBefore(Time other){
            if(this.hour < other.hour) return true;
            if(this.hour == other.hour && this.min <= other.min) return true;

            return false;
        }
    }
}
