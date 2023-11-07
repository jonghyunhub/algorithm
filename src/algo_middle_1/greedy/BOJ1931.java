package algo_middle_1.greedy;

import java.util.Arrays;
import java.util.Scanner;

class Meeting implements Comparable<Meeting> {
    int start, end;
    Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }
    public int compareTo(Meeting that) {
        if (this.end < that.end) {
            return -1;
        } else if (this.end == that.end) {
            if (this.start < that.start) {
                return -1;
            } else if (this.start == that.start) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}
public class BOJ1931 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Meeting[] a = new Meeting[n];
        for (int i=0; i<n; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            a[i] = new Meeting(start, end);
        }
        Arrays.sort(a);
        int last = -1;
        int ans = 0;
        for (int i=0; i<n; i++) {
            if (last <= a[i].start) {
                last = a[i].end;
                ans += 1;
            }
        }
        System.out.println(ans);
    }
}
