//coded by Pham Tuan Nghia (25021912)
//QH-2025-I/CQ-I-CS2 (UET - VNU)

import java.util.Random;

public class StdRandom {
    private static Random random = new Random();

    // Set seed for reproducibility if needed
    public static void setSeed(long seed) {
        random.setSeed(seed);
    }

    // Returns a random integer uniformly between 0 (inclusive) and n (exclusive)
    public static int uniform(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("argument must be positive: " + n);
        }
        return random.nextInt(n);
    }

    // Returns a random integer uniformly between a (inclusive) and b (exclusive)
    public static int uniform(int a, int b) {
        if (b <= a) {
            throw new IllegalArgumentException("invalid range: [" + a + ", " + b + ")");
        }
        return a + uniform(b - a);
    }

    // Returns a random real number uniformly between 0.0 and 1.0
    public static double uniform() {
        return random.nextDouble();
    }

    // Helper function to swap two elements in an array
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // Knuth shuffle (Fisher-Yates) for an Object array
    public static void shuffle(Object[] a) {
        if (a == null) {
            return;
        }
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // Pick an integer r between 0 and i uniformly at random
            int r = uniform(i + 1);
            exch(a, i, r);
        }
    }

    public static void main(String[] args) {
        Integer[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println("Before shuffle: " + java.util.Arrays.toString(a));
        shuffle(a);
        System.out.println("After shuffle:  " + java.util.Arrays.toString(a));
    }
}
