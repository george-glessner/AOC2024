package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Part2 {
    enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            AtomicInteger sum = new AtomicInteger();
            StringBuilder sb = new StringBuilder();

            String line = br.readLine();
            int lineLength = line.length();
            int startPos = 0;

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            String[] stringList = new String[sb.length()];

            for (int i = 0; i < sb.length(); i++) {
                stringList[i] = String.valueOf(sb.charAt(i));
                if (stringList[i].equals("^")) {
                    startPos = i;
                    stringList[i] = ".";
                }
            }

            Set<Integer> goodPaths = Part1.checkPaths(stringList, startPos, lineLength);

            int finalStartPos = startPos;
            goodPaths.stream().sorted().forEach(path -> {
                if (path != finalStartPos) {
                    stringList[path] = "X";
                    if (testBlock(stringList, finalStartPos, lineLength)) {
                        sum.addAndGet(1);
                    }
                    stringList[path] = ".";
                }
            });
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean testBlock(String[] stringList, int currentPos, int lineLength) {
        Direction currentDirection = Direction.UP;
        Map<Integer, List<String>> visitedDir = new HashMap<>();
        boolean exited = false;
        boolean blocked = false;
        int visitedPosition = currentPos;

        while (!exited) {
            try {
                switch (currentDirection) {
                    case UP:
                        if(visitedDir.getOrDefault(currentPos, new ArrayList<>()).contains("UP")) {
                            exited = true;
                            blocked = true;
                            break;
                        }
                        else if (stringList[currentPos - lineLength].equals("X")) {
                            currentDirection = Direction.RIGHT;
                        } else if (stringList[currentPos - lineLength].equals("#")) {
                            currentDirection = Direction.RIGHT;
                        } else if (stringList[currentPos - lineLength].equals(".")) {
                            if ((currentPos - lineLength) < lineLength) {
                                exited = true;
                                break;
                            }
                            currentPos -= lineLength;
                        }
                        visitedDir.putIfAbsent(visitedPosition, new ArrayList<>());
                        visitedDir.get(visitedPosition).add("UP");
                        visitedPosition = currentPos;
                        break;
                    case RIGHT:
                        if (visitedDir.getOrDefault(currentPos, new ArrayList<>()).contains("RIGHT")) {
                            exited = true;
                            blocked = true;
                            break;
                        }
                        else if (currentPos % lineLength == lineLength - 1) {
                            exited = true;
                            break;
                        }
                        else if (stringList[currentPos + 1].equals(".")) {
                            if ((currentPos+1 % lineLength) == lineLength - 1) {
                                exited = true;
                                break;
                            }
                            currentPos += 1;
                        }
                        else if (stringList[currentPos + 1].equals("X")) {
                            currentDirection = Direction.DOWN;
                        } else if (stringList[currentPos + 1].equals("#")) {
                            currentDirection = Direction.DOWN;
                        }
                        visitedDir.putIfAbsent(visitedPosition, new ArrayList<>());
                        visitedDir.get(visitedPosition).add("RIGHT");
                        visitedPosition = currentPos;
                        break;
                    case DOWN:
                        if (visitedDir.getOrDefault(currentPos, new ArrayList<>()).contains("DOWN")) {
                            exited = true;
                            blocked = true;
                            break;
                        }
                         else if (stringList[currentPos + lineLength].equals("X")) {
                            currentDirection = Direction.LEFT;
                        } else if (stringList[currentPos + lineLength].equals("#")) {
                            currentDirection = Direction.LEFT;
                        } else if (stringList[currentPos + lineLength].equals(".")) {
                            if ((currentPos + lineLength) >= (stringList.length - lineLength)) {
                                exited = true;
                                break;
                            }
                            currentPos += lineLength;
                        }
                        visitedDir.putIfAbsent(visitedPosition, new ArrayList<>());
                        visitedDir.get(visitedPosition).add("DOWN");
                        visitedPosition = currentPos;
                        break;
                    case LEFT:
                        if (visitedDir.getOrDefault(currentPos, new ArrayList<>()).contains("LEFT")) {
                            exited = true;
                            blocked = true;
                            break;
                        }
                        else if (currentPos % lineLength == 0) {
                            exited = true;
                            break;
                        }
                        else if (stringList[currentPos - 1].equals(".")) {
                            if ((currentPos - 1) % lineLength == 0) {
                                exited = true;
                                break;
                            }
                            currentPos -= 1;
                        }
                        else if (stringList[currentPos - 1].equals("X")) {
                            currentDirection = Direction.UP;
                        } else if (stringList[currentPos - 1].equals("#")) {
                            currentDirection = Direction.UP;
                        }
                        visitedDir.putIfAbsent(visitedPosition, new ArrayList<>());
                        visitedDir.get(visitedPosition).add("LEFT");
                        visitedPosition = currentPos;
                        break;
                    default:
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                exited = true;
            }
        }
        return blocked;
    }
}