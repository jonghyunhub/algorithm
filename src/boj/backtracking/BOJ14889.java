package boj.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

// https://www.acmicpc.net/problem/14889
public class BOJ14889 {
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] arr = new int[n][n];
        for(int i=0;i<n;i++) {
            String[] split = bufferedReader.readLine().split(" ");
            for(int j=0;j<n;j++) {
                arr[i][j] = Integer.parseInt(split[j]);
            }
        }

        backTracking(arr, n, new HashSet<>(), 0);
        System.out.println(answer);
    }

    public static void backTracking(int[][] arr,
                                    int n,
                                    Set<Integer> team,
                                    int idx) {
        if(team.size() > n/2) return;
        if (team.size() == n / 2) {
            int teamValue = makeTeamValue(arr, team);
            Set<Integer> otherTeam = new HashSet<>();
            for(int i=0;i<n;i++) {
                if(team.contains(i)) continue;
                otherTeam.add(i);
            }
            int otherTeamValue = makeTeamValue(arr, otherTeam);
            answer = Math.min(answer, Math.abs(teamValue - otherTeamValue));
            return;
        }

        for(int i=idx;i<n;i++) {
            if(team.contains(i)) continue;
            team.add(i);
            backTracking(arr, n, team, i+1);
            team.remove(i);
        }
    }


    public static int makeTeamValue(int[][] arr,
                                    Set<Integer> team) {
        int answer = 0;
        for (int member1 : team) {
            for (int member2 : team) {
                if(member1 == member2) continue;
                answer += arr[member1][member2];
            }
        }
        return answer;
    }
}
