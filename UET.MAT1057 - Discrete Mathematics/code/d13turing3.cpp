#include <iostream>

using namespace std;

int main() {
    cout << 15 << endl;
    
    cout << "0 1 1 1 R" << endl; 

    cout << "1 1 2 0 R" << endl; 
    cout << "1 * 4 1 R" << endl; 
    cout << "1 B 5 B L" << endl; 
    
    cout << "2 1 2 1 R" << endl;
    cout << "2 * 2 * R" << endl;
    cout << "2 B 3 * L" << endl; 
 
    cout << "3 1 3 1 L" << endl;
    cout << "3 * 3 * L" << endl;
    cout << "3 0 1 0 R" << endl;

    cout << "4 * 4 1 R" << endl;
    cout << "4 B 5 B L" << endl; 

    cout << "5 1 5 1 L" << endl;
    cout << "5 0 5 1 L" << endl;
    cout << "5 B 99 B R" << endl; 

    return 0;
}