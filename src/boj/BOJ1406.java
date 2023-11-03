package boj;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1406 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String inputString = bufferedReader.readLine();
        int commandCount = Integer.parseInt(bufferedReader.readLine());
        Stack<Character> leftCursor = new Stack<>();
        Stack<Character> rightCursor = new Stack<>();

        //맨 처음에는 커서가 문자열 맨 뒤에 위치
        for (int i = 0; i < inputString.length(); i++) {
            leftCursor.push(inputString.charAt(i));
        }
        while (commandCount-- > 0) {

            String input = bufferedReader.readLine();
            StringTokenizer st = new StringTokenizer(input);
            String command = st.nextToken();

            switch (command) {
                case "L":
                    if (!leftCursor.isEmpty()) {
                        rightCursor.push(leftCursor.pop());
                    }
                    break;
                case "D":
                    if (!rightCursor.isEmpty()) {
                        leftCursor.push(rightCursor.pop());
                    }
                    break;
                case "B":
                    if (!leftCursor.isEmpty()) {
                        leftCursor.pop();
                    }
                    break;
                case "P":
                    Character inputChar = st.nextToken().charAt(0);
                    leftCursor.push(inputChar);
                    break;
            }
        }

        //문자열 정리
        while (!leftCursor.isEmpty()) {
            rightCursor.push(leftCursor.pop());
        }

        //문자열 출력
        while (!rightCursor.isEmpty()) {
            bufferedWriter.write(rightCursor.pop());
        }

        bufferedWriter.flush();
        bufferedReader.close();

        bufferedReader.close();
    }

}
