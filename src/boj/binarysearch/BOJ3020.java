package boj.binarysearch;

import java.io.*;
import java.util.*;

public class BOJ3020 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int H = Integer.parseInt(split[1]);
        int[] bottom = new int[H + 1]; // 높이 i로 날아갈때 부딪히는 석순(아래에서 위)의 갯수
        Arrays.fill(bottom, 0);
        int[] top = new int[H + 1]; // 높이 i로 날아갈때 부딛히는 종유석(위에서 아래)의 갯수
        // 높이 3으로 날아가면 bottom[1], bottom[2], bottom[3] 과 부딪힘
        // 높이 3으로 날아가면 top[3], top[4], top[5] 과 부딪힘 (전체 높이 5일때)
        Arrays.fill(top, 0);

        for (int i = 0; i < N; i++) {
            int nextInt = Integer.parseInt(br.readLine());
            // 석순(아래에서 위)
            if(i%2 == 0){
                bottom[nextInt]++;
                continue;
            }
            // 종유석(위에서 아래)
            top[H-nextInt+1]++;
        }

        // 누적합 계산
        for(int i=H; i>=2; i--){
            bottom[i-1] += bottom[i];
        }

        for(int i=1; i<=H; i++){
            top[i] += top[i-1];
        }
        
        int answer = Integer.MAX_VALUE;
        int cnt = 0;
        for(int i=1; i<=H; i++){
            int crashNum = top[i] + bottom[i];
            if(crashNum < answer){
                answer = crashNum;
                cnt = 1;
                continue;
            }
            if(answer == crashNum) cnt++;
        }

        System.out.println(answer + " " + cnt);
    }
}
