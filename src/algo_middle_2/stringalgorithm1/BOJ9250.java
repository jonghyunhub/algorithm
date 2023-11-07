package algo_middle_2.stringalgorithm1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Node {
    int valid;
    int[] children;
    int pi;
    ArrayList<Integer> indexes;

    Node() {
        valid = -1;
        children = new int[26];
        for (int i = 0; i < 26; i++) {
            children[i] = -1;
        }
        pi = -1;
        indexes = new ArrayList<Integer>();
    }
}

public class BOJ9250 {

    static ArrayList<Node> trie = new ArrayList<>();

    static int init() {
        Node x = new Node();
        trie.add(x);
        return (int) trie.size() - 1;
    }

    static void add(int node, String s, int index, int string_index) {
        if (index == s.length()) {
            trie.get(node).valid = string_index;
            return;
        }
        int c = s.charAt(index) - 'a';
        if (trie.get(node).children[c] == -1) {
            int next = init();
            trie.get(node).children[c] = next;
        }
        add(trie.get(node).children[c], s, index + 1, string_index);
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int root = init();
        int n = sc.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.next();
            add(root, a[i], 0, i);
        }
        Queue<Integer> q = new LinkedList<>();
        trie.get(root).pi = root;
        q.add(root);
        while (!q.isEmpty()) {
            int cur = q.remove();
            for (int i = 0; i < 26; i++) {
                int next = trie.get(cur).children[i];
                if (next == -1) continue;
                if (cur == root) {
                    trie.get(next).pi = root;
                } else {
                    int x = trie.get(cur).pi;
                    while (x != root && trie.get(x).children[i] == -1) {
                        x = trie.get(x).pi;
                    }
                    if (trie.get(x).children[i] != -1) {
                        x = trie.get(x).children[i];
                    }
                    trie.get(next).pi = x;
                }
                int pi = trie.get(next).pi;
                trie.get(next).indexes = new ArrayList<>(trie.get(pi).indexes);
                if (trie.get(next).valid != -1) {
                    trie.get(next).indexes.add(trie.get(next).valid);
                }
                q.add(next);
            }
        }
        int m = sc.nextInt();
        while (m-- > 0) {
            String s = sc.next();
            int node = root;
            boolean ok = false;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                while (node != root && trie.get(node).children[c] == -1) {
                    node = trie.get(node).pi;
                }
                if (trie.get(node).children[c] != -1) {
                    node = trie.get(node).children[c];
                }
                if (trie.get(node).indexes.size() > 0) {
                    ok = true;
                }
            }
            System.out.println(ok ? "YES" : "NO");
        }
    }
}
