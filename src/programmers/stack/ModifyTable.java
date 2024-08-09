package programmers.stack;

import java.util.*;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/81303
public class ModifyTable {

    public static void main(String[] args) {
        final ModifyTable modifyTable = new ModifyTable();
        final int n = 8;
        final int k = 2;
        final String[] cmd = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        System.out.println(modifyTable.solution(n, k, cmd));
    }

    public String solution(int n, int k, String[] cmd) {
        int cursor = k;
        final LinkedList<Character> table = initTable(n);
        final Stack<Integer> deletedRows = new Stack<>();
        final CommandProcessor commandProcessor = new CommandProcessor();
        for (String commandStr : cmd) {
            Command command = new Command(commandStr);
            cursor = commandProcessor.proceed(command, cursor, table, deletedRows);
        }
        final List<Character> answer = initAnswer(n);
        deletedRows.forEach(row -> answer.set(row, 'X'));
        return answer.stream().map(Object::toString).collect(Collectors.joining());
    }

    public LinkedList<Character> initTable(int n) {
        final LinkedList<Character> table = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            table.add('O');
        }
        return table;
    }

    public ArrayList<Character> initAnswer(int n) {
        final ArrayList<Character> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            answer.add('O');
        }
        return answer;
    }

    class Command {
        private final String name;
        private final int value;

        public Command(String commandString) {
            String[] split = commandString.split(" ");
            this.name = split[0];
            this.value = initValue(split);
        }

        private int initValue(String[] split) {
            if (split.length > 1) {
                return Integer.parseInt(split[1]);
            }
            return 0;
        }
    }


    class CommandProcessor {
        private final Map<String, Operation> operations = new HashMap<>();

        public CommandProcessor() {
            operations.put("U", new UpOperation());
            operations.put("D", new DownOperation());
            operations.put("C", new DeleteOperation());
            operations.put("Z", new RestoreOperation());
        }

        public int proceed(Command command, int cursor, LinkedList<Character> table, Stack<Integer> deletedRows) {
            return operations.get(command.name).proceed(command, cursor, table, deletedRows);
        }
    }

    interface Operation {
        int proceed(Command command, int cursor, LinkedList<Character> table, Stack<Integer> deletedRows);
    }

    /**
     * 1. 이동하려는 값 : offset = -(command.value)
     * 2. 단, offset + cursor < 0 이면 cursor = 0
     */
    class UpOperation implements Operation {
        @Override
        public int proceed(Command command, int cursor, LinkedList<Character> table, Stack<Integer> deletedRows) {
            final int offset = -(command.value);
            return Math.max(cursor + offset, 0);
        }
    }


    /**
     * 1. 이동하려는 값 : offset = +(command.value)
     * 2. 단, offset + cursor >= table.size() 이면 cursor = table.size() - 1
     */
    class DownOperation implements Operation {
        @Override
        public int proceed(Command command, int cursor, LinkedList<Character> table, Stack<Integer> deletedRows) {
            final int offset = command.value;
            return cursor + offset >= table.size() ? table.size() - 1 : cursor + offset;
        }
    }

    /**
     * 1. cursor가 가리키는 LinkedList의 원소를 삭제
     * 2. cursor의 값을 deletedRows에 push
     * (LinkedList 자료구조에서는 삭제되면 자동으로 앞으로 당겨지기 때문에 cursor의 값은 변경없음)
     */
    class DeleteOperation implements Operation {
        @Override
        public int proceed(Command command, int cursor, LinkedList<Character> table, Stack<Integer> deletedRows) {
            deletedRows.push(cursor);
            table.remove(cursor);
            return cursor;
        }
    }

    /**
     * 1. deletedRows에서 pop한 인덱스의 값을 'O'로 변경
     * 2. cursor의 값은 변경없음
     */
    class RestoreOperation implements Operation {
        @Override
        public int proceed(Command command, int cursor, LinkedList<Character> table, Stack<Integer> deletedRows) {
            if (!deletedRows.isEmpty()) {
                Integer pop = deletedRows.pop();
                table.add(pop, 'O');
            }
            return cursor;
        }
    }
}
