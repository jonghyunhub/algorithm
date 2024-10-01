package programmers.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/81303
public class ModifyTable2 {

    public static void main(String[] args) {
        final ModifyTable2 modifyTable = new ModifyTable2();
        final int n = 4;
        final int k = 3;
        final String[] cmd1 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        final String[] cmd2 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        final String[] cmd3 = {"C", "C", "C", "C"};
        System.out.println(modifyTable.solution(n, k, cmd3));
    }

    public String solution(int n, int k, String[] cmd) {
        int cursor = k;
        final ArrayList<Character> table = initTable(n);
        final Stack<Integer> deletedRows = new Stack<>();
        final List<Command> commands = Command.initCommands(cmd);
        for (Command command : commands) {
            cursor = command.proceed(cursor, table, deletedRows);
        }
        return table.stream().map(Object::toString).collect(Collectors.joining());
    }

    private ArrayList<Character> initTable(int n) {
        final ArrayList<Character> table = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            table.add('O');
        }
        return table;
    }

    public static abstract class Command {
        abstract int proceed(int cursor, ArrayList<Character> table, Stack<Integer> deletedRows);

        public static Command initCommand(String commandStr) {
            String[] splitCommand = commandStr.split(" ");
            return switch (splitCommand[0]) {
                case "U" -> new UpCommand(Integer.parseInt(splitCommand[1]));
                case "D" -> new DownCommand(Integer.parseInt(splitCommand[1]));
                case "C" -> new DeleteCommand();
                case "Z" -> new RestoreCommand();
                default -> throw new IllegalArgumentException("Invalid command");
            };
        }

        public static List<Command> initCommands(String cmd[]) {
            return Arrays.stream(cmd).map(Command::initCommand).collect(Collectors.toList());
        }
    }


    public static class UpCommand extends Command {
        private int offset;

        public UpCommand(int offset) {
            this.offset = offset;
        }

        @Override
        public int proceed(int cursor, ArrayList<Character> table, Stack<Integer> deletedRows) {
            for (int i = 0; i < offset; i++) {
                if (cursor - i == 0) {
                    return 0;
                }
                if (table.get(cursor - i) == 'X') {
                    offset++;
                }
            }
            return cursor - offset;

        }
    }


    public static class DownCommand extends Command {
        private int offset;

        public DownCommand(int offset) {
            this.offset = offset;
        }

        @Override
        public int proceed(int cursor, ArrayList<Character> table, Stack<Integer> deletedRows) {
            for (int i = 0; i < offset; i++) {
                if (cursor + i == table.size() - 1) {
                    return table.size() - 1;
                }
                if (table.get(cursor + i) == 'X') {
                    offset++;
                }
            }
            return offset + cursor;
        }

    }

    public static class DeleteCommand extends Command {
        @Override
        public int proceed(int cursor, ArrayList<Character> table, Stack<Integer> deletedRows) {
            deletedRows.push(cursor);
            table.set(cursor, 'X');

            if (cursor == table.size() - 1) {
                while (table.get(cursor) == 'X' && cursor > 0) {
                    cursor--;
                }
                return cursor;
            }

            while (table.get(cursor) == 'X' && cursor < table.size() - 1) {
                cursor++;
            }

            return cursor;
        }
    }

    public static class RestoreCommand extends Command {
        @Override
        public int proceed(int cursor, ArrayList<Character> table, Stack<Integer> deletedRows) {
            if (!deletedRows.isEmpty()) {
                table.set(deletedRows.pop(), 'O');
            }
            return cursor;
        }
    }
}
