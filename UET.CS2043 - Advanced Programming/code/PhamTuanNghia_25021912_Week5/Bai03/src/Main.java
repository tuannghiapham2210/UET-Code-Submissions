import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Main{
    public static void main(String[] args){
        String text = "Java is great. Python is great too! I love Java, but python is also fun.";

        //normalizing
        String[] words = text.toLowerCase().replaceAll("[^a-z ]", "").split("\\s+");
        HashMap<String, Integer> frequencyCounter = new HashMap<>();

        //iterates through the whole array and starts counting using hashmap

        for (String word : words){
            frequencyCounter.put(word, frequencyCounter.getOrDefault(word, 0) + 1);
        }

        //an array to store words that appeared exactly once
        List<String> uniqueWords = new ArrayList<>();

        //finding the word that appeared the most
        int maxFrequency = 0;
        String mostCommonWord = null;
        
        for (Map.Entry<String, Integer> entry : frequencyCounter.entrySet()){
            String currentWord = entry.getKey();
            int frequency = entry.getValue();

            if (frequency == 1){
                uniqueWords.add(currentWord);

            } 
            if (frequency > maxFrequency){
                maxFrequency = frequency;
                mostCommonWord = currentWord; 

            }
        }

        //printing out the results
        System.out.println("The most common word: " + "'"  + mostCommonWord + "'" + " appearing " + maxFrequency + " times" );
        
        System.out.print("Unique words: ");
        System.out.println(uniqueWords.toString()); 
        
    } 
}