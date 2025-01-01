package programmers.dp;

import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

    private static final List<Integer> fib = new ArrayList<>();

    public int fib(int n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }
}
