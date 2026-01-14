package leetcode;

import java.util.*;

public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3, new TreeNode(4), new TreeNode(5));
        TreeNode root1 = new TreeNode(1, left1, right1);
        /**
         * [1,2,3,null,null,4,5,6,7]
         * 1(idx=0)
         *        / \
         *       2   3
         * (idx=1)   (idx=2)
         *          / \
         *         4   5
         *    (idx=5) (idx=6)
         *        / \
         *       6   7
         *  (idx=7) (idx=8)
         */
        TreeNode left2 = new TreeNode(2, null, null);
        TreeNode right2 = new TreeNode(3, new TreeNode(4, new TreeNode(6), new TreeNode(7)), new TreeNode(5));
        TreeNode root2 = new TreeNode(1, left2, right2);

        Codec ser = new Codec();
        Codec deser = new Codec();
        String serialize = ser.serialize(root2);
        TreeNode ans = deser.deserialize(serialize);
        System.out.println(ans.val);
    }


    static public class Codec {

        /**
         * [1,2,3,null,null,4,5] : root -> left -> right 순으로 돌면서 list에 추가
         * 0,1,2,3,    4,  5, 6
         * Output: [1,2,3,null,null,4,5]
         * tree 문자열 표현 : i, i*2 +1, i*2 + 2 (i = level) <- 이런 인덱스 기반으로 표현하면 안됨
         * queue에 넣고 꺼내는 순서에서 하나 꺼낸 이후 다음에 나오는 2개가 자식
         */
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "";

            StringBuilder sb = new StringBuilder();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) {
                    sb.append("null,");
                } else {
                    sb.append(node.val).append(",");
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }

            if(sb.charAt(sb.length() - 1) == ','){
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }


        // Decodes your encoded data to tree.
        // now : i, left : i*2 + 1, right : i*2 +2
        // 재귀 방식으로 tree 생성하면 부모가 null이고 자식이 null이 아닌 경우 커버 불가능
        // -> BFS 방식으로 처리 필요
        // split 배열에서 순차적으로 인덱스 증가시키면서 읽기
        public TreeNode deserialize(String data) {
            if(data.isEmpty()) return null;
            String[] split = data.split(",");
            int idx = 0;
            TreeNode root = new TreeNode(Integer.parseInt(split[idx]));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node == null) continue;
                if(idx + 1 < split.length){
                    String leftVal = split[++idx];
                    if(!leftVal.equals("null")){
                        node.left = new TreeNode(Integer.parseInt(leftVal));
                        queue.add(node.left);
                    }
                }

                if(idx +1 < split.length){
                    String rightVal = split[++idx];
                    if(!rightVal.equals("null")){
                        node.right = new TreeNode(Integer.parseInt(rightVal));
                        queue.add(node.right);
                    }
                }
            }

            return root;
        }

    }

    static public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
