package boj.tree;

import java.util.*;

public class BOJ5639 {
    public static void main(String[] args) {
        final Map<Integer,Children> tree = new HashMap<>();
        final Scanner sc = new Scanner(System.in);
        int root = sc.nextInt();
        tree.put(root, new Children());
        while (sc.hasNext()) {
            final int now = sc.nextInt();
            addChild(root, now, tree);
        }
        postOrder(root, tree);
        sc.close();
    }

    static class Children {
        int left;
        int right;

        Children(){
            left = right = 0;
        }
    }

    public static void addChild(int cursor, int child, Map<Integer, Children> tree){
        Children orDefault = tree.getOrDefault(cursor, new Children());
        if(cursor > child) {
            if(orDefault.left == 0){
                orDefault.left = child;
                tree.put(cursor, orDefault);
                return;
            }
            addChild(orDefault.left , child, tree);
        }

        if(cursor < child) {
            if(orDefault.right == 0){
                orDefault.right = child;
                tree.put(cursor, orDefault);
                return;
            }
            addChild(orDefault.right , child, tree);
        }
    }

    // 후위 순회 : left -> right -> root
    public static void postOrder(int now, Map<Integer, Children> tree) {
        if(now == 0) return;
        Children children = tree.getOrDefault(now, new Children());
        int left = children.left;
        int right = children.right;
        postOrder(left, tree);
        postOrder(right,tree);
        System.out.println(now);
    }
}
