import java.util.*;
import java.util.concurrent.locks.*;

class BookStore {
    private Map<String, Integer> stock;
    private ReentrantReadWriteLock lock;

    //constructor
    public BookStore(){
        this.stock = new HashMap<>();
        this.lock = new ReentrantReadWriteLock();
    }

    //method to read the number of books from the stock
    public void getStock(String title){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ": Title: " + title + " Current quantity: " + this.stock.getOrDefault(title, 0));

        } finally {
            lock.readLock().unlock();

        }
    }

    //method to add some books into the stock
    public void addBook(String title, int quantity){
        lock.writeLock().lock();
        try{
            stock.put(title, stock.getOrDefault(title, 0) + quantity);
            System.out.println(Thread.currentThread().getName() + ": Added " + quantity + " " + title + ". New total: " + stock.get(title));
            try{Thread.sleep(3000);} catch (InterruptedException e) {}

        } finally {
            lock.writeLock().unlock();

        }
    }

    //method to borrow some books from the stock
    public void borrow(String title, int quantity){
        lock.writeLock().lock();
        try {
            int current = stock.getOrDefault(title, 0);
            if (current >= quantity){
                stock.put(title, current - quantity);
                System.out.println(Thread.currentThread().getName() + ": Borrowed " + quantity + " " + title + ". Remaining: " + stock.get(title));
                try{Thread.sleep(3000);} catch (InterruptedException e) {}


            } else{
                System.out.println(Thread.currentThread().getName() + ": Could not borrow, not enough " + title + " to borrow!");

            }

        } finally {
            lock.writeLock().unlock();

        }
    }
}

public class Main{
    public static void main(String[] args) {
        BookStore store = new BookStore();

        //adding 10 books to the store to test
        store.addBook("Advanced Java", 10);

        //Reader task: reading trying to get the quantity 4 times
        Runnable readTask = () -> {
            for (int i = 0; i < 10; i++){
                store.getStock("Advanced Java");

                //putting the read to pause so other threads can overlap
                try {Thread.sleep(1000);} catch (InterruptedException e) {}
            }
        };

        //Supplier task: adding to the stock 2 times
        Runnable supplierTask = () -> {
            try {Thread.sleep(2000);} catch (InterruptedException e) {} 
            store.addBook("Advanced Java", 5);

        };

        //Customer task: borrowing from the stock twice
        Runnable customerTask = () -> {
            try {Thread.sleep(7000); } catch (InterruptedException e){}
            store.borrow("Advanced Java", 3);

        };

        //creating threads
        Thread thread1 = new Thread(readTask, "Reader1");
        Thread thread2 = new Thread(readTask, "Reader2");
        Thread thread3 = new Thread(readTask, "Reader3");
        Thread thread4 = new Thread(supplierTask, "Writer1");
        Thread thread5 = new Thread(customerTask, "Writer2");

        //starting the chaos
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        //waiting for every thread to finish its job
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();

        } catch (InterruptedException e) {}

        System.out.print("The final number of books is: ");
        store.getStock("Advanced Java");
    }
}