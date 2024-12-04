package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            Integer sum = 0;
            StringBuilder sb = new StringBuilder();

            String line = br.readLine();
            Integer lineLength = line.length();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            String[] stringList = new String[sb.length()];

            for (int i = 0; i < sb.length(); i++) {
                stringList[i] = String.valueOf(sb.charAt(i));
            }

            for (int i = 0; i < stringList.length; i++) {
                if (stringList[i].equals("X")) {
                    sum += checkHorizontal(stringList, i, lineLength);
                    sum += checkVertical(stringList, i, lineLength);
                    sum += checkDiagonal(stringList, i, lineLength);
                }
            }
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Integer checkHorizontal(String[] stringList, Integer i, Integer lineLength) {
        Integer sum = 0;
        Integer depth = Math.floorMod(i, lineLength);
        String check = "";

        // backwards
        if (depth >= 3) {
            check = stringList[i - 1] + stringList[i - 2] + stringList[i - 3];
            sum += check.equals("MAS") ? 1 : 0;
        }

        // forwards
        if (depth <= lineLength - 4) {
            check = stringList[i + 1] + stringList[i + 2] + stringList[i + 3];
            sum += check.equals("MAS") ? 1 : 0;
        }

        return sum;
    }

    private static Integer checkVertical(String[] stringList, Integer i, Integer lineLength) {
        Integer sum = 0;
        Integer row = i / lineLength;
        String check = "";

        // upwards
        if (row >= 3) {
            check = stringList[i - lineLength] + stringList[i - (lineLength * 2)] + stringList[i - (lineLength * 3)];
            sum += check.equals("MAS") ? 1 : 0;
        }

        // downwards
        if (row <= (stringList.length / lineLength) - 4) {
            check = stringList[i + lineLength] + stringList[i + (lineLength * 2)] + stringList[i + (lineLength * 3)];
            sum += check.equals("MAS") ? 1 : 0;
        }
        return sum;
    }

    private static Integer checkDiagonal(String[] stringList, Integer i, Integer lineLength) {
        Integer sum = 0;
        Integer row = i / lineLength;
        Integer depth = Math.floorMod(i, lineLength);
        String check = "";

        // Down and to the right
        if (row <= (stringList.length / lineLength) - 4 && depth <= lineLength - 4) {
            check = stringList[i + (lineLength + 1)] + stringList[i + (lineLength * 2 + 2)] + stringList[i + (lineLength * 3 + 3)];
            sum += check.equals("MAS") ? 1 : 0;
        }

        // Down and to the left
        if (row <= (stringList.length / lineLength) - 4 && depth >= 3) {
            check = stringList[i + (lineLength - 1)] + stringList[i + (lineLength * 2 - 2)] + stringList[i + (lineLength * 3 - 3)];
            sum += check.equals("MAS") ? 1 : 0;
        }

        // Up and to the right
        if (row >= 3 && depth <= lineLength - 4) {
            check = stringList[i - (lineLength - 1)] + stringList[i - (lineLength * 2 - 2)] + stringList[i - (lineLength * 3 - 3)];
            sum += check.equals("MAS") ? 1 : 0;
        }

        // Up and to the left
        if (row >= 3 && depth >= 3) {
            check = stringList[i - (lineLength + 1)] + stringList[i - (lineLength * 2 + 2)] + stringList[i - (lineLength * 3 + 3)];
            sum += check.equals("MAS") ? 1 : 0;
        }
        return sum;
    }
}