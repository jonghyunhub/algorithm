package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10824 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int i1 = Integer.parseInt(st.nextToken());
        int i2 = Integer.parseInt(st.nextToken());
        int i3 = Integer.parseInt(st.nextToken());
        int i4 = Integer.parseInt(st.nextToken());
        String s1 = String.valueOf(i1);
        String s2 = String.valueOf(i2);
        String s3 = String.valueOf(i3);
        String s4 = String.valueOf(i4);
        String a = s1 + s2;
        String b = s3 + s4;

        long l = Long.parseLong(a) + Long.parseLong(b);
        System.out.println(l);
        br.close();
    }
}
