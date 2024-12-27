package programmers.hash;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42888
public class OpenChattingRoom {

    public static void main(String[] args) {
        OpenChattingRoom openChattingRoom = new OpenChattingRoom();
        String[] record = {
                "Enter uid1234 Muzi",
                "Enter uid4567 Prodo",
                "Leave uid1234",
                "Enter uid1234 Prodo",
                "Change uid4567 Ryan"
        };
        String[] result = openChattingRoom.solution(record);
        for(String log : result){
            System.out.println(log);
        }
    }

    public String[] solution(String[] record) {
        final Map<String, String> userMap = new HashMap<>();
        final List<Log> logs = new ArrayList<>();
        for(String now : record){
            final String[] parse = now.split(" ");
            final String cmd = parse[0];
            final String userId = parse[1];

            if(cmd.equals("Enter")){
                final String nickName = parse[2];
                // 새로 들어왔던 아니던 put을 사용하면 덮어쓰므로 put 명령어 하나로 처리가능
                userMap.put(userId, nickName);
                Log enterLog = new Log(userId, "님이 들어왔습니다.");
                logs.add(enterLog);
                continue;
            }

            if(cmd.equals("Leave")){
                Log leaveLog = new Log(userId, "님이 나갔습니다.");
                logs.add(leaveLog);
                continue;
            }

            if(cmd.equals("Change")){
                final String newNickName = parse[2];
                userMap.put(userId, newNickName);
            }
        }
        return logs.stream().map(log -> log.toResult(userMap)).toArray(String[]::new);
    }

    static class Log {
        String userId;
        String action;

        public Log(String userId, String action){
            this.userId = userId;
            this.action = action;
        }

        public String toResult(Map<String, String> userMap){
            return userMap.get(userId) + action;
        }

    }
}
