package programmers.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

// https://school.programmers.co.kr/learn/courses/30/lessons/81303
public class ModifyTable3 {

    public static void main(String[] args) {
        final ModifyTable3 modifyTable = new ModifyTable3();
        final int n = 8;
        final int k = 2;
        final String[] cmd1 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z"};
        final String[] cmd2 = {"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"};
        final String[] cmd3 = {"C", "C", "C", "C"};
        System.out.println(modifyTable.solution(n, k, cmd2));
    }


    public String solution(int n, int k, String[] cmd) {
        Integer cursor = k + 1;
        final Stack<Integer> deletedRow = new Stack<>();
        final List<Integer> upIdxs = initUpIdxs(n);
        final List<Integer> downIdxs = initDownIdxs(n);
        final List<Command> commands = Command.initCommands(cmd);
        for (Command command : commands)
            cursor = command.proceed(
                    cursor,
                    n,
                    upIdxs,
                    downIdxs,
                    deletedRow
            );
        final char[] answer = new char[n];
        Arrays.fill(answer, 'O');
        for (int row : deletedRow)
            answer[row - 1] = 'X';
        return new String(answer);
    }

    private List<Integer> initUpIdxs(int n) {
        List<Integer> upIdxs = new ArrayList<>();
        for (int i = -1; i <= n; i++)
            upIdxs.add(i);
        return upIdxs;
    }

    private List<Integer> initDownIdxs(int n) {
        List<Integer> downIdxs = new ArrayList<>();
        for (int i = 1; i <= n + 2; i++)
            downIdxs.add(i);
        return downIdxs;
    }

    public static abstract class Command {
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

        public abstract int proceed(Integer cursor,
                                    int inputSize,
                                    List<Integer> upIdxs,
                                    List<Integer> downIdxs,
                                    Stack<Integer> deletedRow);
    }

    public static class UpCommand extends Command {
        private final int offset;

        public UpCommand(int offset) {
            this.offset = offset;
        }

        @Override
        public int proceed(Integer cursor,
                           int inputSize,
                           List<Integer> upIdxs,
                           List<Integer> downIdxs,
                           Stack<Integer> deletedRow) {
            for (int i = 0; i < offset; i++)
                cursor = upIdxs.get(cursor);
            return cursor;
        }
    }

    public static class DownCommand extends Command {
        private final int offset;

        public DownCommand(int offset) {
            this.offset = offset;
        }

        @Override
        public int proceed(Integer cursor,
                           int inputSize,
                           List<Integer> upIdxs,
                           List<Integer> downIdxs,
                           Stack<Integer> deletedRow) {
            for (int i = 0; i < offset; i++)
                cursor = downIdxs.get(cursor);
            return cursor;
        }
    }

    public static class DeleteCommand extends Command {
        @Override
        public int proceed(Integer cursor,
                           int inputSize,
                           List<Integer> upIdxs,
                           List<Integer> downIdxs,
                           Stack<Integer> deletedRow) {
            upIdxs.set(downIdxs.get(cursor), upIdxs.get(cursor));
            downIdxs.set(upIdxs.get(cursor), downIdxs.get(cursor));
            deletedRow.push(cursor);
            return inputSize < downIdxs.get(cursor) ? upIdxs.get(cursor) : downIdxs.get(cursor);
        }
    }

    public static class RestoreCommand extends Command {
        @Override
        public int proceed(Integer cursor,
                           int inputSize,
                           List<Integer> upIdxs,
                           List<Integer> downIdxs,
                           Stack<Integer> deletedRow) {
            if (deletedRow.isEmpty()) {
                return cursor;
            }
            int restore = deletedRow.pop();
            downIdxs.set(upIdxs.get(restore), restore);
            upIdxs.set(downIdxs.get(restore), restore);
            return cursor;
        }
    }

}
