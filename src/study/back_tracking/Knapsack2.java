package study.back_tracking;


public class Knapsack2 {
    private Item[] items;
    private int n;
    private int capacity;
    private boolean[] include;
    private boolean[] bestSet;
    private int maxProfit;
    private int currentWeight;
    private int currentProfit;
    private int level;
    private StringBuilder treeStructure;

    public Knapsack2(Item[] items, int capacity) {
        this.n = items.length - 1;
        this.items = items;
        this.capacity = capacity;
        this.include = new boolean[items.length];
        this.bestSet = new boolean[items.length];
        this.maxProfit = 0;
        this.currentWeight = 0;
        this.currentProfit = 0;
        this.level = 0;
        this.treeStructure = new StringBuilder();
    }

    private String getTreePrefix(int level, boolean isLast) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < level - 1; i++) {
            prefix.append("│   ");
        }
        if (level > 0) {
            prefix.append(isLast ? "└── " : "├── ");
        }
        return prefix.toString();
    }

    private void printNodeState(int i, int choiceNum, float bound, String status, String reason, boolean isLast) {
        String prefix = getTreePrefix(level, isLast);
        String nodeNumber = String.format("(%d, %d)", i, choiceNum);

        String nodeInfo = String.format("%s노드 %s [이익: $%d, 무게: %d, 한계: $%.2f]",
                prefix, nodeNumber, currentProfit, currentWeight, bound);

        String statusInfo = String.format(" - %s%s",
                status, reason.isEmpty() ? "" : " " + reason);

        System.out.println(nodeInfo + statusInfo);

        // 최적해 발견 시 출력
        if (currentWeight <= capacity && currentProfit > maxProfit) {
            String newBestPrefix = getTreePrefix(level, true) + "   ";
            System.out.printf("%s>>> 새로운 최적해 발견! (최대 이익: $%d)\n",
                    newBestPrefix, currentProfit);
        }
    }


    private boolean promising(int i, boolean isLast) {
        // 무게 초과로 실패하는 경우
        if (currentWeight > capacity) {
            printNodeState(i, include[i] ? 1 : 2, 0,
                    String.format("실패 (무게 초과: %d > %d) [현재 최대 이익: $%d]",
                            currentWeight, capacity, maxProfit),
                    "", isLast);
            return false;
        }

        int j = i + 1;
        float bound = currentProfit;
        int totalWeight = currentWeight;

        while (j <= n && totalWeight + items[j].getWeight() <= capacity) {
            totalWeight += items[j].getWeight();
            bound += items[j].getProfit();
            j++;
        }

        int k = j;
        if (k <= n) {
            bound += (capacity - totalWeight) * items[k].getRatio();
        }

        boolean isPromising = bound > maxProfit;
        if (!isPromising) {
            printNodeState(i, include[i] ? 1 : 2, bound,
                    String.format("실패 (한계값 $%.2f ≤ 현재 최대 이익 $%d)",
                            bound, maxProfit),
                    "", isLast);
        } else {
            printNodeState(i, include[i] ? 1 : 2, bound, "유망", "", isLast);
        }

        return isPromising;
    }

    private void knapsack(int i, boolean isLastChild) {
        level++;

        // 현재 해가 실행 가능하고 더 좋은 해인 경우 최적해 갱신
        if (currentWeight <= capacity && currentProfit > maxProfit) {
            maxProfit = currentProfit;
            System.arraycopy(include, 0, bestSet, 0, include.length);
        }

        if (i < n) {
            // 다음 아이템을 포함하는 경우
            include[i + 1] = true;
            currentWeight += items[i + 1].getWeight();
            currentProfit += items[i + 1].getProfit();

            if (promising(i + 1, false)) {
                knapsack(i + 1, false);
            }

            // 상태 복구
            currentWeight -= items[i + 1].getWeight();
            currentProfit -= items[i + 1].getProfit();

            // 다음 아이템을 포함하지 않는 경우
            include[i + 1] = false;
            if (promising(i + 1, true)) {
                knapsack(i + 1, true);
            }
        }

        level--;
    }
    public void solve() {
        System.out.println("=== 0-1 배낭 문제 해결 시작 ===");
        System.out.printf("배낭 용량: %d\n", capacity);
        System.out.println("\n사용 가능한 아이템:");
        for (int i = 1; i <= n; i++) {
            System.out.println("  " + items[i]);
        }
        System.out.println("\n=== 탐색 트리 ===\n");

        promising(0, false);  // 루트 노드 출력
        knapsack(0, true);
        printResult();
    }

    private void printResult() {
        System.out.println("\n=== 최종 결과 ===");
        System.out.println("최대 이익: $" + maxProfit);
        System.out.println("배낭 용량: " + capacity);
        System.out.println("\n선택된 아이템:");

        int totalWeight = 0;
        int totalProfit = 0;
        for (int i = 1; i <= n; i++) {
            if (bestSet[i]) {
                System.out.println("  " + items[i]);
                totalWeight += items[i].getWeight();
                totalProfit += items[i].getProfit();
            }
        }
        System.out.printf("\n총 무게: %d\n", totalWeight);
        System.out.printf("총 이익: $%d\n", totalProfit);
    }
    static class Item {
        private int id;
        private int weight;
        private int profit;
        private float ratio;

        public Item(int id, int weight, int profit) {
            this.id = id;
            this.weight = weight;
            this.profit = profit;
            this.ratio = (float)profit / weight;
        }

        public int getId() { return id; }
        public int getWeight() { return weight; }
        public int getProfit() { return profit; }
        public float getRatio() { return ratio; }

        @Override
        public String toString() {
            return String.format("Item %d: 무게=%d, 가치=%d, p/w=%.2f", id, weight, profit, ratio);
        }
    }

    public static void main(String[] args) {
        Item[] items1 = {
                new Item(0, 0, 0),      // dummy item
                new Item(1, 2, 40),
                new Item(2, 5, 30),
                new Item(3, 10, 50),
                new Item(4, 5, 10)
        };
        int capacity1 = 16;

        Item[] items2 = {
                new Item(0, 0, 0),     // dummy item
                new Item(1, 8, 100),   // p/w = 12.5
                new Item(2, 12, 180),  // p/w = 15
                new Item(3, 10, 120),  // p/w = 12
                new Item(4, 5, 60)     // p/w = 12
        };
        int capacity2 = 20;

        Knapsack2 ks = new Knapsack2(items2, capacity2);
        ks.solve();
    }
}