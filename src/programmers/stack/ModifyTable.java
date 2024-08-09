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
        final List<Character> table = initTable(n);
        final Stack<Integer> deletedRows = new Stack<>();
        final CommandProcessor commandProcessor = new CommandProcessor();
        for (String commandStr : cmd) {
            Command command = new Command(commandStr);
            cursor = commandProcessor.proceed(command, cursor, table, deletedRows);
        }
        return table.stream().map(Object::toString).collect(Collectors.joining());
    }

    public List<Character> initTable(int n) {
        final List<Character> table = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            table.add('O');
        }
        return table;
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

        public int proceed(Command command, int cursor, List<Character> table, Stack<Integer> deletedRows) {
            return operations.get(command.name).proceed(command, cursor, table, deletedRows);
        }
    }

    interface Operation {
        int proceed(Command command, int cursor, List<Character> table, Stack<Integer> deletedRows);
    }

    /**
     * 1. 이동하려는 값 : offset = -(command.value)
     * 2. 단, 중간에 삭제된 원소가 있으면 그 원소의 수만큼 offset의 절댓값 추가
     * => for문을 돌아서 cursor가 지나간 원소들 중에 삭제된 원소가 있으면 offset의 절댓값을 증가시킨다.
     * 3. offset + cursor > table.size() 이면 cursor = table.size() - 1
     */
    class UpOperation implements Operation {
        @Override
        public int proceed(Command command, int cursor, List<Character> table, Stack<Integer> deletedRows) {
            int offset = -(command.value);
            for (int i = cursor; i >= cursor - command.value; i--) {
                if (table.get(i) == 'X') {
                    offset--;
                }
            }
            return Math.max(cursor + offset, 0);
        }
    }


    /**
     * 1. 이동하려는 값 : offset = +(command.value)
     * 2. 단, 중간에 삭제된 원소가 있으면 그 원소의 수만큼 offset의 절댓값 추가
     * => for문을 돌아서 cursor가 지나간 원소들 중에 삭제된 원소가 있으면 offset의 절댓값을 증가시킨다.
     * 3. offset + cursor > table.size() 이면 cursor = table.size() - 1
     */
    class DownOperation implements Operation {
        @Override
        public int proceed(Command command, int cursor, List<Character> table, Stack<Integer> deletedRows) {
            int offset = command.value;
            for (int i = cursor; i <= cursor + command.value; i++) {
                if (table.get(i) == 'X') {
                    offset++;
                }
            }
            return cursor + offset >= table.size() ? table.size() - 1 : cursor + offset;
        }
    }

    /**
     * 1. cursor가 가리키는 원소의 값을 'X'로 변경
     * 2. cursor의 값을 deletedRows에 push
     * 3. cursor의 값을 1 증가한다.
     * 4. cursor가 '삭제되지 않은' 마지막 원소를 가리키고 있으면 cursor = '삭제되지 않은' 마지막 원소의 인덱스 - 1
     */
    class DeleteOperation implements Operation {
        @Override
        public int proceed(Command command, int cursor, List<Character> table, Stack<Integer> deletedRows) {
            int lastIndexOfNotDeleted = 0;
            for (int i = table.size()-1; i >= 0; i--) {
                if (table.get(i) == 'O') {
                    lastIndexOfNotDeleted = i;
                    break;
                }
            }
            deletedRows.push(cursor);
            table.set(cursor, 'X');
            return cursor == lastIndexOfNotDeleted ? lastIndexOfNotDeleted - 1 : cursor + 1;
        }
    }

    /**
     * 1. deletedRows에서 pop한 인덱스의 값을 'O'로 변경
     * 2. cursor의 값은 변경없음
     */
    class RestoreOperation implements Operation {
        @Override
        public int proceed(Command command, int cursor, List<Character> table, Stack<Integer> deletedRows) {
            if (!deletedRows.isEmpty()) {
                Integer pop = deletedRows.pop();
                table.set(pop, 'O');
            }
            return cursor;
        }
    }
}
