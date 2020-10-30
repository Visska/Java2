package java_2_homework_3;

import java.util.HashMap;
import java.util.Map;

public class Task1ArrWord {
    public static void main(String[] args) {
        String[] wordsArray = {
                "book",
                "frog", "frog",
                "apple",
                "bison",
                "list",
                "cat", "cat", "cat",
                "green",
                "bison"
        };

        Map<String, Integer> wordMap = new HashMap<>();
        for(String word : wordsArray){
            int count = 1;
            if (wordMap.get(word) != null) {
                count += wordMap.get(word);
            }
            wordMap.put(word,count);

        }

        System.out.println(wordMap);

    }
}
