package programmers.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

// https://school.programmers.co.kr/learn/courses/30/lessons/49994
public class VisitPathLength {
    public static void main(String[] args) {
        String dirs1 = "ULURRDLLU";
        String dirs2 = "LULLLLLLU";
        String dirs3 = "LLLLLRRRRRRRRRR";
        VisitPathLength visitPathLength = new VisitPathLength();
        int solution = visitPathLength.solution(dirs3);// 7
        System.out.println("solution = " + solution);
    }

    /**
     * 방문한 길의 정의 : 이동하기 전의 좌표 -> 이동한 이후의 좌표 의 쌍
     * => {(x1, y1), (x2,y2)} : 두개의 좌표를 저장할 수 있는 객체 정의후 사용
     * 중복된 길은 제외해야하므로, HashSet에 길을 저장
     * -5 <= x1, y1, x2, y2 <= 5 (이동한 이후의 좌표가 범위를 벗어나면 set에 추가하지 않기)
     * 엣지 케이스 : {(x1, y1), (x2,y2)}, {(x2, y2), (x1,y1)} 이 케이스도 감안하기
     * 실제 이동 경로를 저장하고, 추후에 중복된 경로 제거
     * 처음부터 중복 제거하면 "LLLLLRRRRRRRRRR" 이 10 나와야하는데 5가 나옴
     */
    public int solution(String dirs) {
        List<Path> realPath = new ArrayList<>();
        HashSet<Path> visitedPath = new HashSet<>();
        Coordinate current = new Coordinate(0, 0);

        // 실제 이동한 경로 저장(범위 벗어나는건 제외)
        for (int i = 0; i < dirs.length(); i++) {
            char nextCommand = dirs.charAt(i);
            Coordinate next;
            try {
                next = current.nextCommand(nextCommand);
            } catch (IllegalArgumentException e) { // 범위를 벗어나는 경우는 제외
                continue;
            }
            Path path = new Path(current, next);
            realPath.add(path);
            current = next;
        }

        for (Path path : realPath) {
            Path reversedPath = path.reverse();
            if (visitedPath.contains(reversedPath)) { // 중복된 길은 제외
                continue;
            }
            visitedPath.add(path);
        }

        return visitedPath.size();
    }

    public static class Path {
        private final Coordinate before;
        private final Coordinate next;

        public Path(Coordinate before, Coordinate next) {
            this.before = before;
            this.next = next;
        }

        public Path reverse() {
            return new Path(next, before);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Path)) return false;
            Path path = (Path) o;
            return Objects.equals(before, path.before) && Objects.equals(next, path.next);
        }

        @Override
        public int hashCode() {
            return Objects.hash(before, next);
        }
    }

    public static class Coordinate {
        private final int x;
        private final int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Coordinate)) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public Coordinate(int x, int y) {
            if (x > 5 || x < -5 || y > 5 || y < -5) {
                throw new IllegalArgumentException();
            }
            this.x = x;
            this.y = y;
        }

        public Coordinate nextCommand(char operand) {
            if (operand == 'U') {
                return new Coordinate(x, y + 1);
            }
            if (operand == 'D') {
                return new Coordinate(x, y - 1);
            }
            if (operand == 'R') {
                return new Coordinate(x + 1, y);
            }
            if (operand == 'L') {
                return new Coordinate(x - 1, y);
            }
            throw new RuntimeException("Invalid operand");
        }
    }
}
