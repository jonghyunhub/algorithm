package programmers.graph;

import java.util.*;

public class DFS {
    public static void main(String[] args) {
        int n1 = 5;
        String start1 = "1";
        String[][] graph1 = {
                {"1", "2"},
                {"2", "3"},
                {"3", "4"},
                {"4", "5"}
        };

        int n2 = 6;
        String start2 = "1";
        String[][] graph2 = {
                {"1", "2"},
                {"1", "3"},
                {"2", "4"},
                {"2", "5"},
                {"3", "6"},
                {"5", "6"}
        };
        DFS dfs = new DFS();
        String[] answers = dfs.solutionByRecursive(n2, start2, graph2);
        System.out.print("[");
        for (int i = 0; i < answers.length; i++) {
            System.out.print(answers[i]);
            if (i == answers.length - 1) break;
            System.out.print(", ");
        }
        System.out.println("]");
    }

    /**
     * 1. start 노드를 스택에 넣는다.
     * 2. 스택이 비어있지 않을때까지 스택에서 하나씩 꺼낸다.
     * 3. 꺼낸 노드가 방문 처리가 되어있지 않으면 방문처리
     * 4. 꺼낸 노드에서 이동할수 있는 노드들을 스택에 담는다.
     * 5. 2~4의 과정을 순회한 이후 순회한 노드들의 배열을 리턴한다.
     *
     * @param n
     * @param start
     * @param graph
     * @return
     */
    public String[] solutionByStack(int n, String start, String[][] graph) {
        final Stack<String> stack = new Stack<>();
        final List<String> visited = new ArrayList<>();
        final Map<String, List<String>> graphList = new HashMap<>(n);

        // 그래프 인접 리스트 초기화
        for (String[] arr : graph) {
            List<String> orDefault = graphList.getOrDefault(arr[0], new ArrayList<>());
            orDefault.add(arr[1]);
            graphList.put(arr[0], orDefault);
        }

        stack.add(start);

        while (!stack.isEmpty()) {
            String now = stack.pop();
            // 현재 노드를 방문하지 않았다면 노드에 추가
            if (!visited.contains(now)) visited.add(now);
            for (String node : graphList.getOrDefault(now, new ArrayList<>())) {
                stack.add(node);
            }
        }

        return visited.stream().toArray(String[]::new);
    }

    public String[] solutionByRecursive(int n, String start, String[][] graph) {
        final List<String> visited = new ArrayList<>();
        final Map<String, List<String>> graphList = new HashMap<>(n);

        // 그래프 인접 리스트 초기화
        for (String[] arr : graph) {
            List<String> orDefault = graphList.getOrDefault(arr[0], new ArrayList<>());
            orDefault.add(arr[1]);
            graphList.put(arr[0], orDefault);
        }

        recursiveDfs(start, graphList, visited);
        return visited.stream().toArray(String[]::new);
    }

    public void recursiveDfs(String node, Map<String, List<String>> graph, List<String> visited) {
        // 현재 노드를 방문하지 않은 경우에만 방문 처리
        if (!visited.contains(node)) visited.add(node);
        // 없는 경우에는 조기 종료 해야하지만 여기서는 getOrDefault로 빈 list를 가져와서 처리
        List<String> adjacencyNodes = graph.getOrDefault(node, new ArrayList<>());
        for (String nextNode : adjacencyNodes) {
            recursiveDfs(nextNode, graph, visited);
        }
    }
}
