package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            Integer sum = 0;
            String line = br.readLine();

            while (line != null) {
                if (checkSafe(line.split(" "))) {
                    sum += 1;
                }
                line = br.readLine();
            }

            System.out.println(sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Boolean checkSafe(String[] nums) {
        boolean increasing = true;

        if (Integer.parseInt(nums[0]) == Integer.parseInt(nums[1])) {
            return false;
        }

        if (Integer.parseInt(nums[0]) > Integer.parseInt(nums[1])) {
            increasing = false;
        }

        for (int i = 0; i < nums.length; i++) {
            try {
                if (increasing) {
                    int diff = Integer.parseInt(nums[i + 1]) - Integer.parseInt(nums[i]);
                    if (diff < 1 || diff > 3) {
                        return false;
                    }
                } else {
                    int diff = Integer.parseInt(nums[i]) - Integer.parseInt(nums[i + 1]);
                    if (diff < 1 || diff > 3) {
                        return false;
                    }
                }
            } catch (Exception e) {}
        }
        return true;
    }
}
