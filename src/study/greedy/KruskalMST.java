package study.greedy;

import java.util.*;

public class KruskalMST {

    public static void main(String[] args) {
        int[] counts = new int[2];  // [vertex count, edge count]를 담을 배열
        List<Edge> edges = initFigure4_7(counts);
        int countOfVertices = counts[0];  // 노드의 갯수(vertex)
        int countOfEdges = counts[1];  // 간선의 갯수

        Set<Edge> mst = kruskal(countOfVertices, countOfEdges, edges);

        System.out.println("MST 간선 목록(F집합):");
        int totalWeight = 0;
        for (Edge e : mst) {
            System.out.printf("(%d, %d) - weight: %d%n",
                    e.source, e.destination, e.weight);
            totalWeight += e.weight;
        }
        System.out.println("Total MST weight: " + totalWeight);

        System.out.println("\nMST 트리 구조 (루트: v1):");
        TreeNode root = buildMSTree(1, mst, countOfVertices);
        printTree(root, "", true);
    }

    // figure 4.7 데이터
    private static List<Edge> initFigure4_7(int[] counts) {
        List<Edge> edges = new ArrayList<>();
        counts[0] = 5;  // vertex count
        counts[1] = 8;  // edge count

        // 그래프의 모든 간선 추가 (양방향 간선이므로 한 번만 추가)
        edges.add(new Edge(1, 2, 1));  // v1-v2 weight 1
        edges.add(new Edge(2, 4, 6));  // v2-v4 weight 6
        edges.add(new Edge(1, 3, 3));  // v1-v3 weight 3
        edges.add(new Edge(2, 3, 3));  // v2-v3 weight 3
        edges.add(new Edge(3, 4, 4));  // v3-v4 weight 4
        edges.add(new Edge(3, 5, 2));  // v3-v5 weight 2
        edges.add(new Edge(4, 5, 5));  // v4-v5 weight 5
        return edges;
    }

    private static List<Edge> initTestData(int[] counts) {
        List<Edge> edges = new ArrayList<>();
        counts[0] = 5;  // 노드(정점) 갯수 -  vertex count
        counts[1] = 6;  // 간선 갯수 - edge count

        // 그래프의 모든 간선 추가 (양방향 간선이므로 한 번만 추가)
        edges.add(new Edge(1, 2, 1));
        edges.add(new Edge(1, 5, 3));
        edges.add(new Edge(2, 3, 6));
        edges.add(new Edge(2, 4, 2));
        edges.add(new Edge(3, 5, 4));
        edges.add(new Edge(4, 5, 5));

        return edges;
    }

    public static Set<Edge> kruskal(int n, int m, List<Edge> edges) {
        // 가중치 기준으로 오름차순 정렬
        Collections.sort(edges);

        // F 집합 초기화
        Set<Edge> F = new HashSet<>();

        // 서로소 집합 초기화
        DisjointSet ds = new DisjointSet(n);
        for (int i = 1; i <= n; i++) {
            ds.makeSet(i);
        }

        // 크루스칼 시작
        int edgesAdded = 0;
        int edgeIndex = 0;

        while (edgesAdded < n - 1) {
            Edge e = edges.get(edgeIndex++);
            int i = e.source;
            int j = e.destination;

            int p = ds.find(i);
            int q = ds.find(j);

            if (p != q) {
                ds.merge(p, q);
                F.add(e);
                edgesAdded++;
            }
        }

        return F;
    }


    private static TreeNode buildMSTree(int root, Set<Edge> mst, int n) {
        // 인접 리스트 생성
        List<List<Edge>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // MST의 간선들로 인접 리스트 구성
        for (Edge e : mst) {
            adj.get(e.source).add(new Edge(e.source, e.destination, e.weight));
            adj.get(e.destination).add(new Edge(e.destination, e.source, e.weight));
        }

        // 방문 체크를 위한 배열
        boolean[] visited = new boolean[n + 1];

        return buildTreeDFS(root, adj, visited);
    }

    private static TreeNode buildTreeDFS(int vertex, List<List<Edge>> adj, boolean[] visited) {
        TreeNode node = new TreeNode(vertex);
        visited[vertex] = true;

        for (Edge e : adj.get(vertex)) {
            if (!visited[e.destination]) {
                TreeNode child = buildTreeDFS(e.destination, adj, visited);
                child.edgeWeight = e.weight;
                node.children.add(child);
            }
        }

        return node;
    }

    private static void printTree(TreeNode node, String prefix, boolean isLast) {
        System.out.print(prefix);

        if (isLast) {
            System.out.print("└── ");
            prefix += "    ";
        } else {
            System.out.print("├── ");
            prefix += "│   ";
        }

        // 노드 출력 (루트가 아닌 경우 간선 가중치도 출력)
        if (node.edgeWeight > 0) {
            System.out.println("v" + node.vertex + " (weight: " + node.edgeWeight + ")");
        } else {
            System.out.println("v" + node.vertex);
        }

        for (int i = 0; i < node.children.size(); i++) {
            printTree(node.children.get(i), prefix, i == node.children.size() - 1);
        }
    }


    static class Edge implements Comparable<Edge> {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        // 가중치를 기준으로 오름차순 정렬
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static class DisjointSet {
        static class NodeType {
            int parent;
            int depth;

            NodeType() {
                parent = 0;
                depth = 0;
            }
        }

        NodeType[] universe; // U 배열

        public DisjointSet(int n) {
            universe = new NodeType[n + 1];
            for (int i = 0; i <= n; i++) {
                universe[i] = new NodeType();
            }
        }

        void makeSet(int i) {
            universe[i].parent = i;
            universe[i].depth = 0;
        }

        int find(int i) {
            int j = i;
            while (universe[j].parent != j) {
                j = universe[j].parent;
            }
            return j;
        }

        void merge(int p, int q) {
            if (universe[p].depth == universe[q].depth) {
                universe[p].depth += 1;
                universe[q].parent = p;
            } else if (universe[p].depth < universe[q].depth) {
                universe[p].parent = q;
            } else {
                universe[q].parent = p;
            }
        }


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("DisjointSet state:\n");
            for (int i = 1; i < universe.length; i++) {
                sb.append(String.format("Node %d: parent=%d, depth=%d\n",
                        i, universe[i].parent, universe[i].depth));
            }
            return sb.toString();
        }
    }

    static class TreeNode {
        int vertex;
        List<TreeNode> children;
        int edgeWeight;  // 부모와 연결된 간선의 가중치

        public TreeNode(int vertex) {
            this.vertex = vertex;
            this.children = new ArrayList<>();
            this.edgeWeight = 0;
        }
    }

}