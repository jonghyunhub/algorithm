package programmers.array;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class VisitPathLength2 {

    public static void main(String[] args) {
        String dirs = "LLLLLRRRRRRRRRR";
        VisitPathLength2 solution = new VisitPathLength2();
        System.out.println(solution.solution(dirs));
    }


    public int solution(String dirs) {
        Set<Path> paths = new HashSet<>();
        // 처음에는 0,0을 넣고
        // 그 이후부터는 이전 좌표의 x2,y2를 넣는다
        Path path = new Path(0,0,0,0);
        for(char cmd : dirs.toCharArray()){
            int x2 = path.x2;
            int y2 = path.y2;
            if(cmd == 'L'){
                if(x2-1 < -5){
                    continue;
                }
                path = new Path(x2,y2,x2-1,y2);
            }
            if(cmd == 'R'){
                if(path.x2+1 > 5){
                    continue;
                }
                path = new Path(x2,y2, x2+1,y2);
            }
            if(cmd == 'U'){
                if(path.y2+1 > 5){
                    continue;
                }
                path = new Path(x2,y2,x2,y2+1);
            }
            if(cmd == 'D'){
                if(y2-1 < -5){
                    continue;
                }
                path = new Path(x2,y2,x2,y2-1);
            }
            paths.add(path);
        }
        return paths.size();
    }

    static class Path {
        private int x1, y1,x2, y2; // 클래스 맴버 변수는 할당시 0으로 초기화

        public Path(int x1,int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        // Set에 넣을때 같은 클래스임을 보장하는 equals 오버라이드 필요
        // equals의 함수 원형은 public boolean equals(Object)
        public boolean equals(Object obj){
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Path otherPath = (Path) obj;

            return (this.x1 == otherPath.x1
                    && this.y1 == otherPath.y1
                    && this.x2 == otherPath.x2
                    && this.y2 == otherPath.y2)
                    ||
                    (this.x1 == otherPath.x2
                            && this.y1 == otherPath.y2
                            && this.x2 == otherPath.x1
                            && this.y2 == otherPath.y1);
        }


        public int hashCode() {
            int hash1 = Objects.hash(x1, y1, x2, y2);
            int hash2 = Objects.hash(x2, y2, x1, y1);
            return Math.min(hash1, hash2);
        }
    }

}
