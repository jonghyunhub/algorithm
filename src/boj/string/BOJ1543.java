package boj.string;

import java.io.*;

public class BOJ1543 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String docs = br.readLine();
        String input = br.readLine();
        int answer = 0;
//        for(int i=0; i<docs.length(); i++) {
//            int endIndex = i + input.length();
//            if(endIndex > docs.length()) break;
//            String substring = docs.substring(i, endIndex);
//            if(substring.equals(input)) {
//                answer++;
//                i = endIndex-1;
//            }
//        }

        // String indexOf 사용하면 해당 문자열에 검색하는 문자열이 포함되어있는지 확인가능
        int startIdx = 0;
        while (true) {
            int findIdx = docs.indexOf(input, startIdx);
            if(findIdx == -1) break;
            answer++;
            startIdx = findIdx +  input.length();
        }
        System.out.println(answer);
        br.close();
    }
}
