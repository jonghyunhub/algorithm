package programmers.queue;

import java.util.LinkedList;

// https://school.programmers.co.kr/learn/courses/30/lessons/17680#
public class Cache {
    public static void main(String[] args) {
        final Cache cache = new Cache();
        int cacheSize4 = 4;
        String[] citiess4 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        int cacheSize7 = 3;
        String[] citiess7 = {"a", "b", "c", "d", "a"};
        int solution = cache.solution(cacheSize7, citiess7);
        System.out.println("solution = " + solution);
    }

    /**
     * [함수 인자 분석]
     * - cacheSize : 캐시 크기 (0~30)
     * - cities : 도시 이름 배열 (0~100,000)
     * <p>
     * [문제 요구사항 분석]
     * - 캐시 크기만큼 캐시에 쿼리 결과를 담아 둘 수 있다.
     * - 캐시 hit를 한 경우 실행시간은 1이다.
     * - 캐시 miss를 한 경우 실행시간은 5이다.
     * <p>
     * [케이스 분석]
     * case1) cacheSize = 3, cites = ["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"]
     * 1. Jeju : cacheMiss => +5 (5) cache = ["Jeju"]
     * 2. Pangyo : cacheMiss => +5 (10) cache = ["Jeju", "Pangyo"]
     * 3. Seoul : cacheMiss => +5 (15) cache = ["Jeju", "Pangyo", "Seoul"]
     * 4. New York : cacheMiss => +5 (20) cache = ["Pangyo", "Seoul", "New York"]
     * 5. LA :  cacheMiss => +5 (25) cache = ["Seoul", "New York", "LA"]
     * 6. Jeju :  cacheMiss => +5 (30) cache = ["New York", "LA", "Jeju"]
     * 7. Pangyo :  cacheMiss => +5 (35) cache = ["LA", "Jeju", "Pangyo"]
     * 8. Seoul :  cacheMiss => +5 (40) cache = ["Jeju", "Pangyo", "Seoul"]
     * 9. NewYork : cacheMiss => +5 (45) cache = ["Pangyo", "Seoul" , "NewYork"]
     * 10. LA : cacheMiss => +5 (50) cache = ["Seoul" , "NewYork", "LA"]
     * <p>
     * 캐시가 hit 하면 맨 앞으로 보내지는것에 주의하자.
     * case4) cacheSize = 5, cites = ["Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"]
     * 1. Jeju : cacheMiss => +5 (5) cache = ["Jeju"]
     * 2. Pangyo : cacheMiss => +5 (10) cache = ["Jeju", "Pangyo"]
     * 3. Seoul : cacheMiss => +5 (15) cache = ["Jeju", "Pangyo", "Seoul"]
     * 4. New York : cacheMiss => +5 (20) cache = ["Jeju", "Pangyo", "Seoul", "New York"]
     * 5. LA :  cacheMiss => +5 (25) cache = ["Jeju", "Pangyo", "Seoul", "New York", "LA"]
     * 6. SanFrancisco :  cacheMiss => +5 (30) cache = ["Pangyo", "Seoul", "New York", "LA", "SanFrancisco"]
     * 7. Seoul : cacheHit => +1 (31) cache = ["Pangyo", "New York", "LA", "SanFrancisco", "Seoul"]
     * 8. Rome : cacheMiss => +5 (36) cache = ["New York", "LA", "SanFrancisco", "Seoul", "Rome"]
     * 9. Paris : cacheMiss => +5 (41) cache = ["LA", "SanFrancisco", "Seoul", "Rome", "Paris"]
     * 10. Jeju : cacheMiss => +5 (46) cache = ["SanFrancisco", "Seoul", "Rome", "Paris", "Jeju"]
     * 11. NewYork : cacheMiss => +5 (51) cache = ["Seoul", "Rome", "Paris", "Jeju", "NewYork"]
     * 12. Rome : cacheHit => +1 (52) cache = ["Seoul", "Paris", "Jeju", "NewYork", "Rome"]
     */
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        final LinkedList<String> cache = new LinkedList<>(); // 캐시 순서 변경과 queue FIFO 성질 모두 사용 가능한 LinkedList 사용
        for (String city : cities) {
            String cityName = city.toUpperCase();

            // cache hit -> 현재 값을 큐의 맨 마지막으로 보내고 한칸씩 땡김
            if (cache.contains(cityName)) {
                answer++;
                cache.remove(cityName);
                cache.add(cityName);
                continue;
            }

            // cache miss
            answer += 5;

            // cacheSize 넘어선 경우 맨 처음에 사용한 캐시 삭제
            if (cache.size() >= cacheSize) {
                cache.poll();
            }

            // cacheSize가 0인 경우 대응
            if (cache.size() >= cacheSize) continue;

            cache.add(cityName);
        }

        return answer;
    }
}
