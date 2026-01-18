package leetcode;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/lru-cache/description/
public class LruCacheMain {

    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    public static void main(String[] args) {
//        int capacity = 2;
//        LRUCache cache = new LRUCache(capacity);
//        cache.put(1, 1);
//        cache.put(2, 2);
//        int i1 = cache.get(1);
//        cache.put(3, 3);
//        int i2 = cache.get(2);
//        cache.put(4, 4);
//        int i3 = cache.get(1);
//        int i4 = cache.get(3);
//        int i5 = cache.get(4);


//        int capacity = 1;
//        LRUCache cache = new LRUCache(capacity);
//        cache.put(2, 1);
//        int i = cache.get(2);

//        int capacity = 1;
//        LRUCache cache = new LRUCache(capacity);
//        cache.put(2, 1);
//        int i1 = cache.get(2);
//        cache.put(3, 2);
//        int i2 = cache.get(2);
//        int i3 = cache.get(3);


        int capacity = 2;
        LRUCache cache = new LRUCache(capacity);
        cache.put(2, 1);
        cache.put(2, 2);
        int i1 = cache.get(2);
        cache.put(1, 1);
        cache.put(4, 1);
        int i2 = cache.get(2);
    }


    static class LRUCache {
        int capacity;
        Map<Integer, Node> map = new HashMap<>();
        final Node head;
        final Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head = new Node(-1, -1); // 더미 노드 처리
            tail = new Node(-1, -1);

            head.next = tail;
            tail.prev = head;
        }

        /**
         * get(key): 키로 값을 찾고, 해당 항목을 "가장 최근 사용"으로 갱신
         * 키가 없음 : -1 반환 + 아무 작업 안 함
         * 키가 있음 : 값 반환 + 순서 갱신 (해당 노드를 맨 앞으로 이동)
         *
         * @param key : 저장하는 키
         * @return 저장된 값 리턴
         */
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = moveNodeToHead(key);
            return node.value;
        }

        private Node moveNodeToHead(int key) {
            Node node = map.get(key);

            if (head.next == node) {
                return node;
            }

            Node nowPrev = node.prev;
            Node nowNext = node.next;
            nowNext.prev = nowPrev;
            nowPrev.next = nowNext;

            Node headNext = head.next;
            headNext.prev = node;
            node.next = headNext;

            head.next = node;
            node.prev = head;

            return node;
        }

        /**
         * put(key, value): 새 항목 추가, 용량 초과 시 "가장 오래된 항목" 제거
         * 이미 존재 : 값 갱신 + 순서를 맨 앞으로 이동 => 같은 키로 인서트 하는 경우 override
         * 없음 + 용량 여유 : 맨 앞에 추가
         * 없음 + 용량 초과 : 맨 뒤 제거 → 맨 앞에 추가
         */
        public void put(int key, int value) {
            // 이미 존재 : 값 갱신 + 순서를 맨 앞으로 이동
            if (map.containsKey(key)) {
                map.get(key).value = value;
                moveNodeToHead(key);
                return;
            }

            // cache miss + 용량 부족: 맨 뒤 제거
            if (map.size() + 1 > capacity) {
                Node tailPrev = tail.prev;

                Node tailPrevPrev = tailPrev.prev;
                tailPrevPrev.next = tail;
                tail.prev = tailPrevPrev;

                map.remove(tailPrev.key);
            }

            // 맨 앞에 추가
            insertNodeToHead(key, value);
        }

        private void insertNodeToHead(int key, int value) {
            Node node = new Node(key, value);
            map.put(key, node);

            Node headNext = head.next;
            headNext.prev = node;
            node.next = headNext;

            head.next = node;
            node.prev = head;
        }
    }

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

}
