package boj;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17299 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N];
        int[] feq = new int[1000001];

        for (int i = 0; i < N; i++) { // 숫자 등장 빈도수 카운트
            feq[A[i]]++;
        }

        stack.push(0);


        for (int i = 1; i < A.length; i++) {
            while (!stack.isEmpty()) {
                if(feq[A[stack.peek()]] < feq[A[i]]){ //오등큰수 인 경우
                    answer[stack.pop()] = A[i];
                    if (stack.isEmpty()) { //오등큰수 비교 끝내고 스택이 비어있으면 현재 인덱스 스택에 넣어줌
                        stack.push(i);
                        break;
                    }
                }else { //오등큰수 아닌 경우
                    stack.push(i);
                    break;
                }
            }
        }

        while (!stack.isEmpty()) { //오등큰수가 없으면 -1
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
