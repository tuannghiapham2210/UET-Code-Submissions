public class Main{
    public static void main(String[] args) {
        //error: incompatible types (compile error)
        //Pair<String, Integer> age = new Pair<>("age", "20");

        Pair<String, Integer> age = new Pair<>("age", 20);

        Pair<String, String> id = new Pair<>("student id", "SV001");

        Pair<Integer, Double> coordinates = new Pair<>(105, 21.5);

        System.out.println(age);
        System.out.println(id);
        System.out.println(coordinates);        
    }
}