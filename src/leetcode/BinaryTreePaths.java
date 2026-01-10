package leetcode;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/binary-tree-paths/description/
public class BinaryTreePaths {
    public static void main(String[] args) {
        // root = [1,2,3,null,5]
        TreeNode left = new TreeNode(2, null, new TreeNode(5));
        TreeNode right = new TreeNode(3, null, null);
        TreeNode root = new TreeNode(1, left, right);
        List<String> answers = new BinaryTreePaths().binaryTreePaths(root);
        System.out.println(answers);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> answers = new ArrayList<>();
        dfs(root, answers, new ArrayList<>());
        return answers;
    }

    public void dfs(TreeNode now, List<String> result, List<Integer> path) {
        if(now.left == null && now.right == null){
            path.add(now.val);
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < path.size(); i++) {
                int num = path.get(i);
                builder.append(num);
                if(i < path.size()-1) builder.append("->");
            }
            result.add(builder.toString());
            path.remove(path.size()-1);
            return;
        }

        path.add(now.val);
        if(now.left != null){
            dfs(now.left, result, path);
        }

        if (now.right != null) {
            dfs(now.right, result, path);
        }

        path.remove(path.size() - 1);
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
