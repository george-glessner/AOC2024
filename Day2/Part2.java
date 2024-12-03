package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            Integer sum = 0;
            String line = br.readLine();

            while (line != null) {
                List<String> list = new ArrayList<>(Arrays.asList(line.split(" ")));
                if (checkSafe(list, false)) {
                    sum += 1;
                }
                line = br.readLine();
            }
            System.out.println(sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Boolean checkSafe(List<String> nums, boolean wasSkipped) {
        boolean skipped = wasSkipped;
        boolean increasing = true;

        if (Integer.parseInt(nums.get(0)) > Integer.parseInt(nums.get(1))) {
            increasing = false;
        }

        try {
            for (int i = 0; i < nums.size(); i++) {
                int diff = increasing ? Integer.parseInt(nums.get(i + 1)) - Integer.parseInt(nums.get(i)) : Integer.parseInt(nums.get(i)) - Integer.parseInt(nums.get(i + 1));
                boolean badDirection = increasing ? Integer.parseInt(nums.get(i + 1)) < Integer.parseInt(nums.get(i)) : Integer.parseInt(nums.get(i)) < Integer.parseInt(nums.get(i + 1));
                if ((diff < 1 || diff > 3) || badDirection) {
                    if (skipped) {
                        return false;
                    }
                    List<String> copy = new ArrayList<>(nums);
                    List<String> copy2 = new ArrayList<>(nums);
                    if (i - 1 >= 0) {
                        copy.remove(i - 1);
                    }
                    if (i + 1 < nums.size()) {
                        copy2.remove(i + 1);
                    }
                    boolean validCopy = checkSafe(copy, true);
                    boolean validCopy2 = checkSafe(copy2, true);
                    if (validCopy || validCopy2) {
                        return true;
                    }
                    nums.remove(i);
                    return checkSafe(nums, true);
                }
            }
        } catch (Exception e) {}
        return true;
    }
}
