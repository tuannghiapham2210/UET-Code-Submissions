public class Main {
    public static void main(String[] args) {
        //creating a test String (using gemini)
        String string = "Hello world. This is a Java program! Hello Java, hello world? My name is TuanNghia.";

        System.out.println("Original String: " + string);
        //counting the number of sentences using regex
        String[] sentences = string.split("[.!?]");

        System.out.println("The number of sentences: " + sentences.length);

        //replacing "Java" with "Python"
        string = string.replace("Java", "Python");
        
        //after replacing "Java" with "Python"
        System.out.println("After replacing \"Java\" with \"Python\": " + string);

        StringConcatenate.stringConcat();
        StringBufferConcatenate.stringBufferConcat();
    }
}

class StringConcatenate{
    public static void stringConcat(){
        //getting the time before running the loop
        long before = System.currentTimeMillis();
        
        //running the loop
        String string = "";
        for (int i = 0; i < 100000; ++i){
            string += "Hello";
        } 

        //getting the time after the loop
        long after = System.currentTimeMillis();

        //getting the difference 
        long difference = after - before;

        System.out.println("Execution time (using String): " + difference);
    }
}

class StringBufferConcatenate{
    public static void stringBufferConcat(){
        //getting the time before the loop
        long before = System.currentTimeMillis();

        //executing the loop
        StringBuffer string = new StringBuffer();
        for (int i = 0; i < 100000; ++i){
            string.append("Hello");
        }

        //getting the time after execution
        long after = System.currentTimeMillis();

        //getting the difference 
        long difference = after - before;

        System.out.println("Execution time (using StringBuffer): " + difference);
    }
}
