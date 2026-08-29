import java.util.HashMap;
import java.util.Map;

public class Main{
    public static void main(String[] args){
        String text = "Hello world. This is a java program. Hello java, hello world.";

        WordCounter wordCounter = new WordCounter();
        wordCounter.analyze(text);
        wordCounter.displayResult();
    }
}


class WordCounter{
    //internal state of the class
    private HashMap<String, Integer> wordFrequency;

    //constructor
    public WordCounter() {
        this.wordFrequency = new HashMap<>();
    }

    //implementing the analyze method (this method will just modify the internal state of the class)
    public void analyze(String text){
        //normalizing the text  
        String[] words = text.toLowerCase().replaceAll("[.,]", "").split("\\s+");

        for (String word : words){
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }
    }

    public void displayResult(){
        int maxFrequency = 0;
        String mostCommonWord = null;

        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()){
            String currentWord = entry.getKey();
            int frequency = entry.getValue();

            System.out.println(currentWord + " appearing " + frequency + " time(s)");

            if (frequency > maxFrequency){
                maxFrequency = frequency;
                mostCommonWord = currentWord;
            }

        }

        System.out.println("The most common word is: " + mostCommonWord + " appearing " + maxFrequency + " time(s)");
    }
}