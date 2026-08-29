#include <iostream>

using namespace std;

int main(){
    cout << "this programme will reverse a string" << endl;

    string str;
    getline(cin, str);

    for (int i = str.length() - 1; i >= 0; i--) cout << str[i];
    return 0;
}