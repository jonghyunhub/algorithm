package programmers.tree;

// 프로그래머스 알고리즘 책 '트리 순회' 문제
public class TreeTraversal {
    public static void main(String[] args) {
        final int[] nodes = {1, 2, 3, 4, 5, 6, 7};
        for (String s : solution(nodes)) {
            System.out.println(s);
        }
    }

    public static String[] solution(int[] nodes) {
        String[] result = new String[3];
        result[0] = preOrder(nodes, 0);
        result[1] = inOrder(nodes, 0);
        result[2] = postOrder(nodes, 0);
        return result;
    }

    private static String preOrder(int[] nodes, int index) {
        // index가 범위를 벗어나면 빈 문자열 반환
        if (index >= nodes.length) return "";

        return nodes[index] + " " +
                preOrder(nodes, index * 2 + 1) +
                preOrder(nodes, index * 2 + 2);
    }

    private static String inOrder(int[] nodes, int index) {
        // index가 범위를 벗어나면 빈 문자열 반환
        if (index >= nodes.length) return "";

        return inOrder(nodes, index * 2 + 1) +
                nodes[index] + " " +
                inOrder(nodes, index * 2 + 2);
    }

    private static String postOrder(int[] nodes, int index) {
        if (index >= nodes.length) return "";

        return postOrder(nodes, index * 2 + 1) +
                postOrder(nodes, index * 2 + 2) +
                nodes[index] + " ";
    }
}
