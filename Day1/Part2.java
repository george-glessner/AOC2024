package Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Part2 {
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

             Map<Integer, Integer> occurences = new HashMap<>();

            list2.stream().forEach(i -> occurences.put(i, occurences.getOrDefault(i, 0) + 1));

            for (int i = 0; i < list1.size(); i++) {
                try {
                    sum += list1.get(i) * occurences.get(list1.get(i));
                } catch (NullPointerException e) {}
            }

            System.out.println(sum);

         } catch (IOException e) {
             e.printStackTrace();
         }
    }
}
