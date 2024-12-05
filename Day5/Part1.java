package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {
            Map<Integer, List<Integer>> beforeAfter = new HashMap<>();
            String line = br.readLine();
            List<Integer> pageUpdates = new ArrayList<>();
            int sum = 0;

            while (line != null) {
                if(line.contains("|")){
                    beforeAfter.putIfAbsent(Integer.parseInt(line.split("\\|")[0]), new ArrayList<>());
                    beforeAfter.get(Integer.parseInt(line.split("\\|")[0])).add(Integer.parseInt(line.split("\\|")[1]));
                }
                if(line.contains(",")){
                    String[] pageList = line.split(",");
                    for (String page : pageList) {
                        pageUpdates.add(Integer.parseInt(page));
                    }
                    if (checkOrder(beforeAfter, pageUpdates)) {
                        sum += pageUpdates.get(pageUpdates.size()/2);
                    }
                }
                pageUpdates.clear();
                line = br.readLine();
            }

            System.out.println(sum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkOrder(Map<Integer, List<Integer>> beforeAfter, List<Integer> pageUpdates) {
        List<Integer> seenPages = new ArrayList<>();
        for (Integer pageUpdate : pageUpdates) {
            if (beforeAfter.containsKey(pageUpdate)) {
                for (int beforePage : beforeAfter.get(pageUpdate)) {
                    if (seenPages.contains(beforePage)) {
                        return false;
                    }
                }
            }
            seenPages.add(pageUpdate);
        }
        return true;
    }
}
