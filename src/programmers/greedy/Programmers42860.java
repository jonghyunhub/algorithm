package programmers.greedy;

// https://school.programmers.co.kr/learn/courses/30/lessons/42860
public class Programmers42860 {

    public static void main(String[] args) {
        Programmers42860 programmers42860 = new Programmers42860();
        String name2 = "JAN";
        int solution = programmers42860.solution(name2);
        System.out.println(solution);
    }

    public int solution(String name) {
        int answer = 0;
        int n = name.length();
        StringBuilder target = new StringBuilder();
        for (int i = 0; i < n; i++) {
            target.append("A");
        }

        // 알파벳 변환 비용
        for (int i = 0; i < n; i++) {
            char nameNow = name.charAt(i);
            answer += Math.min(nameNow - 'A', 'Z' - nameNow + 1);
        }

        // 커서 이동 최솟값 계산 (각 i에 대해 방향을 바꾸었을때 커서 비용 계산)
        int minMove = n - 1; // 기본값: 오른쪽으로 끝까지
        for (int i = 0; i < n; i++) {
            // i 이후 연속된 A 구간의 끝 탐색
            int next = i + 1;
            while (next < n && name.charAt(next) == 'A') {
                next++;
            }
            // 전략1: 오른쪽으로 i까지 갔다가 왼쪽으로 돌아서 (n-next) 처리
            int moveA = i * 2 + (n - next);
            // 전략2: 왼쪽으로 (n-next)칸 먼저 갔다가 오른쪽으로 i까지 처리
            int moveB = i + (n - next) * 2;

            minMove = Math.min(minMove, Math.min(moveA, moveB));
        }

        return answer + minMove;
    }
}
