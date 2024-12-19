package programmers.stack;

import java.util.Stack;

public class ModifyTable4 {
    public static void main(String[] args) {
        final int n = 8;
        final int k = 2;
        final String[] cmd1 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        final String[] cmd2 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        final String[] cmd3 = {"C", "C", "C", "C", "C", "C", "C", "C"};
        final String[] cmd4 = {"C", "C", "C", "C", "C", "C", "C", "C", "Z", "Z", "Z", "Z", "Z", "Z", "Z", "Z"};
        final ModifyTable4 modifyTable = new ModifyTable4();
        System.out.println(modifyTable.solution(n, k, cmd4));
    }

    public static class LinkedList {
        Node head;
        int size;

        public LinkedList(int n) {
            head = new Node(-1, null, null); // 전부 삭제시 처리 편의를 위해 head는 값이 -1인 더미 노드로 처리
            Node now = head;
            for (int i = 0; i < n; i++) {
                now = addNext(i, now);
            }
            size = n;
        }

        public Node searchNext(int index, Node search) {
            for (int i = 0; i < index; i++) {
                if (search.next == null) {
                    return search;
                }
                search = search.next;
            }
            return search;
        }

        public Node searchPrev(int index, Node search) {
            for (int i = 0; i < index; i++) {
                if (search.prev == null) {
                    return search;
                }
                search = search.prev;
            }
            return search;
        }

        public Node addNext(int data, Node now) {
            Node newNode = new Node(data, now, now.next);
            if (now.next != null) {
                now.next.prev = newNode;
            }
            now.next = newNode;
            size++;
            return newNode;
        }

        public Node addPrev(int data, Node now) {
            Node newNode = new Node(data, now.prev, now);
            if (now.prev != null) {
                now.prev.next = newNode;
            }
            now.prev = newNode;
            size++;
            return newNode;
        }

        // 삭제된 경우 커서가 삭제된 행의 다음 행을 가리키도록 함
        // 마지막 원소를 삭제한 경우는 삭제한 원소의 이전 노드를 리턴
        public Node deleteNow(Node now) {
            if (now.prev != null) {
                now.prev.next = now.next;
            }
            if (now.next != null) {
                now.next.prev = now.prev;
            }
            size--;
            if (now.next != null) { // 삭제된 경우 커서가 삭제된 행의 다음 행을 가리키도록 함
                return now.next;
            }
            return now.prev; // 마지막 원소를 삭제한 경우 이전 노드 리턴
        }
    }

    public static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    public String solution(int n, int k, String[] cmd) {
        final Stack<Node> deleted = new Stack<>();
        LinkedList linkedList = new LinkedList(n);

        // 처음에 커서의 위치 이동
        Node cursor = linkedList.searchNext(k, linkedList.head.next);

        for (String commandStr : cmd) {
            if (commandStr.charAt(0) == 'U') {
                String[] s = commandStr.split(" ");
                int move = Integer.valueOf(s[1]);
                cursor = linkedList.searchPrev(move, cursor);
                continue;
            }
            if (commandStr.charAt(0) == 'D') {
                String[] s = commandStr.split(" ");
                int move = Integer.valueOf(s[1]);
                cursor = linkedList.searchNext(move, cursor);
                continue;
            }
            if (commandStr.equals("C")) {
                Node node = linkedList.deleteNow(cursor);
                deleted.push(cursor);
                cursor = node;
                continue;
            }
            if (commandStr.equals("Z")) {
                Node pop = deleted.pop();
                if(pop.next!=null){
                    pop.next.prev = pop;
                }
                if(pop.prev!=null){
                    pop.prev.next = pop;
                }
                linkedList.size++;
            }
        }
        // 0부터 n-1까지 순회하면서 포함되어있으면 O, 없으면 X로 표시
        StringBuilder answer = new StringBuilder();
        int index = 0;
        Node search = linkedList.head.next;
        while (index < n) {
            if (search != null && search.data == index) {
                answer.append("O");
                search = search.next;
            } else {
                answer.append("X");
            }
            index++;
        }

        return answer.toString();
    }
}
