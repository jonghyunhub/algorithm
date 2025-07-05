package boj.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ13223 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String nowTime = br.readLine();
        String saltTime = br.readLine();
        StringBuilder sb = new StringBuilder();
        calTime(nowTime, saltTime, sb);

//        if(nowTime.equals(saltTime)) {
//            System.out.println("24:00:00");
//            return;
//        }
//
//        int nowHour = Integer.parseInt(nowTime.substring(0, 2));
//        int nowMin = Integer.parseInt(nowTime.substring(3, 5));
//        int nowSec = Integer.parseInt(nowTime.substring(6, 8));
//
//        int saltHour = Integer.parseInt(saltTime.substring(0, 2));
//        int saltMin = Integer.parseInt(saltTime.substring(3, 5));
//        int saltSec = Integer.parseInt(saltTime.substring(6, 8));
//
//        // nowTime < saltTime => saltTime - nowTime
//        if(isSaltTimeAfterNow(nowTime, saltTime)) {
//            int sec = -nowSec + saltSec;
//            if(sec <0){
//                sec += 60;
//                saltMin--;
//            }
//            int min = -nowMin + saltMin;
//            if(min <0){
//                min += 60;
//                saltHour--;
//            }
//            int time = -nowHour + saltHour;
//
//            if(time < 10) sb.append("0");
//            sb.append(time);
//            sb.append(":");
//
//            if(min < 10) sb.append("0");
//            sb.append(min);
//            sb.append(":");
//
//            if(sec < 10) sb.append("0");
//            sb.append(sec);
//
//            System.out.println(sb);
//            br.close();
//            return;
//        }
//
//
//        // nowTime > saltTime => 24:00:00 - nowTime + saltTime
//        int sec = -nowSec + saltSec;
//        if(sec < 0){
//            sec += 60;
//            saltMin--;
//        }
//        int min = -nowMin + saltMin;
//        if(min < 0){
//            min += 60;
//            saltHour--;
//        }
//
//        int time = 24 - nowHour + saltHour;
//
//        if(time < 10) sb.append("0");
//        sb.append(time);
//        sb.append(":");
//
//        if(min < 10) sb.append("0");
//        sb.append(min);
//        sb.append(":");
//
//        if(sec < 10) sb.append("0");
//        sb.append(sec);

        System.out.println(sb);
        br.close();
    }

    public static boolean isSaltTimeAfterNow(String nowTime, String saltTime) {
        int nowHour = Integer.parseInt(nowTime.substring(0, 2));
        int nowMin = Integer.parseInt(nowTime.substring(3, 5));
        int nowSec = Integer.parseInt(nowTime.substring(6, 8));

        int saltHour = Integer.parseInt(saltTime.substring(0, 2));
        int saltMin = Integer.parseInt(saltTime.substring(3, 5));
        int saltSec = Integer.parseInt(saltTime.substring(6, 8));

        if(nowHour < saltHour) return true;
        if(nowHour > saltHour) return false;

        if(nowMin < saltMin) return true;
        if(nowMin > saltMin) return false;

        if(nowSec < saltSec) return true;
        if(nowSec > saltSec) return false;

        return true; // 둘이 같은 경우
    }

    public static void calTime(String nowTime, String saltTime, StringBuilder sb) {
        String[] splitNowTime = nowTime.split(":");
        int nowHour = Integer.parseInt(splitNowTime[0]);
        int nowMin = Integer.parseInt(splitNowTime[1]);
        int nowSec = Integer.parseInt(splitNowTime[2]);

        String[] splitSaltTime = saltTime.split(":");
        int saltHour = Integer.parseInt(splitSaltTime[0]);
        int saltMin = Integer.parseInt(splitSaltTime[1]);
        int saltSec = Integer.parseInt(splitSaltTime[2]);

        int nowTimeSec = nowSec + nowMin * 60 + nowHour * 3600;
        int saltTimeSec = saltSec + saltMin * 60 + saltHour * 3600;

        if(nowTimeSec == saltTimeSec){
            sb.append("24:00:00");
            return;
        }

        if(nowTimeSec < saltTimeSec){
            int calTimeSec = saltTimeSec - nowTimeSec;
            int calHour = calTimeSec / 3600;
            int calMin = (calTimeSec % 3600)/60;
            int calSec = calTimeSec % 60;

            sb.append(appendTime(calHour))
                    .append(":")
                    .append(appendTime(calMin))
                    .append(":")
                    .append(appendTime(calSec));
            return;
        }

        int calTimeSec = 24 * 3600 + saltTimeSec - nowTimeSec;
        int calHour = calTimeSec / 3600;
        int calMin = (calTimeSec % 3600)/60;
        int calSec = calTimeSec % 60;

        sb.append(appendTime(calHour))
                .append(":")
                .append(appendTime(calMin))
                .append(":")
                .append(appendTime(calSec));
    }

    public static String appendTime(int timeValue){
        if(timeValue >= 0 && timeValue < 10){
            return "0" + timeValue;
        }
        return String.valueOf(timeValue);
    }
}
