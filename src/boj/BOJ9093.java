package boj;

import java.io.*;
import java.util.Stack;

public class BOJ9093 {
    public static void main(String[] args) throws IOException {
        Stack<Character> stack = new Stack<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i=0; i<T; i++){
            String sentence = bf.readLine() + "\n";
            for(int j=0; j<sentence.length(); j++){
                char s = sentence.charAt(j);
                // 공백 혹은 개행문자 만나면 스택 비우고 출력
                if(s == ' ' || s == '\n'){
                    while (!stack.isEmpty()){
                        bw.write(stack.pop());
                    }
                    bw.write(s);
                }else {
                    stack.push(s);
                }
            }
        }
        bw.flush();
    }
}
