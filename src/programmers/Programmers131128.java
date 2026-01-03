package programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/131128
public class Programmers131128 {
    public static void main(String[] args) {
        String X1 = "100";
        String Y1 = "203045";
        String X2 = "5525";
        String Y2 = "1255";
        String solution = new Programmers131128().solution(X2, Y2);
        System.out.println(solution);
    }

    // 공통된 숫자 카운트 할때는 직접 배열로 카운트하는게 효율적
    public String solution(String X, String Y) {
        StringBuilder builder = new StringBuilder();
        int[] countX = new int[10];
        int[] countY = new int[10];
        for(int i = 0; i < X.length(); i++) {
            countX[Integer.parseInt(String.valueOf(X.charAt(i)))]++;
        }

        for(int i = 0; i < Y.length(); i++) {
            countY[Integer.parseInt(String.valueOf(Y.charAt(i)))]++;
        }

        for(int i=9; i>=0; i--) {
            int commonCnt = Math.min(countX[i], countY[i]);
            for(int j=0; j<commonCnt; j++) {
                builder.append(i);
            }
        }

        String answer = builder.toString();
        if(answer.isEmpty()) return "-1";
        if(answer.charAt(0) == '0') return "0";

        return builder.toString();
    }
}
