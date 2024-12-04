package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            Integer sum = 0;
            StringBuilder sb = new StringBuilder();

            String line = br.readLine();
            int lineLength = line.length();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            String[] stringList = new String[sb.length()];

            for (int i = 0; i < sb.length(); i++) {
                stringList[i] = String.valueOf(sb.charAt(i));
            }

            int totalLength = stringList.length/lineLength;

            for (int i = 0; i < stringList.length; i++) {
                int row = i / lineLength;
                int depth = Math.floorMod(i, lineLength);

                // from left
                if (row < totalLength - 2 && depth < lineLength - 2) {
                    if(stringList[i].equals("Z")) {
                        System.out.println("Z");
                    }
                    if (stringList[i].equals("X") || stringList[i + lineLength * 2].equals("X") || stringList[i + lineLength * 2 + 2].equals("X") || stringList[i + 2].equals("X") ||
                            stringList[i].equals("A") || stringList[i + lineLength * 2].equals("A") || stringList[i + lineLength * 2 + 2].equals("A") || stringList[i + 2].equals("A")) {
                        continue;
                    }

                    // middle must always be A
                    if (stringList[i + lineLength + 1].equals("A")) {
                        // top bottom
                        if (stringList[i].equals(stringList[i+2]) && stringList[i+lineLength*2].equals(stringList[i+lineLength*2+2]) && !stringList[i].equals(stringList[i+lineLength*2])) {
                            sum += 1;
                        }
                        // left right
                        else if (stringList[i].equals(stringList[i+lineLength*2]) && stringList[i+2].equals(stringList[i+lineLength*2+2]) && !stringList[i].equals(stringList[i+2])) {
                            sum += 1;
                        }
                    }
                }
            }
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
