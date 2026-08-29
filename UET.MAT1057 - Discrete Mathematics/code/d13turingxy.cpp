#include <iostream>

using namespace std;

int main() {
    cout << 23 << endl;
    
    cout << "0 1 1 1 R" << endl;
    cout << "1 1 2 B R" << endl;
    cout << "1 * 3 B R" << endl;
    cout << "2 1 1 0 R" << endl;
    cout << "2 * 3 B R" << endl;
    cout << "3 1 4 B R" << endl;
    cout << "4 1 5 B R" << endl;
    cout << "4 B 6 * L" << endl;
    cout << "5 1 4 0 R" << endl;
    cout << "5 B 6 * L" << endl;
    
    cout << "6 B 6 B L" << endl;
    cout << "6 0 6 0 L" << endl;
    cout << "6 1 7 1 R" << endl;
    
    cout << "7 B 7 B R" << endl;
    cout << "7 0 8 B L" << endl;
    cout << "8 B 8 B L" << endl;
    cout << "8 1 9 1 R" << endl;
    cout << "9 B 7 1 R" << endl;
    
    cout << "7 * 10 B L" << endl; 
    
    cout << "10 B 10 B L" << endl; 
    
    cout << "10 1 11 1 L" << endl; 
    
    cout << "11 1 11 1 L" << endl; 
    
    cout << "11 B 12 B R" << endl; 
    
    return 0;
}