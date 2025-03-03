package programmers.kotlin

import java.util.*;

/**
[문제 분석]
1. 다리의 길이, 다리의 최대 하중 무게, 대기 트럭의 갯수가 주어진다.
2. 각각의 트럭은 다리의 길이만큼의 초가 지나야 다리를 건널 수 있다.
3. 다리 위의 모든 트럭의 무게 <= 최대 하중 까지 버틸 수 있다.
4. 이때, 모든 트럭이 다리를 지나는 최소 시간을 구하면 된다.
5. 다리에 다른 값이 존재하지 않으면 다리를 나타내는 큐에 0을 넣어서 모델링한다.
 */
class TrucksInBridge {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 0
        val bridge : Queue<Int> = ArrayDeque<Int>(bridge_length);
        val trucks : Queue<Int> = ArrayDeque<Int>();
        for(item in truck_weights){
            trucks.add(item);
        }
        for(i in 0 until bridge_length) bridge.add(0) // 비어있는거 표현

        // 현재 다리 무게 계산
        var weightInBridge = 0
        for(itemWeight in bridge){
            weightInBridge += itemWeight
        }

        // 남은 트럭이 있거나, 다리에 트럭이 존재하면 계속 반복
        while(!trucks.isEmpty() || weightInBridge != 0){
            val poll = bridge.poll()
            if(poll > 0) weightInBridge -= poll // 트럭이 다리에서 나오면 전체 무게에서 빼기

            // 다리에 못들어가면 다리에 0(빈값 넣기)
            if(trucks.isEmpty()){
                bridge.add(0)
                answer++
                continue
            }

            // 현재 다리 무게 + 다리에 들어가려는 트럭의 무게 <= 전체 하중인지 체크
            if(weightInBridge + trucks.peek() > weight){
                bridge.add(0)
                answer++
                continue
            }

            val truck = trucks.poll()
            bridge.add(truck)
            weightInBridge += truck
            answer++
        }
        return answer
    }
}


fun main() {
    val solution = TrucksInBridge()
    val bridge_length1 = 2
    val weight1 = 10
    val truck_weights1 = intArrayOf(7,4,5,6)

    val bridge_length2 = 100
    val weight2 = 100
    val truck_weights2 = intArrayOf(10)

    val result = solution.solution(bridge_length2, weight2, truck_weights2)
    print(result)
}
