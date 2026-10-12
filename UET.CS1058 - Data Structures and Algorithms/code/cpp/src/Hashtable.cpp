//coded by Pham Tuan Nghia (25021912)
//QH-2025-I/CQ-I-CS2 (UET - VNU)

#include <iostream>
#include <list>
#include <string>
#include <stdexcept>
#include <type_traits>
#include <functional>

//-------------------- HASH FUNCTION HELPERS --------------------
// Helper function to hash strings using Horner's rule
int hashString(const std::string& str, int tableSize) {
    if (tableSize <= 0) {
        return 0;
    }

    unsigned long long hashVal = 0;

    for (char c : str) {
        hashVal = (hashVal * 31 + static_cast<unsigned char>(c)) % tableSize;
    }

    return static_cast<int>(hashVal);
}

// Helper function to hash integers
int hashInt(int key, int tableSize) {
    if (tableSize <= 0) {
        return 0;
    }

    int h = key % tableSize;

    if (h < 0) {
        h += tableSize;
    }

    return h;
}

//-------------------- SEPARATE CHAINING HASH TABLE --------------------
template <typename K, typename V>
class SeparateChainingHashTable {
private:
    struct KeyValuePair {
        K key;
        V value;

        KeyValuePair(const K& k, const V& v) : key(k), value(v) {}
    };

    std::list<KeyValuePair>* buckets; // Array of linked lists
    int tableSize;                    // Number of buckets
    int itemCount;                    // Total number of items in the hash table

    // Hash function selector based on key type
    int hash(const K& key) const {
        if constexpr (std::is_same_v<K, std::string>) {
            return hashString(key, tableSize);
        }
        else if constexpr (std::is_integral_v<K>) {
            return hashInt(static_cast<int>(key), tableSize);
        }
        else {
            std::hash<K> hasher;
            return static_cast<int>(hasher(key) % tableSize);
        }
    }

public:
    // Constructor: Initialize hash table with given size
    SeparateChainingHashTable(int size = 101) {
        if (size <= 0) {
            size = 101;
        }

        tableSize = size;
        itemCount = 0;
        buckets = new std::list<KeyValuePair>[tableSize];
    }

    // Destructor: Free dynamically allocated memory
    ~SeparateChainingHashTable() {
        delete[] buckets;
        buckets = nullptr;
    }

    // Copy Constructor (Deep copy)
    SeparateChainingHashTable(const SeparateChainingHashTable& other) {
        tableSize = other.tableSize;
        itemCount = other.itemCount;
        buckets = new std::list<KeyValuePair>[tableSize];

        for (int i = 0; i < tableSize; i++) {
            buckets[i] = other.buckets[i];
        }
    }

    // Copy Assignment Operator (Deep copy)
    SeparateChainingHashTable& operator=(const SeparateChainingHashTable& other) {
        if (this != &other) {
            delete[] buckets;

            tableSize = other.tableSize;
            itemCount = other.itemCount;
            buckets = new std::list<KeyValuePair>[tableSize];

            for (int i = 0; i < tableSize; i++) {
                buckets[i] = other.buckets[i];
            }
        }

        return *this;
    }

    // Insert a key-value pair into the hash table
    // If key already exists, update its value
    void insert(const K& key, const V& value) {
        int idx = hash(key);

        for (auto& pair : buckets[idx]) {
            if (pair.key == key) {
                pair.value = value;
                return;
            }
        }

        buckets[idx].emplace_back(key, value);
        itemCount++;
    }

    // Remove a key-value pair from the hash table
    // Throw exception if key not found
    void remove(const K& key) {
        int idx = hash(key);

        for (auto it = buckets[idx].begin(); it != buckets[idx].end(); ++it) {
            if (it->key == key) {
                buckets[idx].erase(it);
                itemCount--;
                return;
            }
        }

        throw std::runtime_error("Key not found");
    }

    // Search for a value associated with the given key
    // Throw exception if key not found
    V search(const K& key) const {
        int idx = hash(key);

        for (const auto& pair : buckets[idx]) {
            if (pair.key == key) {
                return pair.value;
            }
        }

        throw std::runtime_error("Key not found");
    }

    // Check if the hash table contains the given key
    bool contains(const K& key) const {
        int idx = hash(key);

        for (const auto& pair : buckets[idx]) {
            if (pair.key == key) {
                return true;
            }
        }

        return false;
    }

    // Return the current load factor
    float loadFactor() const {
        if (tableSize == 0) {
            return 0.0f;
        }

        return static_cast<float>(itemCount) / static_cast<float>(tableSize);
    }

    // Return number of items in the hash table
    int size() const {
        return itemCount;
    }

    // Display the hash table (for debugging)
    void display() const {
        for (int i = 0; i < tableSize; i++) {
            std::cout << "Bucket " << i << ": ";

            for (const auto& pair : buckets[i]) {
                std::cout << "[" << pair.key << ": " << pair.value << "] -> ";
            }

            std::cout << "nullptr" << std::endl;
        }
    }
};

//-------------------- OPEN ADDRESSING HASH TABLE --------------------
template <typename K, typename V>
class OpenAddressingHashTable {
private:
    enum EntryStatus { EMPTY, OCCUPIED, DELETED };

    struct Entry {
        K key;
        V value;
        EntryStatus status;
        
        Entry() : status(EMPTY) {}
    };

    Entry* table;      // Array of entries
    int tableSize;     // Size of the table
    int itemCount;     // Number of items in the table

    // Hash function selector based on key type
    int hash(const K& key) const {
        if constexpr (std::is_same_v<K, std::string>) {
            return hashString(key, tableSize);
        }
        else if constexpr (std::is_integral_v<K>) {
            return hashInt(static_cast<int>(key), tableSize);
        }
        else {
            std::hash<K> hasher;
            return static_cast<int>(hasher(key) % tableSize);
        }
    }

    // Probe function for collision resolution (linear probing)
    int probe(int hashVal, int i) const {
        return (hashVal + i) % tableSize;
    }

public:
    // Constructor: Initialize hash table with given size
    OpenAddressingHashTable(int size = 101) {
        if (size <= 0) {
            size = 101;
        }

        tableSize = size;
        itemCount = 0;
        table = new Entry[tableSize];
    }

    // Destructor: Free dynamically allocated memory
    ~OpenAddressingHashTable() {
        delete[] table;
        table = nullptr;
    }

    // Copy Constructor (Deep copy)
    OpenAddressingHashTable(const OpenAddressingHashTable& other) {
        tableSize = other.tableSize;
        itemCount = other.itemCount;
        table = new Entry[tableSize];

        for (int i = 0; i < tableSize; i++) {
            table[i] = other.table[i];
        }
    }

    // Copy Assignment Operator (Deep copy)
    OpenAddressingHashTable& operator=(const OpenAddressingHashTable& other) {
        if (this != &other) {
            delete[] table;

            tableSize = other.tableSize;
            itemCount = other.itemCount;
            table = new Entry[tableSize];

            for (int i = 0; i < tableSize; i++) {
                table[i] = other.table[i];
            }
        }

        return *this;
    }

    // Insert a key-value pair into the hash table
    // If key already exists, update its value
    // If table is full, throw an exception
    void insert(const K& key, const V& value) {
        int h = hash(key);
        int firstAvailable = -1;

        for (int i = 0; i < tableSize; i++) {
            int idx = probe(h, i);

            if (table[idx].status == OCCUPIED) {
                if (table[idx].key == key) {
                    table[idx].value = value;
                    return;
                }
            }
            else if (table[idx].status == DELETED) {
                if (firstAvailable == -1) {
                    firstAvailable = idx;
                }
            }
            else { // EMPTY
                if (firstAvailable == -1) {
                    firstAvailable = idx;
                }

                break;
            }
        }

        if (firstAvailable != -1) {
            table[firstAvailable].key = key;
            table[firstAvailable].value = value;
            table[firstAvailable].status = OCCUPIED;
            itemCount++;
            return;
        }

        throw std::overflow_error("Hash table is full");
    }

    // Remove a key-value pair from the hash table
    // Throw exception if key not found
    void remove(const K& key) {
        int h = hash(key);

        for (int i = 0; i < tableSize; i++) {
            int idx = probe(h, i);

            if (table[idx].status == OCCUPIED) {
                if (table[idx].key == key) {
                    table[idx].status = DELETED;
                    itemCount--;
                    return;
                }
            }
            else if (table[idx].status == EMPTY) {
                break;
            }
        }

        throw std::runtime_error("Key not found");
    }

    // Search for a value associated with the given key
    // Throw exception if key not found
    V search(const K& key) const {
        int h = hash(key);

        for (int i = 0; i < tableSize; i++) {
            int idx = probe(h, i);

            if (table[idx].status == OCCUPIED) {
                if (table[idx].key == key) {
                    return table[idx].value;
                }
            }
            else if (table[idx].status == EMPTY) {
                break;
            }
        }

        throw std::runtime_error("Key not found");
    }

    // Check if the hash table contains the given key
    bool contains(const K& key) const {
        int h = hash(key);

        for (int i = 0; i < tableSize; i++) {
            int idx = probe(h, i);

            if (table[idx].status == OCCUPIED) {
                if (table[idx].key == key) {
                    return true;
                }
            }
            else if (table[idx].status == EMPTY) {
                break;
            }
        }

        return false;
    }

    // Return the current load factor
    float loadFactor() const {
        if (tableSize == 0) {
            return 0.0f;
        }

        return static_cast<float>(itemCount) / static_cast<float>(tableSize);
    }

    // Return number of items in the hash table
    int size() const {
        return itemCount;
    }

    // Display the hash table (for debugging)
    void display() const {
        for (int i = 0; i < tableSize; i++) {
            std::cout << "Index " << i << ": ";

            if (table[i].status == OCCUPIED) {
                std::cout << "[" << table[i].key << ": " << table[i].value << "]";
            }
            else if (table[i].status == DELETED) {
                std::cout << "<DELETED>";
            }
            else {
                std::cout << "<EMPTY>";
            }

            std::cout << std::endl;
        }
    }
};

//-------------------- STRICT TESTING FUNCTIONS --------------------
void testSeparateChainingHashTable() {
    SeparateChainingHashTable<std::string, int> scTable(10);

    // --- TEST 1: Initialization & Empty State ---
    if (scTable.size() != 0) {
        throw std::runtime_error("TEST FAILED: New table size should be 0.");
    }

    if (scTable.loadFactor() != 0.0f) {
        throw std::runtime_error("TEST FAILED: Load factor should be 0.");
    }

    if (scTable.contains("apple")) {
        throw std::runtime_error("TEST FAILED: Empty table should not contain keys.");
    }

    // --- TEST 2: Insert & Search ---
    scTable.insert("apple", 5);
    scTable.insert("banana", 8);
    scTable.insert("cherry", 12);
    scTable.insert("date", 15);
    scTable.insert("elderberry", 20);
    scTable.insert("fig", 25);

    if (scTable.size() != 6) {
        throw std::runtime_error("TEST FAILED: Size should be 6 after 6 insertions.");
    }

    if (scTable.search("apple") != 5) {
        throw std::runtime_error("TEST FAILED: Search for 'apple' failed.");
    }

    if (scTable.search("fig") != 25) {
        throw std::runtime_error("TEST FAILED: Search for 'fig' failed.");
    }

    if (!scTable.contains("cherry")) {
        throw std::runtime_error("TEST FAILED: contains('cherry') should be true.");
    }

    // --- TEST 3: Update Existing Key ---
    scTable.insert("apple", 99);

    if (scTable.size() != 6) {
        throw std::runtime_error("TEST FAILED: Updating existing key changed size.");
    }

    if (scTable.search("apple") != 99) {
        throw std::runtime_error("TEST FAILED: Updated value not retrieved.");
    }

    // --- TEST 4: Removal ---
    scTable.remove("banana");

    if (scTable.size() != 5) {
        throw std::runtime_error("TEST FAILED: Size should be 5 after removal.");
    }

    if (scTable.contains("banana")) {
        throw std::runtime_error("TEST FAILED: Removed key still reported as contained.");
    }

    // --- TEST 5: Exception Handling ---
    bool exceptionCaught = false;

    try {
        scTable.search("banana"); // Already removed
    }
    catch (const std::runtime_error&) {
        exceptionCaught = true;
    }

    if (!exceptionCaught) {
        throw std::runtime_error("TEST FAILED: Searching removed key did not throw exception.");
    }

    exceptionCaught = false;

    try {
        scTable.remove("non_existent_key");
    }
    catch (const std::runtime_error&) {
        exceptionCaught = true;
    }

    if (!exceptionCaught) {
        throw std::runtime_error("TEST FAILED: Removing non-existent key did not throw exception.");
    }

    // --- TEST 6: Generic Key Support (Integer Keys) ---
    SeparateChainingHashTable<int, std::string> intTable(5);

    intTable.insert(1, "one");
    intTable.insert(6, "six");     // Collides with 1 mod 5
    intTable.insert(11, "eleven"); // Collides with 1 mod 5

    if (intTable.size() != 3) {
        throw std::runtime_error("TEST FAILED: Separate chaining integer table size wrong.");
    }

    if (intTable.search(1) != "one" || intTable.search(6) != "six" || intTable.search(11) != "eleven") {
        throw std::runtime_error("TEST FAILED: Chaining collision resolution failed for integer keys.");
    }

    // --- SUCCESS ---
    std::cout << "SeparateChainingHashTable Worked" << std::endl;
}

void testOpenAddressingHashTable() {
    std::cout << "========== TESTING OPEN ADDRESSING HASH TABLE ==========" << std::endl;
    OpenAddressingHashTable<int, std::string> oaTable(10);

    // --- TEST 1: Initialization & Empty State ---
    if (oaTable.size() != 0) {
        throw std::runtime_error("TEST FAILED: New open addressing table size should be 0.");
    }

    if (oaTable.loadFactor() != 0.0f) {
        throw std::runtime_error("TEST FAILED: Load factor should be 0.");
    }

    // --- TEST 2: Insert with Collisions (Linear Probing) ---
    oaTable.insert(5, "five");
    oaTable.insert(15, "fifteen");     // Collision at index 5 -> probes to 6
    oaTable.insert(25, "twenty-five"); // Collision -> probes to 7
    oaTable.insert(35, "thirty-five"); // Collision -> probes to 8
    oaTable.insert(45, "forty-five");  // Collision -> probes to 9

    if (oaTable.size() != 5) {
        throw std::runtime_error("TEST FAILED: Size should be 5 after insertions.");
    }

    if (oaTable.search(5) != "five") {
        throw std::runtime_error("TEST FAILED: Search for 5 failed.");
    }

    if (oaTable.search(15) != "fifteen") {
        throw std::runtime_error("TEST FAILED: Search for 15 failed.");
    }

    if (oaTable.search(45) != "forty-five") {
        throw std::runtime_error("TEST FAILED: Search for 45 failed.");
    }

    // --- TEST 3: Update Existing Key ---
    oaTable.insert(15, "FIFTEEN_UPDATED");

    if (oaTable.size() != 5) {
        throw std::runtime_error("TEST FAILED: Updating existing key changed size.");
    }

    if (oaTable.search(15) != "FIFTEEN_UPDATED") {
        throw std::runtime_error("TEST FAILED: Updated value not retrieved.");
    }

    // --- TEST 4: Removal and Probing Past Tombstones (DELETED entries) ---
    oaTable.remove(15);

    if (oaTable.size() != 4) {
        throw std::runtime_error("TEST FAILED: Size should be 4 after removal.");
    }

    if (oaTable.contains(15)) {
        throw std::runtime_error("TEST FAILED: Removed key still reported as contained.");
    }

    // Search elements that probed past index 6 (where 15 was deleted)
    if (oaTable.search(25) != "twenty-five") {
        throw std::runtime_error("TEST FAILED: Probing past DELETED entry failed.");
    }

    if (oaTable.search(45) != "forty-five") {
        throw std::runtime_error("TEST FAILED: Probing past DELETED entry failed.");
    }

    // --- TEST 5: Slot Reuse (Inserting after DELETED) ---
    oaTable.insert(15, "fifteen_reinserted");

    if (oaTable.size() != 5) {
        throw std::runtime_error("TEST FAILED: Size wrong after reinsertion.");
    }

    if (oaTable.search(15) != "fifteen_reinserted") {
        throw std::runtime_error("TEST FAILED: Reinserted key search failed.");
    }

    // --- TEST 6: Table Full Exception ---
    OpenAddressingHashTable<int, int> smallTable(3);

    smallTable.insert(1, 100);
    smallTable.insert(2, 200);
    smallTable.insert(3, 300);

    bool overflowCaught = false;

    try {
        smallTable.insert(4, 400); // Exceeds capacity of 3
    }
    catch (const std::overflow_error&) {
        overflowCaught = true;
    }

    if (!overflowCaught) {
        throw std::runtime_error("TEST FAILED: Inserting into full table did not throw overflow_error.");
    }

    // --- TEST 7: Generic Key Support (String Keys) ---
    OpenAddressingHashTable<std::string, int> strTable(10);

    strTable.insert("hello", 1);
    strTable.insert("world", 2);

    if (strTable.size() != 2) {
        throw std::runtime_error("TEST FAILED: String key table size wrong.");
    }

    if (strTable.search("hello") != 1 || strTable.search("world") != 2) {
        throw std::runtime_error("TEST FAILED: String key search failed.");
    }

    // --- SUCCESS ---
    std::cout << "OpenAddressingHashTable Worked" << std::endl;
}

void testHashTables() {
    testSeparateChainingHashTable();
    testOpenAddressingHashTable();
}

int main() {
    testHashTables();
    return 0;
}
