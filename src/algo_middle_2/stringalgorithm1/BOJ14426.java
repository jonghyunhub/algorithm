package algo_middle_2.stringalgorithm1;

import java.util.ArrayList;
import java.util.Scanner;

class TrieA {
    ArrayList<Node> trie;
    int root;

    TrieA() {
        trie = new ArrayList<Node>();
        root = init();
    }

    int init() {
        Node x = new Node();
        trie.add(x);
        return trie.size() - 1;
    }

    void add(int node, String s, int index) {
        if (index == s.length()) {
            trie.get(node).valid = true;
            return;
        }
        int c = s.charAt(index) - 'a';
        if (trie.get(node).children[c] == -1) {
            int next = init();
            trie.get(node).children[c] = next;
        }
        int child = trie.get(node).children[c];
        add(child, s, index + 1);
    }

    void add(String s) {
        add(root, s, 0);
    }

    boolean search(int node, String s, int index) {
        if (node == -1) return false;
        if (index == s.length()) return true;
        int c = s.charAt(index) - 'a';
        int child = trie.get(node).children[c];
        return search(child, s, index + 1);
    }

    boolean search(String s) {
        return search(root, s, 0);
    }

    class Node {
        int[] children;
        boolean valid;

        Node() {
            children = new int[26];
            for (int i = 0; i < 26; i++) {
                children[i] = -1;
            }
            valid = false;
        }
    }
}

public class BOJ14426 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        TrieA trie = new TrieA();
        while (n-- > 0) {
            trie.add(sc.next());
        }
        int ans = 0;
        while (m-- > 0) {
            if (trie.search(sc.next())) {
                ans += 1;
            }
        }
        System.out.println(ans);
    }
}
