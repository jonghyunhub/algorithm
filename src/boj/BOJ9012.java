package boj;

import java.util.Scanner;

public class BOJ9012 {

    //스택 일반적인 구현
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int T = scanner.nextInt();
//        scanner.nextLine(); // 남아있는 개행 삭제
//        for (int i = 0; i < T; i++) {
//            String PS = scanner.nextLine();
//            boolean isTrue = true;
//            Stack<Character> stack = new Stack<>();
//            for (int j = 0; j < PS.length(); j++) {
//                if (PS.charAt(j) == '(') {
//                    stack.push('(');
//                } else {
//                    if (stack.isEmpty()) {
//                        isTrue = false;
//                        break;
//                    }
//                    stack.pop();
//                }
//            }
//            if (!stack.isEmpty()) {
//                isTrue = false;
//            }
//            if (isTrue) {
//                System.out.println("YES");
//            } else {
//                System.out.println("NO");
//            }
//        }
//
//        scanner.close();
//    }

    //스택을 직접 사용하지 않고 스택에 들어있는 원소 갯수로 구현
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        scanner.nextLine(); // 남아있는 개행 삭제
        for (int i = 0; i < T; i++) {
            String PS = scanner.nextLine();
            int stackSize = 0;
            boolean isTrue = true;
            for (int j = 0; j < PS.length(); j++) {
                if (PS.charAt(j) == '(') {
                    stackSize++;
                } else {
                    if (stackSize > 0) {
                        stackSize--;
                    } else {
                        isTrue = false;
                    }
                }
            }
            //PS 문자열 다 꺼낸 상황 -> 스택에 뭐가 남아있으면 오류!
            if (stackSize != 0) {
                isTrue = false;
            }

            if (isTrue) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }
        scanner.close();
    }
}
