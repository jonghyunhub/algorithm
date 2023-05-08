package boj;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

public class BOJ11656 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        BufferedWriter br = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] arr = new String[s.length()];
        for (int i = 0; i < s.length(); i++) {
            arr[i] = s.substring(i);
        }

        Arrays.sort(arr);
        for (String s1 : arr) {
            br.write(s1 + "\n");
        }
        br.flush();
        br.close();
        scanner.close();
    }
}
