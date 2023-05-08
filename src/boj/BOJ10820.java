package boj;

import java.io.*;

public class BOJ10820 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        while (s != null) {
            int lowerCase = 0;
            int upperCase = 0;
            int number = 0;
            int blank = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    number++;
                }
                if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                    lowerCase++;
                }
                if (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                    upperCase++;
                }
                if (s.charAt(i) == ' ') {
                    blank++;
                }
            }
            bw.write(lowerCase + " " + upperCase + " " + number + " " + blank + "\n");
            s = br.readLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
