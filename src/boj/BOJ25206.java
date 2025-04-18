package boj;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BOJ25206 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final Map<String, Double> scoreMap = new HashMap<>();
        scoreMap.put("A+", 4.5);
        scoreMap.put("A0", 4.0);
        scoreMap.put("B+", 3.5);
        scoreMap.put("B0", 3.0);
        scoreMap.put("C+", 2.5);
        scoreMap.put("C0", 2.0);
        scoreMap.put("D+", 1.5);
        scoreMap.put("D0", 1.0);
        scoreMap.put("F", 0.0);
        double creditTotal = 0;
        double totalScore = 0.0;
        for (int i = 0; i < 20; i++) {
            String input = sc.nextLine();
            String[] split = input.split(" ");
            double credit = Double.parseDouble(split[1]);
            String score = split[2];
            if (score.equals("P")) continue;
            creditTotal += credit;
            totalScore += credit * scoreMap.get(score);
        }
        Double averageScore = totalScore / creditTotal;
        System.out.println(averageScore);
        sc.close();
    }
}
