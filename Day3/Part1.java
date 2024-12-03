package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            Integer sum = 0;
            List<Integer> list1 = new ArrayList<>();

            String line = br.readLine();

            while (line != null) {
                Pattern pattern = Pattern.compile("(mul)\\(([0-9]{1,3},[0-9]{1,3}\\))");
                Pattern pattern2 = Pattern.compile("[0-9]{1,3},[0-9]{1,3}");
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    Matcher matcher2 = pattern2.matcher(matcher.group());
                    String[] nums = matcher2.group().split(",");
                    sum += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
                }
                line = br.readLine();
            }

            System.out.println(sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
