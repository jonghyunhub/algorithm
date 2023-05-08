package boj;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class BOJ11655 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = scanner.nextLine();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(c > 'z' - 13 && c <= 'z'){
                bw.write((char) ('a' + 12 - ('z' - c) ));
            }else if (c >= 'a' && c <= 'z' - 13) {
                bw.write((char) (c + 13));
            } else if (c > 'Z' - 13 && c <= 'Z') {
                bw.write((char) ('A' + 12 - ('Z' - c)));
            } else if (c >= 'A' && c <= 'Z' - 13) {
                bw.write((char) (c + 13));
            } else {
                bw.write(c);
            }
        }
        bw.flush();
        bw.close();
        scanner.close();
    }
}
