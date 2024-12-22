package programmers.queue;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/42583
public class TruckInBridge {

    public static void main(String[] args) {
        TruckInBridge truckInBridge = new TruckInBridge();
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7, 4, 5, 6};
        System.out.println(truckInBridge.solution(bridge_length, weight, truck_weights));
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;

        // 대기 트럭 초기화
        Queue<Truck> waitTrucks = new ArrayDeque();
        for(int i=0; i<truck_weights.length; i++){
            waitTrucks.add(new Truck(truck_weights[i]));
        }

        Queue<Truck> trucksInBridge = new ArrayDeque<>();
        List<Truck> trucksOutOfBridge = new ArrayList<>();
        int weightsOfTrucksInBridge = 0;

        // 다리에 들어갈 수 있는 트럭과 다리 위의 트럭이 없을 때까지 반복
        while(!(waitTrucks.isEmpty() && trucksInBridge.isEmpty())){
            // 다리에서 나올 수 있는 트럭이 있으면 다리에서 트럭을 꺼낸다.
            while(!trucksInBridge.isEmpty() && trucksInBridge.peek().timeInBridge >= bridge_length){
                Truck poll = trucksInBridge.poll();
                trucksOutOfBridge.add(poll);
                weightsOfTrucksInBridge -= poll.weight;
            }

            // 다리에 들어갈 수 있는 트럭이 있으면 다리에 트럭을 넣는다.
            // 다리에 트럭이 들어가는건 1초에 한대씩 가능
            if(!waitTrucks.isEmpty()
                    && weightsOfTrucksInBridge + waitTrucks.peek().weight <= weight){
                Truck poll = waitTrucks.poll();
                trucksInBridge.add(poll);
                weightsOfTrucksInBridge += poll.weight;
            }

            // 다리에 있는 트럭들의 다리 위에서 지난 시간을 업데이트한다.
            trucksInBridge.stream().forEach(truck -> truck.timeInBridge++);

            time++;
        }
        return time;
    }

    public static class Truck {
        int weight;
        int timeInBridge = 0;

        public Truck(int weight){
            this.weight = weight;
        }
    }
}
