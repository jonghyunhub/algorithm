package algo_middle_2.stringalgorithm1;

import java.util.ArrayList;
import java.util.Scanner;

class TrieB {
    class Node {
        int[] children;
        boolean valid;

        Node() {
            children = new int[2];
            for (int i = 0; i < 2; i++) {
                children[i] = -1;
            }
            valid = false;
        }
    }

    ;
    ArrayList<Node> trie = new ArrayList<>();
    int root;

    int init() {
        Node x = new Node();
        trie.add(x);
        return (int) trie.size() - 1;
    }

    TrieB() {
        root = init();
    }

    void add(int node, int num, int bit) {
        if (bit == -1) {
            trie.get(node).valid = true;
            return;
        }
        int c = (num >> bit) & 1;
        if (trie.get(node).children[c] == -1) {
            int next = init();
            trie.get(node).children[c] = next;
        }
        add(trie.get(node).children[c], num, bit - 1);
    }

    void add(int num) {
        add(root, num, 31);
    }

    int query(int node, int num, int bit) {
        if (bit == -1) return 0;
        int c = (num >> bit) & 1;
        c = 1 - c;
        if (trie.get(node).children[c] == -1) {
            c = 1 - c;
        }
        if (trie.get(node).children[c] == -1) {
            return 0;
        }
        int next = 0;
        if (c == 1) next = 1 << bit;
        return next | query(trie.get(node).children[c], num, bit - 1);
    }

    int query(int num) {
        return query(root, num, 31);
    }
}

public class BOJ13505 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0;
        TrieB trie = new TrieB();
        for (int i = 0; i < n; i++) {
            int num = sc.nextInt();
            trie.add(num);
            int temp = (trie.query(num) ^ num);
            if (ans < temp) ans = temp;
        }
        System.out.println(ans);
    }
}
