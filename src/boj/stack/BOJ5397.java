package boj.stack;

import java.io.*;
import java.util.*;

/**
 * 각 입력의 길이는 (1 ≤ L ≤ 1,000,000)
 * 백스페이스 입력시 '-' -> 앞의 문자가 존재하면 삭제
 */
public class BOJ5397 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int lineNum = Integer.parseInt(br.readLine());
            for (int i = 0; i < lineNum; i++) {
                final String input = br.readLine();
                final LinkedList<Character> list = new LinkedList<>();
                final ListIterator<Character> cursor = list.listIterator(0);
                for (char c : input.toCharArray()) {
                    switch (c) {
                        case '<':
                            if (cursor.hasPrevious()) {
                                cursor.previous();
                            }
                            break;
                        case '>':
                            if (cursor.hasNext()) {
                                cursor.next();
                            }
                            break;
                        case '-':
                            if (cursor.hasPrevious()) {
                                cursor.previous();
                                cursor.remove();
                            }
                            break;
                        default:
                            cursor.add(c);
                    }
                }
                final StringBuilder sb = new StringBuilder();
                for (Character c : list) {
                    sb.append(c);
                }
                System.out.println(sb);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
