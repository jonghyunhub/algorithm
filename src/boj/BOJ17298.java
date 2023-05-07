package boj;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        int[] answer = new int[N];
        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>(); // 인덱스 배열
        stack.push(0);

        for (int i = 1; i < A.length; i++) {
            while (!stack.isEmpty()){
                Integer peek = stack.peek();
                if (A[peek] < A[i]) { // 오큰수 인 경우 , A의 인덱스 스택에서 꺼내고 오큰수 값을 정답 배열에 넣어줌
                    answer[stack.pop()] = A[i];
                    if (stack.isEmpty()) { //오큰수 비교 끝내고 스택이 비어있으면 현재 인덱스 스택에 넣어줌
                        stack.push(i);
                        break;
                    }
                } else { //오큰수 아닌 경우
                    stack.push(i);
                    break;
                }
            }
        }

        while (!stack.isEmpty()) { //오큰수가 없으면 -1
            answer[stack.pop()] = -1;
        }

        for (int i : answer) {
            bw.write(i + " ");
        }


        bw.flush();
        bw.close();
        br.close();
    }
}
