package boj;

import java.io.*;

public class BOJ10809 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] alphabet = new int[26];
        String s = br.readLine();
        int firstData = s.charAt(0) - 'a';

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (alphabet[index] == 0) {
                alphabet[index] = i;
            }
        }

        for (int i = 0; i < alphabet.length; i++) {
            if (i == firstData) {
                bw.write(0 + " ");
            }else if (alphabet[i] == 0) {
                bw.write(-1 + " ");
            }else {
                bw.write(alphabet[i] + " ");
            }
        }


        br.close();
        bw.flush();
        bw.close();

    }
}
