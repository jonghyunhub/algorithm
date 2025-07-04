package boj.string;

import java.io.*;

public class BOJ2744 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferedReader.readLine();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<input.length(); i++) {
            char c = input.charAt(i);
            if(isSmallChar(c)) {
                stringBuilder.append((char)(c - 'a' + 'A'));
                continue;
            }
            stringBuilder.append((char)(c - 'A' + 'a'));
        }

        System.out.println(stringBuilder);
    }

    public static boolean isSmallChar(char c){
        return c >= 'a' && c <= 'z';
    }
}
