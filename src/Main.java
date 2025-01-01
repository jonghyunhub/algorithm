import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        double i = fractionKnapsackSolution(30);
        System.out.println("i = " + i);
    }

    public static double fractionKnapsackSolution(int weightLimit) {
        double answerValue = 0;
        List<Item> items = new ArrayList<>(List.of(
                new Item(10, 10),
                new Item(15, 12),
                new Item(20, 10),
                new Item(25, 8),
                new Item(30, 5)
        ));

        items.sort((o1, o2) -> {
            double v1 = (double) o1.value / o1.weight;
            double v2 = (double) o2.value / o2.weight;
            return Double.compare(v2, v1);
        });

        for (Item item : items) {
            if (item.weight <= weightLimit) {
                weightLimit -= item.weight;
                answerValue += item.value;
                continue;
            }

            if (weightLimit > 0) {
                double fraction = (double) weightLimit / item.weight;
                answerValue += (item.value * fraction);
                break;
            }
        }

        return answerValue;
    }

    static class Item {
        int weight;
        int value;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}