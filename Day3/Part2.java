package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            Integer sum = 0;

            Pattern mulPattern = Pattern.compile("(mul)\\(([0-9]{1,3},[0-9]{1,3}\\))|do\\(\\)|don't\\(\\)");
            Pattern numPattern = Pattern.compile("[0-9]{1,3},[0-9]{1,3}");

            StringBuilder sb = new StringBuilder();
            boolean doMultiply = true;

            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            Matcher mulMatcher = mulPattern.matcher(sb);
            while (mulMatcher.find()) {
                String match = mulMatcher.group();
                if (match.equals("do()")) {
                    doMultiply = true;
                } else if (match.equals("don't()")) {
                    doMultiply = false;
                } else {
                    Matcher numMatcher = numPattern.matcher(match);
                    if (numMatcher.find() && doMultiply) {
                        String[] nums = numMatcher.group().split(",");
                        sum += Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
                    }
                }
            }
            System.out.println(sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
