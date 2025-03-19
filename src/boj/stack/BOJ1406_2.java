package boj.stack;

import java.io.*;
import java.util.*;

/**
 * [문제 요구 사항 분석]
 * - 편집기에 입력되어있는 문자열이 주어진다. : 길이 N (1<= N <= 100,000) => O(N^2) 이상이면 시간초과
 * - 입력할 명령어의 갯수 : M (1<= M <=500,000) => 데이터 탐색 및 수정을 O(1)인 링크드 리스트 필요
 * - 문자열을 입력받아 수정하고 관리할 자료구조 : 링크드 리스트
 * - 맨 처음에 탐색시 O(N)으로 걸리지만 그 이후에는 이동을 한칸씩 함
 * - 데이터의 추가 삭제는 O(1)로 소요
 * -
 */
public class BOJ1406_2 {
    public static void main(String[] args) {
        final LinkedList<Character> list = new LinkedList<>();
        try (final InputStreamReader inputStreamReader = new InputStreamReader(System.in);
             final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            String line = bufferedReader.readLine();
            for (char c : line.toCharArray()) {
                list.add(c);
            }
            // 커서 맨 마지막으로 위치
            ListIterator<Character> cursor = list.listIterator(list.size());

            final int commandCount = Integer.parseInt(bufferedReader.readLine());
            for (int i = 0; i < commandCount; i++) {
                String command = bufferedReader.readLine();
                final char commandFirst = command.charAt(0);
                switch (commandFirst) {
                    case 'L':
                        if (cursor.hasPrevious()) {
                            cursor.previous();
                        }
                        break;
                    case 'D':
                        if (cursor.hasNext()) {
                            cursor.next();
                        }
                        break;
                    case 'B':
                        // listIterator는 최근에 접근한 원소를 삭제하므로
                        // 왼쪽 원소 삭제시 왼쪽 원소에 먼저 접근해야함
                        if (cursor.hasPrevious()) {
                            cursor.previous();
                            cursor.remove();
                        }
                        break;
                    case 'P':
                        char newChar = command.charAt(2);
                        cursor.add(newChar);
                        break;
                }
            }
            StringBuilder result = new StringBuilder();
            for (Character character : list) {
                result.append(character);
            }
            System.out.print(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
