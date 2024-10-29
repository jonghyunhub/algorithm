package programmers.dp;

public class Factorial {

    public static void main(String[] args) {
        final Factorial factorial = new Factorial();
        System.out.println(factorial.factorial(15));
    }

    public int factorial(int n) {
        if(n <= 1) return 1;
        return factorial(n-1) * n;
    }
}
