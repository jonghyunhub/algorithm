package study.back_tracking;


public class Knapsack {
    private Item[] items;           // 아이템 배열
    private int n;                  // 아이템의 개수
    private int capacity;           // 배낭의 최대 용량
    private boolean[] include;      // 현재 선택된 아이템들
    private boolean[] bestSet;      // 최적해의 아이템 선택
    private int maxProfit;          // 최대 이익
    private int currentWeight;      // 현재 무게
    private int currentProfit;      // 현재 이익

    public Knapsack(Item[] items, int capacity) {
        this.n = items.length - 1;  // 인덱스 0은 사용하지 않음
        this.items = items;
        this.capacity = capacity;
        this.include = new boolean[items.length];
        this.bestSet = new boolean[items.length];
        this.maxProfit = 0;
        this.currentWeight = 0;
        this.currentProfit = 0;
    }

    public static void main(String[] args) {
        // 인덱스 0은 사용하지 않음
        //example 5.6
        Item[] items1 = {
                new Item(0, 0, 0),      // dummy item
                new Item(1, 2, 40),
                new Item(2, 5, 30),
                new Item(3, 10, 50),
                new Item(4, 5, 10)
        };
        int capacity1 = 16;

        // 자작데이터
        Item[] items2 = {
                new Item(0, 0, 0),     // dummy item
                new Item(1, 8, 100),   // p/w = 12.5
                new Item(2, 12, 180),  // p/w = 15
                new Item(3, 10, 120),  // p/w = 12
                new Item(4, 5, 60)     // p/w = 12
        };
        int capacity2 = 20;

        Knapsack ks = new Knapsack(items2, capacity2);
        ks.solve();
    }

    private boolean promising(int i) {
        int j, k;
        int totalWeight;
        float bound;

        if (currentWeight >= capacity) {
            return false;
        }

        // 현재 노드의 한계값 계산
        j = i + 1;
        bound = currentProfit;
        totalWeight = currentWeight;

        // 남은 아이템들을 용량 제한까지 추가
        while (j <= n && totalWeight + items[j].getWeight() <= capacity) {
            totalWeight += items[j].getWeight();
            bound += items[j].getProfit();
            j++;
        }

        k = j;
        if (k <= n) {
            // 분할 가능한 배낭 문제의 상한 계산
            bound += (capacity - totalWeight) *
                    ((float) items[k].getProfit() / items[k].getWeight());
        }

        return bound > maxProfit;
    }

    private void knapsack(int i) {
        if (currentWeight <= capacity && currentProfit > maxProfit) {
            // 현재까지의 최적해 갱신
            maxProfit = currentProfit;
            System.arraycopy(include, 0, bestSet, 0, include.length);
        }

        if (promising(i) && i < n) {
            // 다음 아이템을 포함하는 경우
            include[i + 1] = true;
            currentWeight += items[i + 1].getWeight();
            currentProfit += items[i + 1].getProfit();
            knapsack(i + 1);

            // 상태 복구
            currentWeight -= items[i + 1].getWeight();
            currentProfit -= items[i + 1].getProfit();

            // 다음 아이템을 포함하지 않는 경우
            include[i + 1] = false;
            knapsack(i + 1);
        }
    }

    public void solve() {
        knapsack(0);
        printResult();
    }

    private void printResult() {
        System.out.println("최대 이익: " + maxProfit);
        System.out.println("배낭 용량: " + capacity);
        System.out.println("\n선택된 아이템:");

        int totalWeight = 0;
        for (int i = 1; i <= n; i++) {
            if (bestSet[i]) {
                System.out.println(items[i]);
                totalWeight += items[i].getWeight();
            }
        }
        System.out.println("\n총 무게: " + totalWeight);
    }

    static class Item {
        private int id;
        private int weight;
        private int profit;

        public Item(int id, int weight, int profit) {
            this.id = id;
            this.weight = weight;
            this.profit = profit;
        }

        public int getId() {
            return id;
        }

        public int getWeight() {
            return weight;
        }

        public int getProfit() {
            return profit;
        }

        @Override
        public String toString() {
            return String.format("Item %d: 무게=%d, 가치=%d", id, weight, profit);
        }
    }
}