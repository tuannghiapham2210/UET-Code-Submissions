#include <iostream>

using namespace std;

int main() {
    cout << 24 << endl;
    
    cout << "0 1 1 0 R" << endl; 
    cout << "1 1 1 1 R" << endl; 
    cout << "1 * 2 * R" << endl; 
    cout << "2 0 2 0 R" << endl; 
    cout << "2 1 3 0 L" << endl; 
    cout << "3 0 3 0 L" << endl; 
    cout << "3 * 4 * L" << endl; 
    cout << "4 1 4 1 L" << endl; 
    cout << "4 0 0 0 R" << endl; 

    cout << "0 * 6 B L" << endl; 
    cout << "6 0 6 B L" << endl; 
    cout << "6 B 7 B R" << endl; 
    
    cout << "7 B 7 B R" << endl; 
    cout << "7 0 11 1 R" << endl; 
    cout << "7 1 11 1 R" << endl; 
    
    cout << "11 0 11 1 R" << endl; 
    cout << "11 1 11 1 R" << endl; 
    cout << "11 B 99 B L" << endl; 

    cout << "2 B 8 B L" << endl; 

    cout << "8 0 8 B L" << endl; 
   
    cout << "8 * 9 B L" << endl; 
    
  
    cout << "9 1 9 1 L" << endl; 
    cout << "9 0 9 1 L" << endl; 
  
    cout << "9 B 99 B R" << endl; 

    return 0;
}