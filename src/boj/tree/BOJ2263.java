package boj.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.acmicpc.net/problem/2263
public class BOJ2263 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());

        // in-order => left -> root -> right
        /**
         * in-order 1,2,3 이 아래와 같은 세개의 트리 모두 가능
         *     2          1           3
         *    / \          \         /
         *   1   3          2       2
         *                   \     /
         *                    3   1
         */
        String[] split = bufferedReader.readLine().split(" ");
        List<Integer> inOrderArr = new ArrayList<>();
        for (int i=0; i<n; i++) {
            inOrderArr.add(Integer.parseInt(split[i]));
        }


        /**
         *  post-order => left -> right -> root (루트를 알 수 있음 + 숫자 중복 X)
         1. post-order 에서 구한 root 를 통해 트리 복원 (post-order의 맨 마지막 원소는 => 모든 서브 트리의 루트!!)
         2. 복원한 트리에서 pre-order 구하기
         ---------------------------------------------
         * in-order에서 루트 기준으로 알 수 있는 것
         * - 왼쪽에 있는 범위 전체가 왼쪽 서브트리에 속한다
         * - 오른쪽에 있는 범위 전체가 오른쪽 서브트리에 속한다
         * => 그 범위 안에서 누가 루트(직접 자식)인지는 모름
         *         5(root)       => in-order: 1 3 4 5 7
         *        / \            => post-order: 1 4 3 7 5
         *       3   7           5(root),7(right-root),3(left-root), 4(right-root), 1(left-root) 순으로 순회 (but post-order 만으로 트리 결정 x)
         *      / \
         *     1   4
         */
        List<Integer> postOrderArr = new ArrayList<>();
        split = bufferedReader.readLine().split(" ");
        for (int i=0; i<n; i++) {
            postOrderArr.add(Integer.parseInt(split[i]));
        }
        int root = postOrderArr.get(postOrderArr.size() - 1);
        Map<Integer, List<Integer>> tree = new HashMap<>();
        makeTree(tree, root, inOrderArr, postOrderArr);

        // pre-order 구하기 => root -> left -> right
        StringBuilder builder = new StringBuilder();
        preOrder(tree, root, builder);
        builder.setLength(builder.length()-1); // 마지막 공백 하나 삭제
        System.out.println(builder);
    }

    public static void makeTree(Map<Integer, List<Integer>> tree, int root, List<Integer> inOrder, List<Integer> postOrder) {
        if(root == -1) return;
        int rootIdx = -1;
        for (int i = 0; i < inOrder.size(); i++) {
            if(root == inOrder.get(i)) rootIdx = i;
        }
        if(rootIdx == -1) return;
        List<Integer> children = tree.computeIfAbsent(root, k -> new ArrayList<>());
        Integer right = -1;
        if (!postOrder.isEmpty()) {
            right = postOrder.get(postOrder.size() - 1);
            postOrder.remove(postOrder.size() - 1);
        }
        Integer left = -1;
        if (!postOrder.isEmpty()) {
            left = postOrder.get(postOrder.size() - 1);
            postOrder.remove(postOrder.size() - 1);
        }
        children.add(left);
        children.add(right);
        makeTree(tree, right, inOrder, postOrder);
        makeTree(tree, left, inOrder, postOrder);
    }

    // pre-order 구하기 => root -> left -> right
    public static void preOrder(Map<Integer, List<Integer>> tree, int root, StringBuilder builder) {
        if(root == -1) return;
        builder.append(root).append(" ");
        List<Integer> children = tree.getOrDefault(root, new ArrayList<>());
        for (int child : children) {
            if(child == -1) continue;
            preOrder(tree, child, builder);
        }
    }
}
