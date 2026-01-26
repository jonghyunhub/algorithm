package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1991
public class BOJ1991 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        Map<String, List<String>> tree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] split = bufferedReader.readLine().split(" ");
            List<String> children = tree.computeIfAbsent(split[0], k -> new ArrayList<>());
            children.add(split[1]);
            children.add(split[2]);
        }

        String root = "A";
        StringBuilder builder = new StringBuilder();
        preOrder(tree, builder, root);
        System.out.println(builder);
        builder.setLength(0);

        inOrder(tree, builder, root);
        System.out.println(builder);
        builder.setLength(0);

        postOrder(tree, builder, root);
        System.out.println(builder);
        builder.setLength(0);

    }

    // 부모 -> left -> right
    public static void preOrder(Map<String, List<String>> tree,
                         StringBuilder builder,
                         String now) {
        if(now == null || now.equals(".")) return;
        List<String> children = tree.getOrDefault(now, new ArrayList<>());
        builder.append(now);
        preOrder(tree, builder, children.get(0));
        preOrder(tree, builder, children.get(1));
    }

    // left -> 부모 -> right
    public static void inOrder(Map<String, List<String>> tree,
                                 StringBuilder builder,
                                 String now) {
        if(now == null || now.equals(".")) return;
        List<String> children = tree.getOrDefault(now, new ArrayList<>());
        inOrder(tree, builder, children.get(0));
        builder.append(now);
        inOrder(tree, builder, children.get(1));
    }

    // left -> right -> 부모
    public static void postOrder(Map<String, List<String>> tree,
                                StringBuilder builder,
                                String now) {
        if(now == null || now.equals(".")) return;
        List<String> children = tree.getOrDefault(now, new ArrayList<>());
        postOrder(tree, builder, children.get(0));
        postOrder(tree, builder, children.get(1));
        builder.append(now);
    }
}
