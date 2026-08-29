public class Pair<K, V>{
    //attributes (instance variables)
    private K key;
    private V value;

    //constructor
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    //public setters/getters
    public K getKey(){
        return this.key;
    }

    public V getValue(){
        return this.value;
    }

    public void setKey(K key){
        this.key = key;
    }

    public void setValue(V value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.key + " - " + this.value;
    }
}