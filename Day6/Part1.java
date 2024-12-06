package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Part1 {
    enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            Integer sum = 0;
            StringBuilder sb = new StringBuilder();

            String line = br.readLine();

            int lineLength = line.length();
            int currentPos = 0;
            Set<Integer> visited = new HashSet<>();
            boolean exited = false;

            Direction currentDirection = Direction.UP;

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            String[] stringList = new String[sb.length()];

            for (int i = 0; i < sb.length(); i++) {
                stringList[i] = String.valueOf(sb.charAt(i));
                if (stringList[i].equals("^")) {
                    currentPos = i;
                    stringList[i] = ".";
                }
            }

            Set<Integer> goodPaths = checkPaths(stringList, currentPos, lineLength);
            System.out.println(goodPaths.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<Integer> checkPaths(String[] stringList, int currentPos, int lineLength) {
        boolean exited = false;
        Direction currentDirection = Direction.UP;
        Set<Integer> visited = new HashSet<>();

        while (!exited) {
            try {
                switch (currentDirection) {
                    case UP:
                        if (stringList[currentPos - lineLength].equals("#")) {
                            currentDirection = Direction.RIGHT;
                        } else if (stringList[currentPos - lineLength].equals(".")) {
                            currentPos -= lineLength;
                            visited.add(currentPos);
                        }
                        break;
                    case RIGHT:
                        if (stringList[currentPos + 1].equals("#")) {
                            currentDirection = Direction.DOWN;
                        } else if (stringList[currentPos + 1].equals(".")) {
                            currentPos += 1;
                            visited.add(currentPos);
                        }
                        break;
                    case DOWN:
                        if (stringList[currentPos + lineLength].equals("#")) {
                            currentDirection = Direction.LEFT;
                        } else if (stringList[currentPos + lineLength].equals(".")) {
                            currentPos += lineLength;
                            visited.add(currentPos);
                        }
                        break;
                    case LEFT:
                        if (stringList[currentPos - 1].equals("#")) {
                            currentDirection = Direction.UP;
                        } else if (stringList[currentPos - 1].equals(".")) {
                            currentPos -= 1;
                            visited.add(currentPos);
                        }
                        break;
                    default:
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                exited = true;
            }
        }
        return visited;
    }
}