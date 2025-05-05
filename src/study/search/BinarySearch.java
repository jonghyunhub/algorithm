package study.search;

import java.util.*;

public class BinarySearch {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int answer = input.nextInt();

        int left = 1;
        int right = Integer.MAX_VALUE - 2;

        while (left < right) {
            System.out.println(left + " " + right);
            int mid = left/2 + right/2;

            if(answer > mid){
                left = mid + 1;
                continue;
            }

            // answer <= mid
            right = mid;
        }

        System.out.println(left);
        input.close();
    }
}
