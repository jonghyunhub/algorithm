package boj;

import java.io.*;

public class BOJ10808 {

    //아스키 코드 '0' : 48 , 'A' : 65, 'a' : 97
    public static void main(String[] args) throws IOException {
        int[] alphabet = new int[26];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            alphabet[s.charAt(i) - 'a']++;
        }
        for (int i : alphabet) {
            bw.write(i + " ");
        }
        bw.flush();
        br.close();
    }

}
