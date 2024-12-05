package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {
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
                    if (!Part1.checkOrder(beforeAfter, pageUpdates)) {
                        pageUpdates = reOrder(beforeAfter, pageUpdates);
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

    public static List<Integer> reOrder(Map<Integer, List<Integer>> beforeAfter, List<Integer> pageUpdates) {
        List<Integer> seenPages = new ArrayList<>();
        for (int i = 0; i < pageUpdates.size(); i++) {
            if (beforeAfter.containsKey(pageUpdates.get(i))) {
                for (int j = 0; j < beforeAfter.get(pageUpdates.get(i)).size(); j++) {
                    if (seenPages.contains(beforeAfter.get(pageUpdates.get(i)).get(j))) {
                        int index = pageUpdates.indexOf(beforeAfter.get(pageUpdates.get(i)).get(j));
                        pageUpdates.add(index, pageUpdates.get(i));
                        pageUpdates.remove(i+1);
                        return reOrder(beforeAfter, pageUpdates);
                    }
                }
            }
            seenPages.add(pageUpdates.get(i));
        }
        return pageUpdates;
    }
}
