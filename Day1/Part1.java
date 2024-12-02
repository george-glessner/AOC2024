package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Part1 {
    public static void main(String[] args) {
         try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
             Integer sum = 0;
             List<Integer> list1 = new ArrayList<>();
             List<Integer> list2 = new ArrayList<>();

             String line = br.readLine();

             while (line != null) {
                 list1.add(Integer.parseInt(line.split("   ")[0]));
                 list2.add(Integer.parseInt(line.split("   ")[1]));
                 line = br.readLine();
             }


             list1.sort(Comparator.comparing(Integer::intValue));
             list2.sort(Comparator.comparing(Integer::intValue));

            for (int i = 0; i < list1.size(); i++) {
                sum += Math.abs(list1.get(i) - list2.get(i));
            }

            System.out.println(sum);

         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
