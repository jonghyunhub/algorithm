package boj;

import java.io.*;
import java.util.Stack;

public class BOJ17413 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> stack = new Stack<>();
        String sentence = br.readLine();
        sentence += '\n';
        boolean isTag = false;
        for (int i = 0; i < sentence.length(); i++) {

            if (sentence.charAt(i) == '<') {
                isTag = true;
                while (!stack.isEmpty()) {
                    bw.write(stack.pop());
                }
            }
            if (sentence.charAt(i) == '>') {
                isTag = false;
                bw.write(sentence.charAt(i));
                continue;
            }
            if (isTag) {
                bw.write(sentence.charAt(i));
                continue;
            }

            if (sentence.charAt(i) == ' ' || sentence.charAt(i) == '\n' ) {
                while (!stack.isEmpty()) {
                    bw.write(stack.pop());
                }
                bw.write(sentence.charAt(i));
            } else {
                stack.push(sentence.charAt(i));
            }

        }

        br.close();
        bw.flush();
        bw.close();
    }
}
