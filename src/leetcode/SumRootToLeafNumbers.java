package leetcode;

import java.util.*;

// https://leetcode.com/problems/sum-root-to-leaf-numbers/
public class SumRootToLeafNumbers {
    public static void main(String[] args) {
        SumRootToLeafNumbers sumRootToLeafNumbers = new SumRootToLeafNumbers();
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode root = new TreeNode(1, left, right);
        int summed = sumRootToLeafNumbers.sumNumbers(root);
        System.out.println(summed);
    }

    public int sumNumbers(TreeNode root) {
        List<String> find = new ArrayList<>();
        findDfs(find, new StringBuilder(), root);
        return find.stream().mapToInt(Integer::valueOf).sum();
    }

    public void findDfs(List<String> result,
                        StringBuilder builder,
                        TreeNode now) {
        builder.append(now.val);
        if(now.left == null && now.right == null) {
            result.add(builder.toString());
            builder.setLength(builder.length() - 1);
            return;
        }
        if(now.left != null) {
            findDfs(result,builder, now.left);
        }
        if(now.right != null) {
            findDfs(result,builder, now.right);
        }
        builder.setLength(builder.length() - 1);
    }

    //  Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
