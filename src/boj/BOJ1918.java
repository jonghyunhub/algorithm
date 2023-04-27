package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1918 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();
        Stack<Character> operationStack = new Stack<>();
        for (Character character : operationStack) {
            if (character == '(') {

            } else if (character == '+'
                    || character == '-'
                    || character == '*'
                    || character == '/') {

            } else {
                System.out.print(character);
            }
        }
    }
}
