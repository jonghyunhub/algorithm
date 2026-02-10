package programmers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// https://school.programmers.co.kr/learn/courses/30/lessons/60061
public class Programmers60061 {
    public static void main(String[] args) {
        Programmers60061 programmers60061 = new Programmers60061();
        int n1 = 5;
        int[][] build_frame1 = {{1, 0, 0, 1}, {1, 1, 1, 1}, {2, 1, 0, 1}, {2, 2, 1, 1}, {5, 0, 0, 1}, {5, 1, 0, 1},
                {4, 2, 1, 1}, {3, 2, 1, 1}};
        int[][] solution = programmers60061.solution(n1, build_frame1);
        for (int[] arr : solution) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public int[][] solution(int n, int[][] build_frame) {
        Set<Build> builds = new HashSet<>();
        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int type = frame[2]; // 0 : 기둥, 1 : 보
            int command = frame[3]; // 0 : 삭제, 1 : 설치
            if (command == 0) {  // 삭제
                Build build = new Build(x, y, type);
                if (!builds.contains(build)) continue;
                builds.remove(build); // 삭제 먼저 하고 유효성 체크후 유효하지 않으면 롤백
                if (!validateBuilds(builds)) {
                    builds.add(build);
                }
            } else { // 설치
                Build build = new Build(x, y, type);
                if (builds.contains(build)) continue;
                builds.add(build); // 설치 먼저 하고 유효성 체크후 유효하지 않으면 롤백
                if (!validateBuilds(builds)) {
                    builds.remove(build);
                }
            }
        }
        return builds.stream()
                .map(b -> new int[]{b.x, b.y, b.type})
                .sorted((a, b) -> {
                    if (a[0] != b[0]) return a[0] - b[0];
                    if (a[1] != b[1]) return a[1] - b[1];
                    return a[2] - b[2];
                })
                .toArray(int[][]::new);
    }

    // 현재 구조물들의 상태를 체크해서 유효한지 검증
    public boolean validateBuilds(Set<Build> builds) {
        for (Build build : builds) {
            // 기둥인 경우
            if (build.type == 0) {
                boolean canBuild = false;
                if (build.y == 0) continue; // 바닥위에 있는 경우
                for (Build other : builds) {
                    if (other.equals(build)) continue;
                    // 다른 보 위에 있는 경우
                    if (other.type == 1 &&
                            other.y == build.y &&
                            (other.x + 1 == build.x || other.x == build.x)
                    ) {
                        canBuild = true;
                        break;
                    }
                    // 다른 기둥 위에 있는 경우
                    if (other.type == 0 &&
                            other.y == build.y - 1 &&
                            other.x == build.x
                    ) {
                        canBuild = true;
                        break;
                    }
                }
                if (!canBuild) return false;
            }
            // 보의 경우
            else {
                // 양쪽 끝 부분이 다른 보와 동시에 연결되어있는 경우
                boolean canBuild = false;
                boolean leftBo = builds.contains(new Build(build.x - 1, build.y, 1));
                boolean rightBo = builds.contains(new Build(build.x + 1, build.y, 1));
                if (leftBo && rightBo) {
                    canBuild = true;
                }

                for (Build other : builds) {
                    if (other.equals(build)) continue;
                    // 한쪽 끝 부분이 기둥 위에 있는 경우
                    if (other.type == 0 &&
                            other.y == build.y - 1 &&
                            (other.x == build.x || other.x == build.x + 1)
                    ) {
                        canBuild = true;
                        break;
                    }
                }
                if (!canBuild) return false;
            }
        }
        return true;
    }


    static class Build {
        int x;
        int y;
        int type; // 0 : 기둥, 1 : 보

        public Build(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Build build = (Build) o;
            return x == build.x && y == build.y && type == build.type;
        }

        public int hashCode() {
            return Objects.hash(x, y, type);
        }
    }
}
