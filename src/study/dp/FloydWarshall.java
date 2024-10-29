package study.dp;

public class FloydWarshall {
    static int inf = 999999;
    // 배열 인덱스 1부터 N까지 사용을 위해 [N*N] 행렬을 사용시 -> [N+1][N+1] 배열을 만들고 0행, 0열은 사용하지 않고 1~N 행,열 사용
    static int[][] graph = {
            {0, 0, 0, 0, 0, 0},
            {0, 0, 1, inf, 1, 5},
            {0, 9, 0, 3, 2, inf},
            {0, inf, inf, 0, 4, inf},
            {0, inf, inf, 2, 0, 3},
            {0, 3, inf, inf, inf, 0}
    };
    // 새로 만든 테스트 케이스
    static int[][] graph2 = {
            {0, 0, 0, 0, 0, 0},
            {0, 0, inf, 8, 2, 5},
            {0, 2, 0, 3, 2, inf},
            {0, inf, inf, 0, 4, inf},
            {0, inf, 7, 2, 0, 3},
            {0, inf, 3, 1, inf, 0}
    };

    // 거쳐가는 최근 정점을 기록하는 배열
    static int[][] p = new int[6][6];

    public static void main(String[] args) {
        floydWarshall(graph2);
        printShortestPath(p);
    }

    public static void floydWarshall(int[][] graph) {
        int n = graph.length;

        for (int k = 1; k < n; k++) {
            for (int row = 1; row < n; row++) {
                for (int column = 1; column < n; column++) {
                    if (graph[row][column] > graph[row][k] + graph[k][column]) {
                        graph[row][column] = graph[row][k] + graph[k][column];
                        p[row][column] = k;
                    }
                }
            }
            System.out.println("--------------k: " + k + "---------------------");
            printResult(graph,p);
            System.out.println();
        }
    }

    public static void printMatrix(int[][] matrix) {
        int length = matrix.length;
        for (int row=1; row < length; row++) {
            System.out.print("[");
            for (int column = 1; column < length; column++) {
                printValue(matrix[row][column]);
                if (column != length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }

    private static void printValue(int value) {
        if (value == inf) {
            System.out.print("∞");
            return;
        }
        System.out.print(value);
    }

    private static void printResult(int[][] graph, int[][] p) {
        System.out.println("--------------graph--------------------");
        printMatrix(graph);
        System.out.println("--------------p------------------------");
        printMatrix(p);
    }

    private static void printShortestPath(int[][] graph) {
        for (int row = 1; row < graph.length; row++) {
            for (int column = 1; column < graph.length; column++) {
                System.out.print("path(" + row + "," + column + ") : " + row + " -> ");
                path(graph, row, column);
                System.out.println(column);
            }
        }
    }

    private static void path(int[][] graph ,int row, int column) {
        int value = graph[row][column];
        if (value != 0) {
            path(graph, row, value);
            System.out.print(value + " -> ");
            path(graph, value, column);
        }
    }
}
