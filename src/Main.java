public class Main {

    public static void main(String[] args) {
        boolean abd = isPalindrome("aba");
        System.out.println(abd);
    }

    public static boolean isPalindrome(String s) {
        int i=0;
        boolean isPalindrome = true;
        for (int j = 0; j < (s.length()-1) / 2; j++) {
            if(s.charAt(i)!=s.charAt(s.length()-1-i))
                isPalindrome = false;
        }
        return isPalindrome;
    }
}